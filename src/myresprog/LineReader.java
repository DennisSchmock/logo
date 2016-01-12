package myresprog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class LineReader implements Iterable<String>{
    private String filename;
    LineReader(String filename){
        this.filename=filename;        
    }
    

    @Override
    public Iterator<String> iterator() {
        try {
            return new LineIterator(filename);
        } catch (IOException ex) {
            Logger.getLogger(LineReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
    
    public ArrayList<String[]> textFileToStringArrayList() {
        ArrayList<String[]> textArray = new ArrayList();
        for (String s : new LineReader(filename)) {
            textArray.add(s.split(" ",0));
            System.out.println("Added: "+s+ " to arraylist!");
            for (int i = 0; i < textArray.get(0).length; i++) {
                
                  System.out.println(textArray.get(0)[i]);  
                
                
            }
        }
        return textArray;
    }
    
}
