//성능 요약
//메모리: 88.1 MB, 시간: 12.44 ms

import java.util.PriorityQueue;

class Solution {
    
    
    int solution(int[][] land) {
        
        int N = land.length;
        
        for(int i = 1; i < N; i++){
            for(int j = 0; j < 4; j++){
                int max = 0;
                for(int k = 0; k < 4; k++){
                    if(j == k) continue;
                    max = Math.max(max, land[i - 1][k]);
                }
                land[i][j] += max;
            }
        }
        
        int answer = 0;
        
        for(int i = 0; i < 4; i++){
            answer = Math.max(land[N - 1][i], answer);
        }
            
        return answer;
    }
}
