### 성능 요약

### 메모리: 93.7 MB, 시간: 5.23 ms

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int N = records.length;
        Map<String, Integer> carInfoA = new HashMap<>();
        Map<String, Integer> carInfoB = new HashMap<>();
        Map<String, Integer> carFees = new HashMap<>();
        for (int i = 0; i < N; i++){
            int HH = Integer.parseInt(records[i].substring(0,2));
            int MM = Integer.parseInt(records[i].substring(3,5));
            int m = (HH * 60) + MM;
            String carNum = records[i].substring(6,10);
            String inOut = records[i].substring(11,13);
            if (inOut.equals("IN")){
                carInfoA.put(carNum, m);
            } else {
                if (carInfoB.containsKey(carNum)){
                    carInfoB.replace(carNum, (m - carInfoA.get(carNum)) + carInfoB.get(carNum));
                } else {
                    carInfoB.put(carNum, m - carInfoA.get(carNum));
                }
                carInfoA.remove(carNum);
            }
        }
        
        if (!carInfoA.isEmpty()){
            for (String carNum : carInfoA.keySet()){
                if (carInfoB.containsKey(carNum)){
                    carInfoB.replace(carNum, (((60 * 23) + 59) - carInfoA.get(carNum)) + carInfoB.get(carNum));
                } else {
                    carInfoB.put(carNum, (((60 * 23) + 59) - carInfoA.get(carNum)));
                }
            }
        }
        
        for (String carNum : carInfoB.keySet()){
            int m = carInfoB.get(carNum);
            if (m <= fees[0]){
                carFees.put(carNum, fees[1]);
            } else {
                if (((m-fees[0]) % fees[2]) == 0){
                    m = (m-fees[0]) / fees[2];
                } else {
                    m = ((m-fees[0]) / fees[2]) + 1;
                }
                carFees.put(carNum, fees[1] + (m * fees[3]));
            }
        }
        
        int[] answer = new int[carFees.size()];
        
        List<String> carNums = new ArrayList<>(carFees.keySet());
        carNums.sort((o1, o2) -> o1.compareTo(o2));
        for (int i = 0; i < carNums.size(); i++){
            answer[i] = carFees.get(carNums.get(i));
        }
        
        return answer;
    }
}