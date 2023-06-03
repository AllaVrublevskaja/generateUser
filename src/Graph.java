import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

public class Graph {
    private final Map<UUID, List<UUID>> edges;  //список рёбер
    public Graph(Map<UUID, List<UUID>> edges) {
        this.edges = edges;
    }
    public void addEdge(UUID from, UUID to) {
        if (!edges.containsKey(from)) {
            edges.put(from, new ArrayList<>());
        }
        if (from != to)
            edges.get(from).add(to);
    }
    private void dfsUtil(UUID v, List<UUID> visited) {
        visited.add(v);
        System.out.println("Посещена вершина " + v);

        for(UUID to : edges.get(v)) {
            if(!visited.contains(to)) {
                dfsUtil(to, visited);
            }
        }
    }
    //Поиск в глубину
    public void dfs(UUID v) {
        List<UUID> visited = new ArrayList<>();
        dfsUtil(v, visited);
    }
    //Поиск в ширину
    public void bfs(UUID v) {
        Queue<UUID> q = new LinkedList<>();
        List<UUID> visited = new ArrayList<>();
        q.add(v);
        int level = 0;
        while(!q.isEmpty()) {
            int verticesInLevel = q.size();
            System.out.println("Текущий уровень: " + level);
            for(int i = 0; i < verticesInLevel; ++i) {
                UUID cur = q.poll();
                if(visited.contains(cur)) continue;
                visited.add(cur);
                q.addAll(edges.get(cur));
                System.out.print(cur + " ");
            }
            System.out.println();
            ++level;
        }
    }
}
