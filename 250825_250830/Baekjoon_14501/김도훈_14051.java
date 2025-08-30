// 메모리: 14280 KB
// 시간: 108 ms

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] counsel = new int[N+1][2];
        int[] dp = new int[N+2];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dayLen = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());

            counsel[i][0] = dayLen;
            counsel[i][1] = pay;
        }

        for(int i = N; i > 0; i--) {
            int next = i + counsel[i][0];

            // 일할 수 있는 날짜를 넘어가면 이전 최대값이 최대값일것
            if(next > N + 1) {
                dp[i] = dp[i+1];
                continue;
            }

            // 범위 밖으로 안넘으면?
            // 일 안하고 넘어간거(dp[i+1])이랑 일 했을 때 생길 값 중에 최대값을 골라야한다
            dp[i] = Math.max(dp[i+1], counsel[i][1] + dp[next]);
        }
        bw.write(""+dp[1]);
        bw.flush();
        bw.close();
    }
}
