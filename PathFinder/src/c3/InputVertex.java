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
public class InputVertex {
    String testLine;
    String vName;
    String [] input;
    int abscissa, ordinate;
    Vertex vert;
    InputVertex(String testline) {
        this.testLine = testline;
        this.input = testline.split(" ");
        this.vName = input[0];
        this.abscissa = Integer.parseInt(input[1]) * 5;
        this.ordinate = Integer.parseInt(input[2]) * 5;
    }    
    
    public Vertex addVertex() {
        vert = new Vertex(vName, abscissa, ordinate);
        return vert;
        
    }
}
