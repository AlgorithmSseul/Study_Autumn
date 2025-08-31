### 성능 요약

### 메모리: 87.5 MB, 시간: 17.24 ms

import java.util.*;

class Solution {
    int solution(int[][] land) {
        int answer = 0;
        
        int N = land.length;
        int[] prev = new int[4];
        for (int i = 0; i < 4; i++){
            prev[i] = land[0][i];
        }
        int[] dp = new int[4];
        for (int i = 1; i < N; i++){
            for (int j = 0; j < 4; j++){
                int max = 0;
                for (int k = 0; k < 4; k++){
                    if (j == k) continue;
                    max = Math.max(max, prev[k]);
                }
                dp[j] = max + land[i][j];
            }
            for (int j = 0; j < 4; j++){
                prev[j] = dp[j];
            }
        }
        
        for (int i = 0; i < 4; i++){
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
    
}