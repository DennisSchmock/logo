package myresprog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Point implements Comparable<Point> {
    private double x;
    private double y;
    
    Point(double x, double y){
        this.x=x;
        this.y=y;
        System.out.println("New point x: " + this.x);
        System.out.println("New point y: " + this.y);
    }
    
    
    @Override
    public int compareTo(Point o) {
        if (this.x == o.getX()&&this.y==o.getY()){
            return 0;
        }
        return -1;
    }
    
     public static int getInt(double coord){
        if (coord>0 && coord-(int)coord>0.5) return (int) (Math.ceil(coord)+0.1);
        if (coord<0 && (int)coord-coord>0.5) return (int) (Math.floor(coord)-0.1);
        else return (int) coord;
    }
    
    public int returnIntX(){
        if (x>0 && x-(int)x>0.5) return (int) (Math.ceil(x)+0.1);
        if (x<0 && (int)x-x>0.5) return (int) (Math.floor(x)-0.1);
        else return (int) x;
    }
    
    public int returnIntY(){
        if (y>0 && y-(int)y>0.5) return (int) (Math.ceil(y)+0.1);
        if (y<0 && (int)y-y>0.5) return (int) (Math.floor(y)-0.1);
        else return (int) y;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    
}
