/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myresprog;

import java.io.File;

/**
 *
 * @author dennisschmock
 */
public class FileFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public String getDescription() {
        return "Text documents (*.txt)";
    }

    @Override
    public boolean accept(File file) {
        // Allow only directories, or files with ".txt" extension
        return file.isDirectory() || file.getAbsolutePath().endsWith(".txt");
    }

}
