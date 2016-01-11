/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myresprog;

/**
 *
 * @author dennisschmock
 */
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {
    
    

    public void drawLine(Graphics g, OurPoint a, OurPoint b) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
        repaint();
    }
    
    

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        System.out.println("");

    }
    
    

    
    
}




