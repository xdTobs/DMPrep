package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] infoline = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int n = infoline[0];
        int m = infoline[1];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            adjList.get(edge[0]).add(edge[1]);
            //adjList.get(edge[1]).add(edge[0]);

        }
        System.out.println(dfs(adjList,0));

    }

    static boolean dfs(List<List<Integer>> adjList, int beginNode) {
        Stack<Integer> dfsStack = new Stack<>();
        boolean[] isVisited = new boolean[adjList.size()];

        dfsStack.push(beginNode);
        while (!dfsStack.isEmpty()) {
            int current = dfsStack.pop();
            if (!isVisited[current]) {
                isVisited[current] = true;

                // do stuff

                for (int dest : adjList.get(current)) {
                    if (!isVisited[dest])
                        dfsStack.push(dest);
                }
            }

        }
        return isVisited[0];


    }
}
