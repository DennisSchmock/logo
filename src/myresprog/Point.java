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
    
    public int returnIntX(){
        return (int) this.x;
    }
    
    public int returnIntY(){
        return (int) this.y;
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
