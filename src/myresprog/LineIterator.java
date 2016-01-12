package myresprog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Iterator;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author Daniel
 */
public class LineIterator implements Iterator<String> {

    String filename;
    BufferedReader br;
    String stringToReturn;

    LineIterator(String filename) throws IOException {
        this.filename = filename;
        try {
            Reader reader = new InputStreamReader(new FileInputStream(filename), "UTF-8");
            br = new BufferedReader(reader);
            stringToReturn = br.readLine();
            System.out.println(stringToReturn + " in constructor");
           
        } catch (IOException ex) {
            System.out.println("It did'nt read the file!");
        }

    }
    
    

    @Override
    public boolean hasNext() {
        if (stringToReturn == null) {
            try {
                br.close();
                System.out.println("* End of file *");
            } catch (IOException ex) {
            }
        }
        return (stringToReturn != null);
    }

    @Override
    public String next() {
        String r = stringToReturn;
        
        
            try {
            stringToReturn = br.readLine();
            System.out.println(stringToReturn);
        

        } catch (IOException ex) {
            System.out.println("noget fejlede i next");
        }
        

        return r;
    }

}
