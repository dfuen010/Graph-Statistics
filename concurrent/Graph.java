package concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

// Currently makes one big Graph
// But maybe we need to make an individual graph
// For each partition?

public class Graph implements Serializable {

    // Use to store node number and color, [3, "blue"]
    private Map<Integer, String> nodeColors;

    // use to store node and list connectins, [3, [1, 5, 7]]
    private Map<Integer, List<Integer>> adjacencyList;

    // use to store node and what partition it belongs to, [3, 1] - 3 is in partition 1
    private Map<Integer, Integer> nodePartitions;

    // store how many partitions are in the graph for determining how many actors to use
    private Integer numPartition;



    public Graph() {
        nodeColors = new HashMap<>();
        adjacencyList = new HashMap<>();
        nodePartitions = new HashMap<>();
        numPartition = 0;
    }

    public void addNode(int node, String color, int partition) {
        nodeColors.put(node, color);
        nodePartitions.put(node, partition);
        numPartition = partition + 1;
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

    //get the specified partition and it's nodes in a new graph
    public Graph getPartitionGraph(int chosenPartition)
    {
        Graph newgraph = new Graph();
        for (Map.Entry<Integer, String> entry : nodeColors.entrySet()) {
            int node = entry.getKey();
            String color = entry.getValue();
            List<Integer> neighbors = adjacencyList.get(node);
            Integer partition = nodePartitions.get(node);
            if (partition == chosenPartition) 
            {
                newgraph.addNode(node, color, partition);
                for (int neighbor : neighbors) {
                    newgraph.addEdge(node, neighbor);
                }
            }
        }
        return newgraph;
    }

    public List<Integer> getNodes()
    {
        List<Integer> nodeList = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : nodeColors.entrySet()) 
        {
            int node = entry.getKey();
            nodeList.add(node);
        }
        return nodeList;
    }

    public Integer getDegree(int node) 
    {
        Integer degree = 0;
        List<Integer> neighbors = adjacencyList.get(node);
        for (int neighbor: neighbors) 
        {
            degree = degree + 1;
        }
        return degree;
    }

    public String getColor(int node) {
        return nodeColors.get(node);
    }

    public List<Integer> getNeighbors(int node) {
        return adjacencyList.get(node);
    }

    public Integer getPartition(int node) {
        return nodePartitions.get(node);
    }

    public void printGraph() {
        System.out.print("Number of Partitions: " + numPartition + "\n");
        for (Map.Entry<Integer, String> entry : nodeColors.entrySet()) {
            int node = entry.getKey();
            String color = entry.getValue();
            List<Integer> neighbors = adjacencyList.get(node);
            Integer partition = nodePartitions.get(node);
        
            System.out.print("Node " + node + " (Color: " + color + ") (Partition: " + partition + ") -> Neighbors: ");
            for (int neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }


        // Now you have the graph data stored in the 'graph' object, and you can access nodes, colors, and edges as needed.
}
// This code reads the input from a file named "input.txt" and constructs a graph using the Graph class, just like in the previous example. You can then access information about nodes, their colors, and their neighbors from the graph object. Make sure to adjust the filename ("input.txt") to match the actual name of your input file.




