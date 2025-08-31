// LinkedList 메모리: 298300 KB, 시간: 1076 ms
// ArrayDeque 메모리: 311112 KB, 시간: 1164 ms

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;

    static int res = Integer.MIN_VALUE;

    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static public class Node {
        int row, col;

        Node (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer NM = new StringTokenizer(br.readLine());
        N = Integer.parseInt(NM.nextToken());
        M = Integer.parseInt(NM.nextToken());

        map = new int[N][M];

        for(int row = 0; row < N; row++) {
            StringTokenizer inRow = new StringTokenizer(br.readLine());

            for(int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(inRow.nextToken());
            }
        }

        dfs(0);

        bw.write("" + res);
        bw.flush();
        bw.close();
    }

    static void dfs(int depth) {
        // 벽 3개 추가되었을 때
        if(depth == 3) {
            bfs();
            return;
        }

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                // 질병도, 벽도 아닌 일반 빈칸일 때
                if(map[row][col] != 0) continue;

                // 해당 위치가 0, 그니깐 아무것도 없을 때
                map[row][col] = 1;
                dfs(depth + 1);
                map[row][col] = 0;
            }
        }
    }

    static void bfs() {

        // 둘 다 해봤다. 근데 이게 Queue에 대해 새로 선언해주는 횟수가 적으면 ArrayDeque가 유리한데 여러번 호출하면 배열 확정 + 복사가 발생한다.
        // LinkedList는 노드 단위로 붙여서 리사이즈 복사 비용이 없음. 즉 고반복, 재선언이 많은 경우엔 LinkedList가 유리하다.
        //Queue<Node> q = new ArrayDeque<Node>();
        Queue<Node> q = new LinkedList<Node>();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(map[row][col] == 2) {
                    q.add(new Node(row, col));
                }
            }
        }

        int[][] copymap = new int[N][M];

        for(int i = 0; i < N; i++) {
            copymap[i] = Arrays.copyOf(map[i], M);
        }

        while(!q.isEmpty()) {
            Node tempNode = q.poll();
            int row = tempNode.row;
            int col = tempNode.col;

            for(int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];

                // 1. 범위 밖으로 나가면
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                // 2. 갈수 없는곳. 그니깐 0이 아닌 딴거일 때
                if (copymap[nr][nc] != 0) continue;

                copymap[nr][nc] = 2;
                q.add(new Node(nr, nc));
            }
        }

        searchSafeArea(copymap);
    }

    static void searchSafeArea (int[][] inMap) {
        int safeArea = 0;
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(inMap[row][col] != 0) continue;

                safeArea++;
            }
        }

        if(safeArea > res) res = safeArea;
    }
}

