import java.io.*;
import java.util.*;

// 메모리: 79.8 MB, 시간: 0.03 ms
class Solution {
	public int solution(int[] players, int m, int k) {

		int answer = 0;
		int[] serverRun = new int[24]; // 돌아가고 있는 서버개수

		for (int i = 0; i < players.length; i++) {
			if ((int) players[i] / m > serverRun[i]) {
				int j = 0;
                int plusServer = (int) players[i] / m-serverRun[i];
				while (j < k&&j + i < players.length) {
					serverRun[j + i]+=plusServer;
					j++;
					}
                answer+=plusServer;
				}
				
			}
		return answer;
	}
}
