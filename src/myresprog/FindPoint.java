package myresprog;


import java.awt.geom.Point2D;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class FindPoint {
    
    public static Point findNewPoint(Point old, double dist, double angle){
        double x = old.getX()+Math.cos((angle-90)* Math.PI / 180) * dist;
        double y = old.getY()+Math.sin((angle-90)* Math.PI / 180) * dist;
        System.out.println("New Point x:"+x+" y:"+y);
        return new Point (x,y);
    }
}
