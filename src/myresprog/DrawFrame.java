/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myresprog;

import javax.swing.JFrame;

/**
 *
 * @author dennisschmock
 */
public class DrawFrame extends JFrame {

    public DrawFrame() {

        initUI();
    }

    private void initUI() {

        add(new Drawing());

        setTitle("TurtleFarm");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPanel().setVisible(true);
            }
        });
    }
}
