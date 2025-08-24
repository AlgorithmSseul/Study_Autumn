// 메모리: 87.6 MB, 시간: 35.45 ms

import java.util.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        Map<Integer, List<String>> map = new HashMap<>();
        
        for(int i = 0; i < records.length; i++){
            StringTokenizer st = new StringTokenizer(records[i]);
            String time = st.nextToken();
            int cId = Integer.parseInt(st.nextToken());
            String inout = st.nextToken();
            List<String> times = map.get(cId);
            if(times == null){
                times = new ArrayList<String>();
            }
            times.add(time);
            map.put(cId, times);
        }
        
        List<List<Integer>> results = new ArrayList<>();
        
        // "HH:mm" 형식 파서
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        
        for(int key : map.keySet()){
            List<String> times = map.get(key);
            
            long wholeTime = 0;
            
            for(int t = 0; t < times.size(); t += 2){
                // 문자열 -> LocalTime 변환
                LocalTime time1 = LocalTime.parse(times.get(t), formatter);
                LocalTime time2 = (times.size() == t + 1) ? LocalTime.parse("23:59", formatter): LocalTime.parse(times.get(t + 1), formatter);
                
                // 두 시각의 차이 (분 단위)
                wholeTime += Duration.between(time1, time2).toMinutes();
                
            }
            
            int fee = fees[1];
            if((float)wholeTime / (float)fees[0] > 1){
                int extra = (int)wholeTime - fees[0];
                double extraUnit = Math.ceil((float)extra / (float)fees[2]);
                
                fee += extraUnit * fees[3];
            } 
            
            results.add(Arrays.asList(key, fee));
        }
        
        results.sort(Comparator.comparingInt(l -> l.get(0)));
        
        answer = new int[results.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = results.get(i).get(1);
        }
        
        return answer;
    }
}
