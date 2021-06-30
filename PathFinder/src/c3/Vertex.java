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
public class Vertex {
    private String name;
    private int xCoor, yCoor;
    
    Vertex(String name, int xCoor, int yCoor) {
        this.name = name;
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    Vertex(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setX(int x) {
        this.xCoor = x;
    }
    public void setY(int y) {
        this.yCoor = y;
    }
        
    public int getX() {
        return this.xCoor;
    }
    
    public int getY() {
        return this.yCoor;
    }
    public String getXs() {
        return Double.toString((double)this.xCoor / 5.0 );
    }    
    public String getYs() {
        return Double.toString( (double)this.yCoor / 5.0 );
    }    
    @Override
    
    public String toString() {
        String hello = this.name + " " + Double.toString( (double)this.xCoor / 5.0) + " " + Double.toString( (double) this.yCoor / 5.0);
        return hello;
    }
}
