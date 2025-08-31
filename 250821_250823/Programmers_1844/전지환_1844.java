### 성능 요약

### 메모리: 54.5 MB, 시간: 11.17 ms

import java.util.*;

class Solution {
    static int answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        answer = 0;
        visited = new boolean[n][m];
        bfs(new int[]{0,0}, maps, 0, n, m);
        
        return answer;
    }
    
    public void bfs(int[] start, int[][] maps, int count, int n, int m){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(start);
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            count++;
            int size = q.size();
            for (int s = 0; s < size; s++){
                int[] now = q.poll();
                int cx = now[0];
                int cy = now[1];
                for (int i = 0; i < 4; i++){
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    if (canNext(nx,ny,maps,n,m)){
                        if (nx == n-1 && ny == m-1) {
                            answer = count + 1;
                            return;
                        }
                        q.add(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        
        answer = -1;
    }
    // 벽인지
    // 범위 밖인지
    // 이미 간 곳인지
    public boolean canNext (int nx, int ny, int[][] maps, int n, int m){
        if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
            if (maps[nx][ny] != 0){
                if (!visited[nx][ny]){
                    return true;
                }
            }
        }
        return false;
    }
}