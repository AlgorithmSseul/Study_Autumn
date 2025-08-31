import java.util.*;
import java.io.*;

// 메모리: 87.6 MB, 시간: 29.76 ms
class Solution {
    int solution(int[][] land) {
        int N = land.length;     // 행의 개수 (세로 길이)
        
        // dp[r][c] = r행 c열에 도착했을 때의 최대 누적 점수
        int[][] dp = new int[N][4];
        
        // 첫 행 초기화: 첫 행은 그대로 시작
        for(int i =0; i<4; i++){
            dp[0][i] = land[0][i];
        }
        
        // 두 번째 행부터 마지막 행까지 DP 갱신
        for(int i=1; i<N; i++){
            dp[i][0] = land[i][0]+Math.max(Math.max(dp[i-1][1],dp[i-1][2]),dp[i-1][3]);
            dp[i][1] = land[i][1]+Math.max(Math.max(dp[i-1][0],dp[i-1][2]),dp[i-1][3]);
            dp[i][2] = land[i][2]+Math.max(Math.max(dp[i-1][0],dp[i-1][1]),dp[i-1][3]);
            dp[i][3] = land[i][3]+Math.max(Math.max(dp[i-1][0],dp[i-1][1]),dp[i-1][2]);
        }
        
        // 마지막 행에서의 최대값이 정답
        int answer = Math.max(Math.max(dp[N-1][0], dp[N-1][1]),
                             Math.max(dp[N-1][2], dp[N-1][3]));

        return answer;
    }
}
