import java.io.*;

// 메모리: 15512 KB, 시간: 88 ms
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		if (N == 1){
			System.out.println(1 % 15746);
            return;
        }
		if (N == 2){
			System.out.println(2 % 15746);
            return;
        }
        dp[1] = 1;
		dp[2] = 2;
		
		// dp[n] = dp[n-1] + dp[n-2] 식 가짐
		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
		}

		System.out.println(dp[N]);
	}

}


// %15746을 마지막에 하면 안되는 이유
// dp[N]의 값은 피보나치 비슷한 수열이라서 아주 빠르게 커짐
// N=1,000,000일 때 실제 값은 자릿수가 수십만 자리가 넘어감
// dp[1,000,000]은 훨씬 훨씬 커서 long으로도 절대 못 담으
// 중간 계산을 전부 저장해두고 마지막에만 % 15746을 하면 → 오버플로우 발생

