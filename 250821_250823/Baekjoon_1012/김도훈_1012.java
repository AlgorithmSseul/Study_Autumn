import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] map;
    static int[][] cabbage;

    static int M, N, K;

    // 우 하 좌 상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer MNK = new StringTokenizer(br.readLine());

            M = Integer.parseInt(MNK.nextToken());
            N = Integer.parseInt(MNK.nextToken());
            K = Integer.parseInt(MNK.nextToken());

            map = new boolean[N][M];
            cabbage = new int[K][2];

            for (int k = 0; k < K; k++) {
                StringTokenizer colrow = new StringTokenizer(br.readLine());
                int col = Integer.parseInt(colrow.nextToken());
                int row = Integer.parseInt(colrow.nextToken());

                cabbage[k][0] = row;
                cabbage[k][1] = col;


                map[row][col] = true;
            }

            int res = bfs(0);
            bw.write("" + res);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static int bfs(int res) {
        Deque<Integer> q = new ArrayDeque<>();

        for (int k = 0; k < K; k++) {
            int row = cabbage[k][0];
            int col = cabbage[k][1];

            if (map[row][col]) {
                // 방문 처리: 큐에 넣는 순간!
                map[row][col] = false;
                q.add(row * M + col);

                while (!q.isEmpty()) {
                    int v = q.poll();
                    int r = v / M;
                    int c = v % M;

                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];

                        // 1. 경계 나가는 경우
                        if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

                        // 2. 방문 안한곳
                        if (map[nr][nc]) {
                            map[nr][nc] = false;
                            q.add(nr * M + nc);
                        }
                    }
                }
                res++;
            }
        }
        return res;
    }
}
