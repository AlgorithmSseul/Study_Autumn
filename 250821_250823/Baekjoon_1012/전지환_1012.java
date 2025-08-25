### 성능 요약

### 메모리: 15912 KB, 시간: 140 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, M, N, K;
	static int[][] parm;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			parm = new int[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				parm[B][A] = 1;
			}
			
			visited = new boolean[N][M];
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j]) continue;
					if (parm[i][j] == 0) continue;
					bfs(i,j);
					count++;
				}
			}
			sb.append(count);
			if (tc < T)sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {i, j});
		visited[i][j] = true;
		
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (visited[nr][nc]) continue;
				if (parm[nr][nc] == 0) continue;
				q.add(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}
}
