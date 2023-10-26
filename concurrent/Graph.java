package concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Currently makes one big Graph
// But maybe we need to make an individual graph
// For each partition?

public class Graph {

    // Use to store node number and color, [3, "blue"]
    private Map<Integer, String> nodeColors;

    // use to store node and list connectins, [3, [1, 5, 7]]
    private Map<Integer, List<Integer>> adjacencyList;



    public Graph() {
        nodeColors = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addNode(int node, String color) {
        nodeColors.put(node, color);
        //might need erorr checking
        adjacencyList.put(node, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        if (adjacencyList.containsKey(source)){

            if (!adjacencyList.get(source).contains(destination))
            {
                adjacencyList.get(source).add(destination);
            }
            
        }
        else {
            adjacencyList.put(source, new ArrayList<>());
            adjacencyList.get(source).add(destination);
        }

        if (adjacencyList.containsKey(destination)){

            if (!adjacencyList.get(destination).contains(source))
            {
                adjacencyList.get(destination).add(source);
            }
        }
        else {
            adjacencyList.put(destination, new ArrayList<>());
            adjacencyList.get(destination).add(source);
        }


        //adjacencyList.get(source).add(destination);
        //adjacencyList.get(destination).add(source); 
    }

    public String getColor(int node) {
        return nodeColors.get(node);
    }

    public List<Integer> getNeighbors(int node) {
        return adjacencyList.get(node);
    }

    public void printGraph() {
        for (Map.Entry<Integer, String> entry : nodeColors.entrySet()) {
            int node = entry.getKey();
            String color = entry.getValue();
            List<Integer> neighbors = adjacencyList.get(node);
            
            System.out.print("Node " + node + " (Color: " + color + ") -> Neighbors: ");
            for (int neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }


        // Now you have the graph data stored in the 'graph' object, and you can access nodes, colors, and edges as needed.
}
// This code reads the input from a file named "input.txt" and constructs a graph using the Graph class, just like in the previous example. You can then access information about nodes, their colors, and their neighbors from the graph object. Make sure to adjust the filename ("input.txt") to match the actual name of your input file.




