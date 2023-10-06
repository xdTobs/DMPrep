package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dijsktra {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] infoline = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int n = infoline[0];
        int m = infoline[1];
        List<List<List<Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            adjList.get(edge[0]).add(List.of(edge[1],edge[2]));
            //adjList.get(edge[1]).add(List.of(edge[0],edge[2]));

        }
        System.out.println(dijkstra(adjList,0));


    }

    static boolean dijkstra(List<List<List<Integer>>> adjList, int beginNode) {
        int[] distances = new int[adjList.size()];
        Arrays.fill(distances, Integer.MAX_VALUE); // Set initial distances to all nodes as infinity
        distances[beginNode] = 0; // Starting node's distance is 0
        PriorityQueue<NodeDistance> DijkstraQueue = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.distance)
        );

        DijkstraQueue.add(new NodeDistance(beginNode, 0));

        boolean[] isVisited = new boolean[adjList.size()];
        while (!DijkstraQueue.isEmpty()) {
            NodeDistance current = DijkstraQueue.poll();

            // If the current node has already been visited, skip it
            if (isVisited[current.node]) {
                continue;
            }

            // Mark the current node as visited
            isVisited[current.node] = true;

            for (List<Integer> neighbor : adjList.get(current.node)) {
                int nextNode = neighbor.get(0);
                int weight = neighbor.get(1);
                if (distances[nextNode] > distances[current.node] + weight) {
                    distances[nextNode] = distances[current.node] + weight;
                    DijkstraQueue.add(new NodeDistance(nextNode, distances[nextNode]));
                }
            }
        }

        return true;
    }
}

class NodeDistance {
    int node;
    int distance;

    public NodeDistance(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
