/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c3;

import java.util.ArrayList;

/**
 *
 * @author singh
 */
public class InputEdge {
    String testLine;
    String [] input;
    String start, end;
    Vertex from;
    Vertex to;
    double weight;
    InputEdge(String testLine) {
        this.testLine = testLine;
        this.input = testLine.split(" ");
        this.start = input[0];
        this.end = input[1];
        this.weight = Double.parseDouble(input[2]);
    }
    
    public Edge addEdge(ArrayList <Vertex> vertices) {
        Vertex v1 = null, v2 = null;
        
        for(Vertex vert: vertices) {
            if( vert.getName().equals(start) ) {
                v1 = vert;
            }
            if( vert.getName().equals(end) ) {
                v2 = vert;
            }
        }
        Edge e = new Edge(v1, v2, weight);
        return e;
    }
}
