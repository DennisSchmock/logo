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
    static Point findNewPoint(Point old, int dist, int angle){
        double x = old.x+Math.cos((angle-90)* Math.PI / 180) * dist;
        double y = old.y+Math.sin((angle-90)* Math.PI / 180) * dist;
        System.out.println("New Point x:"+x+" y:"+y);
        return new Point (x,y);
    }
}
