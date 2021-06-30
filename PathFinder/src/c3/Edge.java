/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c3;

/**
 *
 * @author singh
 */
public class Edge {
    private double weight; 
    private Vertex from;
    private Vertex to;
    private String start, end;
    Edge(Vertex from, Vertex to,double weight ) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.start = from.getName();
        this.end = to.getName();
    }
    
    public void setFrom(Vertex v) {
        this.from = v;
    }
    
    public void setTo(Vertex v) {
        this.to = v;
    } 
    
    public void setWeight(double w) {
        this.weight = w;
    }
    
    public double getWeight() {
        return this.weight;
    }
    public String getFrom() {
        return this.start;
    }
    public Vertex getVfrom() {
        return this.from;
    } 
    public Vertex getVto() {
        return this.to;
    }
   
    public String getTo() {
        return this.end;
    }
    
    public String getsWeight() {
        return Double.toString(weight);
    }
    @Override
    public String toString() {
        return "from : " + this.start + " " + "to : " + this.end + " " + "weight : " + Double.toString(weight);
    }
    
    
}
