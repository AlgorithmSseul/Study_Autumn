// 메모리: 50776 KB
// 시간: 388 ms

import java.io.*;
import java.util.*;

public class Main {

    static int INTMAX = Integer.MAX_VALUE;

    static public class Node {
        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<ArrayList<Node>> bus = new ArrayList<ArrayList<Node>>();
        for(int i = 0; i < N+1; i++) {
            bus.add(new ArrayList<Node>());
        }

        int start, end, cost;
        for(int i = 0; i < M; i++) {
            StringTokenizer SEC = new StringTokenizer(br.readLine());
            start = Integer.parseInt(SEC.nextToken());
            end = Integer.parseInt(SEC.nextToken());
            cost = Integer.parseInt(SEC.nextToken());

            // 문제에서 버스가 A -> B로만 간다는거였음. 버스라길래 당연히 왕복인줄 알았는데 유향이였음
            bus.get(start).add(new Node(end, cost));
            // bus.get(end).add(new Node(start, cost));
        }

        StringTokenizer SPEP = new StringTokenizer(br.readLine());
        int StartPoint = Integer.parseInt(SPEP.nextToken());
        int EndPoint = Integer.parseInt(SPEP.nextToken());

        // 거리값 담을 배열(최적)
        int[] dist = new int[N+1];

        for(int i = 0; i < N+1; i++) {
            dist[i] = INTMAX;
        }

        // 다익스트라 시작
        PriorityQueue<Node> q = new PriorityQueue<Node>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));

        q.offer(new Node(StartPoint, 0));
        dist[StartPoint] = 0;

        int res = 0;

        while (!q.isEmpty()) {
            Node curNode = q.poll();

            // 조기 코드 종료
            if(curNode.end == EndPoint) {
                res = dist[EndPoint];
                break;
            }

            if(dist[curNode.end] < curNode.cost){
                continue;
            }

            for(int i = 0; i < bus.get(curNode.end).size(); i++) {
                Node nextNode = bus.get(curNode.end).get(i);

                // 만약 거쳐서 가는 길이 이전에 있던 길보다 짧으면 갱신
                if(curNode.cost + nextNode.cost < dist[nextNode.end]) {
                    dist[nextNode.end] = curNode.cost + nextNode.cost;

                    // 갱신하면 큐에 넣는다
                    q.offer(new Node(nextNode.end, dist[nextNode.end]));
                }
            }
        }
        bw.write("" + res);
        bw.flush();
        bw.close();
    }
}
