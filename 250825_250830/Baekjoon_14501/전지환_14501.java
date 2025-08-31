### 성능 요약

### 메모리: 14256 KB, 시간: 100 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] T;
	static int[] P;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		max = 0;
		dfs(0, 0, 0);
		
		System.out.print(max);
		
	}

	static void dfs(int pre, int now, int cur) {
		if (now > N) {
			max = Math.max(max, cur - P[pre]);
			return;
		}
		
		if (now == N) {
			max = Math.max(max, cur);
			return;
		}
		for (int i = now; i < N; i++) {
			dfs(i, i + T[i], cur + P[i]);
		}
	}
}
