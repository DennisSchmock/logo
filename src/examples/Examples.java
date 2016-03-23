/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.util.ArrayList;

/**
 *
 * @author dennisschmock
 */
public class Examples {

    private ArrayList<String> codes;
    private String flower;
    private String fractal;
    private String colors;

   

    public Examples() {
        flower = "moveto 30,500\n"
                + "repeat 12\n"
                + "rt 90\n"
                + "mf 50\n"
                + "lt 90\n"
                + "call flower\n"
                + "repeat end\n"
                + "rt 90\n"
                + "call ground 500 30\n"
                + "\n"
                + "declare flower\n"
                + "color 3\n"
                + "fd 100\n"
                + "repeat 60\n"
                + "color 8\n"
                + "rt 6\n"
                + "fd 20\n"
                + "bk 20\n"
                + "repeat end\n"
                + "repeat 60\n"
                + "color 3\n"
                + "rt 6\n"
                + "fd 5\n"
                + "mb 5\n"
                + "repeat end\n"
                + "mb 100\n"
                + "declare end\n"
                + "\n"
                + "\n"
                + "declare ground x d\n"
                + "color 5 \n"
                + "repeat d\n"
                + "moveto 20,x\n"
                + "fd 700\n"
                + "let x x+1\n"
                + "repeat end\n"
                + "declare end\n"
                + "";

        fractal = "declare square l d\n"
                + "if d>0\n"
                + "repeat 4\n"
                + "fd l\n"
                + "rt 90\n"
                + "call square l*0.4 d-1\n"
                + "repeat end\n"
                + "if end\n"
                + "declare end\n"
                + "\n"
                + "call square 200 6";
        
        colors = "let red 0\n"
                + "let blue 0\n"
                + "let green 0\n"
                + "let a 1\n"
                + "\n"
                + "repeat 1000\n"
                + "\n"
                + "color red green blue\n"
                + "fd a\n"
                + "rt 90\n"
                + "let a a + 0.5\n"
                + "let red red +1 \n"
                + "let green red + 1\n"
                + "repeat end\n"
                + "\n"
                + "";

    }

    public ArrayList<String> getCodes() {
        return codes;
    }

    public String getFlower() {
        return flower;
    }

    public String getFractal() {
        return fractal;
    }
    
     public String getColors() {
        return colors;
    }
}
