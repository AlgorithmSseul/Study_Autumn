import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution { //메모리: 91.4 MB, 시간: 7.21 ms
    public int[] solution(int[] fees, String[] records) {

        String[][] parts = new String[1000][3];
        Map<String, String> map = new HashMap<>();
        Map<String, Integer> totalMap = new TreeMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] part = records[i].split(" ");
            int useTime = 0;
            String a = part[0]; // time
            String b = part[1]; // car
            String c = part[2]; // IN/OUT

            if (c.equals("IN")) {
                map.put(b, a);
            } else {
                String inputTime = map.remove(b);
                String[] splitInputTime = inputTime.split(":");
                String[] splitOutputTime = a.split(":");

                int inputMinute = Integer.parseInt(splitInputTime[0]) * 60 + Integer.parseInt(splitInputTime[1]);
                int outputMinute = Integer.parseInt(splitOutputTime[0]) * 60 + Integer.parseInt(splitOutputTime[1]);

                useTime = outputMinute - inputMinute;


                totalMap.put(b, totalMap.getOrDefault(b, 0) + useTime);
            }
        }


        int notOutputCar = 23 * 60 + 59;
        for (Map.Entry<String, String> e : map.entrySet()) {
            String[] splitInputTime = e.getValue().split(":");
            int inputMinute = Integer.parseInt(splitInputTime[0]) * 60 + Integer.parseInt(splitInputTime[1]);
            int notOutUseTime = notOutputCar - inputMinute;


            totalMap.put(e.getKey(), totalMap.getOrDefault(e.getKey(), 0) + notOutUseTime);
        }

        int[] answer = new int[totalMap.size()];
        int idx = 0;
        for (String car : totalMap.keySet()) {
            int used = totalMap.get(car);
            answer[idx++] = costCalc(used, fees);
        }
        return answer;
    }


    private int costCalc(int time, int[] fees) {
        int baseTime = fees[0];
        int baseFee  = fees[1];
        int unitTime = fees[2];
        int unitFee  = fees[3];

        if (time <= baseTime) return baseFee;
        int extra = time - baseTime;
        int units = (extra + unitTime - 1) / unitTime;
        return baseFee + units * unitFee;
    }
}