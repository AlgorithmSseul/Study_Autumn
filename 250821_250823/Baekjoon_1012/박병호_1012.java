package Baekjoon_1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 박병호_1012 {
    static int M;
    static int N;
    static int[][] shipfall;
    static boolean[][] visit;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            shipfall = new int[N][M];
            visit = new boolean[N][M];
            int V = Integer.parseInt(st.nextToken());

            for (int i = 0; i < V; i++) {
                StringTokenizer st1=new StringTokenizer(br.readLine());
                int num1 =Integer.parseInt(st1.nextToken());
                int num2 = Integer.parseInt(st1.nextToken());
                shipfall[num2][num1] = 1;
            }
            int answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (shipfall[i][j] == 1 && !visit[i][j]) {
                        visit[i][j] = true;
                        bfs(i, j);
                        answer++;
                    }
                }
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<int[]>();
        int[] first = { x, y };
        q.add(first);
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int X = temp[0];
            int Y = temp[1];
            for (int i = 0; i < 4; i++) {
                int nx = X + dx[i];
                int ny = Y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visit[nx][ny] && shipfall[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    q.add(new int[] { nx, ny });
                }
            }
        }
    }

}
