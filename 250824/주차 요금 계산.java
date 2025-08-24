//성능 요약
//메모리: 90.5 MB, 시간: 7.06 ms

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        boolean[] inout = new boolean[10000];
        String[] timeRecord = new String[10000];
        int[] totalTime = new int[10000];
        
        for(int t = 0; t < records.length; t++) {
            String[] stamp = records[t].split(" ");
            
            int carNum = Integer.parseInt(stamp[1]);
            
            if(stamp[2].equals("IN")) {                
                timeRecord[carNum] = stamp[0];
                inout[carNum] = true;
            } else {
                inout[carNum] = false;
                
                String[] inTimeStamp = timeRecord[carNum].split(":");
                int inTime = Integer.parseInt(inTimeStamp[0]);
                int inMinute = Integer.parseInt(inTimeStamp[1]);
                
                String[] outTimeStamp = stamp[0].split(":");
                int outTime = Integer.parseInt(outTimeStamp[0]);
                int outMinute = Integer.parseInt(outTimeStamp[1]);
                
                int minGap = outMinute - inMinute;
                int tempTime = 0;
                // 들어간 시간의 분이 나간 시간의 분보다 크면(ex. 25분 입장 15분 퇴장)
                if(minGap < 0) {
                    tempTime = (outTime - 1 - inTime) * 60 + (60 + minGap);
                } else {
                    tempTime = (outTime - inTime) * 60 + minGap; 
                }
                totalTime[carNum] += tempTime;
            }
        }// 길이만큼 실행한거
        
        // 이젠 방문만 하고 아직 안나간 애들 찾기
        for(int i = 0; i < 10000; i++) {
            if(!inout[i]) continue;
            
            String[] inTimeStamp = timeRecord[i].split(":");
            int inTime = Integer.parseInt(inTimeStamp[0]);
            int inMinute = Integer.parseInt(inTimeStamp[1]);
            
            
            int minGap = 59 - inMinute;
            int tempTime = 0;
            // 59가 제일 커서 따로 구분없음
            totalTime[i] += (23 - inTime) * 60 + minGap; 
        }
        
        int defTime = fees[0];
        int defPay = fees[1];
        int defMinGap = fees[2];
        int defMinGapPay = fees[3];
        
        List<Integer> ans = new ArrayList<Integer>();
        
        for(int i = 0; i < 10000; i++) {
            if(totalTime[i] == 0) continue;
            
            if(totalTime[i] <= defTime) {
                ans.add(defPay);
                continue;
            }
            
            int pay = 0;
            
            if(((totalTime[i] - defTime) % defMinGap) == 0) {
                pay = defPay + ((totalTime[i] - defTime) / defMinGap) * defMinGapPay;
            } else {
                pay = defPay + (((totalTime[i] - defTime) / defMinGap) + 1) * defMinGapPay;
            }
            ans.add(pay);
        }
                
        int ansSize = ans.size();
        
        int[] answer = new int[ansSize];
        
        for(int i = 0; i < ansSize; i++) {
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}