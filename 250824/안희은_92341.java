// 메모리: 76.6 MB, 시간: 14.66 ms
import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // fees: [기본시간, 기본요금, 단위시간, 단위요금]
        int baseTime = fees[0], baseFee = fees[1], unitTime = fees[2], unitFee = fees[3];

        // 입차 시각(분) 저장: 차량번호 -> 입차분
        Map<Integer, Integer> inMap = new HashMap<>();
        // 총 주차시간(분) 누적: 차량번호 -> 누적분
        Map<Integer, Integer> totalTime = new HashMap<>();

        for (String record : records) {
            String[] part = record.split(" ");               // ["HH:MM", "####", "IN/OUT"]
            String[] hm = part[0].split(":");
            int time = Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]); // 분 단위
            int carNum = Integer.parseInt(part[1]);
            String action = part[2];

            if (action.equals("IN")) {
                inMap.put(carNum, time);
            } else { // OUT
                int inTime = inMap.remove(carNum);
                int duration = time - inTime;
                totalTime.put(carNum, totalTime.getOrDefault(carNum, 0) + duration);
            }
        }

        // 출차하지 않은 차량: 23:59로 정산
        final int END = 23 * 60 + 59;
        for (Map.Entry<Integer, Integer> e : inMap.entrySet()) {
            int carNum = e.getKey();
            int inTime = e.getValue();
            int duration = END - inTime;
            totalTime.put(carNum, totalTime.getOrDefault(carNum, 0) + duration);
        }

        // 차량번호 오름차순으로 요금 계산
        List<Integer> cars = new ArrayList<>(totalTime.keySet());
        Collections.sort(cars);

        int[] answer = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            int t = totalTime.get(cars.get(i));
            answer[i] = calcFee(t, baseTime, baseFee, unitTime, unitFee);
        }
        return answer;
    }

    // 요금 계산
    private int calcFee(int t, int baseTime, int baseFee, int unitTime, int unitFee) {
        if (t <= baseTime) return baseFee;
        int extra = t - baseTime;
        int units = (int) Math.ceil(extra / (double) unitTime);
        return baseFee + units * unitFee;
    }
}
