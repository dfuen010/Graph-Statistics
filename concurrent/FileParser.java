package concurrent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import concurrent.Graph;

// Have list of classes in salsa that are actors?

public class FileParser {

    private static String filepath;

    public FileParser(String filePath)
    {
        filepath = filePath;
    }

    public static Graph createGraph()
    {
        
        Graph graph = new Graph();
        int currentPartition = -1;





        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            String[] tmpNodes;
            String[] tmpColors;
            String[] tmpEdges;
            String[] tmpTmp;

            while ((line = reader.readLine()) != null) {

                // Should Generally be true
                if (line.startsWith("partition")) {
                    currentPartition++;

                    line = reader.readLine();
                    
                    
                    tmpNodes = line.split(",");
                    
                    // Move on from node value to node color
                    line = reader.readLine();

                    tmpColors = line.split(",");


                    for (int i = 0; i < tmpNodes.length; i++)
                    {
                        int nodeInt = Integer.parseInt(tmpNodes[i]);
                        graph.addNode(nodeInt, tmpColors[i], currentPartition);
                    }

                    // Move on from node value to edges
                    line = reader.readLine();

                    // ["1,2", "12,5", "2,453"]
                    tmpEdges = line.split(" ");

                    for (int c = 0; c < tmpEdges.length; c++) {
                        tmpTmp = tmpEdges[c].split(",");
                        // Now is just ["3", "23"]

                        int tmpIntOne = Integer.parseInt(tmpTmp[0]);
                        int tmpIntTwo = Integer.parseInt(tmpTmp[1]);
                        //System.err.println("ERRORS");
                        //System.err.println(tmpIntOne);
                        //System.err.println(tmpIntTwo);
                        graph.addEdge(tmpIntOne, tmpIntTwo);
                    }



                } 
                else {
                    System.err.println("NO PARTITION FOUND");
                }
                
                //System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        graph.printGraph();
        return graph;
    }

    public static void main(String[] args) 
    {
        
        Graph graph = new Graph();
        int currentPartition = -1;





        try (BufferedReader reader = new BufferedReader(new FileReader("concurrent/input_file.txt"))) {
            String line;
            String[] tmpNodes;
            String[] tmpColors;
            String[] tmpEdges;
            String[] tmpTmp;

            while ((line = reader.readLine()) != null) {

                // Should Generally be true
                if (line.startsWith("partition")) {
                    currentPartition++;

                    line = reader.readLine();
                    
                    
                    tmpNodes = line.split(",");
                    
                    // Move on from node value to node color
                    line = reader.readLine();

                    tmpColors = line.split(",");


                    for (int i = 0; i < tmpNodes.length; i++)
                    {
                        int nodeInt = Integer.parseInt(tmpNodes[i]);
                        graph.addNode(nodeInt, tmpColors[i], currentPartition);
                    }

                    // Move on from node value to edges
                    line = reader.readLine();

                    // ["1,2", "12,5", "2,453"]
                    tmpEdges = line.split(" ");

                    for (int c = 0; c < tmpEdges.length; c++) {
                        tmpTmp = tmpEdges[c].split(",");
                        // Now is just ["3", "23"]

                        int tmpIntOne = Integer.parseInt(tmpTmp[0]);
                        int tmpIntTwo = Integer.parseInt(tmpTmp[1]);
                        //System.err.println("ERRORS");
                        //System.err.println(tmpIntOne);
                        //System.err.println(tmpIntTwo);
                        graph.addEdge(tmpIntOne, tmpIntTwo);
                    }



                } 
                else {
                    System.err.println("NO PARTITION FOUND");
                }
                
                //System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> nodes = graph.getNodes();
        for (Integer i = 0; i < nodes.size(); ++i)
        {
            Integer currNode = nodes.get(i);
            System.out.println(currNode);
        }

        System.out.println(graph.getDegree(0));

        graph.printGraph();
    }
        
        
        // Now you have the graph data stored in the 'graph' object, and you can access nodes, colors, and edges as needed.
    
}









/*
 * 
 * try (BufferedReader reader = new BufferedReader(new FileReader("concurrent/input_file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("partition")) {
                    currentPartition++;
                } else if (!line.isEmpty()) {
                    String[] parts = line.split(" ");
                    int node = Integer.parseInt(parts[0].split(",")[0]);
                    String color = parts[1];
                    String[] edges = parts[2].split(" ");

                    graph.addNode(node, color);

                    for (String edge : edges) {
                        int neighbor = Integer.parseInt(edge.split(",")[1]);
                        graph.addEdge(node, neighbor);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

 * 
 * 
 * 
 * 
 */
