//메모리: 13784 KB, 시간: 100 ms
import java.io.*;
import java.util.*;

public class Main {
    static int[][] ground;
    static boolean[][] visited;
    static int M, N;
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우 (행 변화)
    static int[] dc = {0, 0, -1, 1}; // 상하좌우 (열 변화)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Tc = Integer.parseInt(br.readLine().trim()); // 테스트케이스
        for (int t = 0; t < Tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 가로(열)
            N = Integer.parseInt(st.nextToken()); // 세로(행)
            int K = Integer.parseInt(st.nextToken()); // 배추 개수

            ground = new int[N][M];      // 행=N, 열=M, 반대로 쓰지 않기
            visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); // 열
                int y = Integer.parseInt(st.nextToken()); // 행
                ground[y][x] = 1;                         // y가 행, x가 열
            }

            int worm = 0; // 지렁이 수
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (ground[r][c] == 1 && !visited[r][c]) {
                        bfs(r, c);
                        worm++;
                    }
                }
            }
            System.out.println(worm);
        }

    }

    static void bfs(int sr, int sc) {
        Deque<int[]> q = new ArrayDeque<>();
        visited[sr][sc] = true;
        q.offer(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue; 
                if (visited[nr][nc] || ground[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
    }
}

