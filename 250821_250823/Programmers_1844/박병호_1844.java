package Programmers_1844;

import java.util.Queue;
import java.util.LinkedList;

class 박병호_1844 {
    static int []dx ={-1,1,0,0};
    static int []dy ={0,0,-1,1};
    static boolean[][] visited;
    static int count=-1;

    public int solution(int[][] maps) {

        int n=maps.length-1;
        int m=maps[0].length-1;
        visited= new boolean[n+1][m+1];
        bfs(n,m,maps);
        int answer = 0;
        answer = count;
        return answer;
    }

    public static void bfs(int n,int m,int [][]maps){

        Queue<int[]> q = new LinkedList<int[]>();
        int []start = {0,0,1};
        q.add(start);
        visited[0][0]=true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int X = temp[0];
            int Y = temp[1];
            int Z = temp[2];
            if(X==n&&Y==m){
                count=Z;
                break;
            }else{
                for (int i = 0; i < 4; i++) {
                    int nx = X + dx[i];
                    int ny = Y + dy[i];

                    if (nx >= 0 && nx < n+1 && ny >= 0 && ny < m+1 && !visited[nx][ny] && maps[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        q.add(new int[] { nx, ny, Z+1});
                    }
                }
            }
        }
    }
}