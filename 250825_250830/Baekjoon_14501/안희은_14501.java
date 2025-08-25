import java.io.*;
import java.util.*;

// 메모리: 11552 KB, 시간: 64 ms
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] T = new int[N + 2]; // 상담 소요 일수
		int[] P = new int[N + 2]; // 금액
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		// dp[i] = i일에 일을 시작할 때 얻을 수 있는 최대 수익
		int[] dp = new int[N + 2];

		for (int i = N; i >= 1; i--) {
			int next = i + T[i]; // 이 상담을 끝낸 뒤의 다음 시작 가능일
			if (next <= N + 1) {
				dp[i] = Math.max(P[i] + dp[next], dp[i + 1]); // 현재 선택 vs 스킵하고 다음꺼 선택
			} else {
				dp[i] = dp[i + 1]; // 기간을 넘기면 스킵
			}
		}

		System.out.println(dp[1]);
	}
}

// DP 지선생 안 쓸 때까지 해보기...
// 왜 마지막에 dp[1]을 출력해야되는지 궁금했는데
// dp[1] = max( dp[2] ,  P[1] + dp[1+T[1]] )   여기서 1일부터 시작할지, 2일부터 시작할지 결정

