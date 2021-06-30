/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JTextField;
/**
 *
 * @author singh
 */
public class FormGraph {
    Map <String, ArrayList <Edge>> pGraph = new TreeMap <String, ArrayList <Edge>> ();
    Map <String, Double> cost = new HashMap <String, Double>() ;
    Map <String, Integer> visited = new HashMap <String, Integer>();
    Map <String, Edge> path = new HashMap <String, Edge>() ;
    ArrayList <Vertex> vertices = new ArrayList <Vertex>();
    ArrayList <Edge> edgePath = new ArrayList <Edge>();
    private String pathWay = "";
    private String graphicPath = "";
    private int size;
    FormGraph(int size, ArrayList <Vertex> vertices) {
        this.size = size;
        this.vertices = vertices;
    }
    //PathTransition t=new PathTransition();
    public String getPathWay() {
        return this.pathWay;
    }
    
    public String getGraphicPath() {
        return this.graphicPath;
    }
   
    
    public void generate(ArrayList <Edge> edges) {
        for(Edge e: edges) {
            if(pGraph.containsKey(e.getFrom())) {
                ArrayList <Edge> hello = pGraph.get(e.getFrom());
                hello.add(e);
                pGraph.put(e.getFrom(), hello);               
            }
            else {
                ArrayList <Edge> hello = new ArrayList <Edge> ();
                hello.add(e);
                pGraph.put(e.getFrom(), hello);
            }
        }
        
        for(Vertex v: vertices) {
            cost.put(v.getName(), Double.MAX_VALUE);
            visited.put(v.getName(), -1);
        }        
        
    }
    
    public void printGraph() {
        for(Map.Entry<String, ArrayList <Edge> > entry: pGraph.entrySet()) {
            System.out.println("From :" + entry.getKey());
            for(Edge e: entry.getValue() ) {
                System.out.println(e.toString());
            }
        }
    }
 
    public String Min(Map<String, Double> cost, Map<String, Integer> visited) {
        double min = Double.MAX_VALUE;
        String current = null;
        for(Map.Entry<String, ArrayList <Edge> > entry: pGraph.entrySet() ) {
            if(cost.get(entry.getKey() ) < min && visited.get(entry.getKey()) == -1 ) {
                min = cost.get(entry.getKey());
                current = entry.getKey();
            }
            
        }
        return current;
    }
    
    public void Dijkstra(String source) {
        cost.put(source, 0.0);
//        visited.put(source, 1);
        for(int i = 0; i < size ; i++) {
            String least = Min(cost, visited);
            if(least == null) 
                break;
            visited.put(least, 1);
            ArrayList <Edge> list = pGraph.get(least);
            for(Edge e: list) {
                if(least.equals("C") && e.getTo().equals("E")){
                    System.out.println("Edge: "+e.toString());
                    System.out.println("1: "+visited.containsKey(e.getTo()) +" 2 " + visited.get(e.getTo()) + " 3: " + cost.get(e.getTo())+ " 4: "+(cost.get(least) + e.getWeight()));
                }
                if( visited.containsKey(e.getTo()) &&   visited.get(e.getTo()) != 1 && cost.get(e.getTo()) > cost.get(least) + e.getWeight() ) {
                    cost.put(e.getTo(), cost.get(least) + e.getWeight());
                    System.out.println(" d From  " + e.getFrom() + "d To " + e.getTo() + "least " + least);
                    path.put(e.getTo(), e);
                    
                }
            }            
        } 
        
    }
    
    public void printPath(String destination, String source) {
        Edge ee = path.get(destination);
//        System.out.println("Passed" + ee.toString());
        if(ee.getFrom().equals(source)) {
            pathWay = pathWay + " " + ee.getFrom() ;
            return;
        }
        else {
            printPath(ee.getFrom(),source);
            pathWay = pathWay + " -> " + ee.getFrom() ;
            
        }
    }
    
    public void pathEdge(String destination, String source)
    {
        Edge ee = path.get(destination);
//        System.out.println(ee.toString());
        if(ee.getFrom().equals(source))
        {
            edgePath.add(ee);
            return;
        }
        else {
            pathEdge(ee.getFrom(),source);
            edgePath.add(ee);
            
        }
    }  
 
    public void graphicPath(String destination, String source) {
        Edge ee = path.get(destination);
//        System.out.println("Passed" + ee.toString());
        if(ee.getFrom().equals(source)) {
            graphicPath = graphicPath + " " + ee.getFrom() ;
            return;
        }
        else {
            graphicPath(ee.getFrom(),source);
            graphicPath = graphicPath + " " + ee.getFrom() ;
            
        }
    }    

    
}
