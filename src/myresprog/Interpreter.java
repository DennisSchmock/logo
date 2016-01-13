/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myresprog;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 *
 * @author dennisschmock
 */
public class Interpreter {

    private Point currentPoint;
    private Point oldPoint;
    private LineReader lr;
    private ArrayList<String[]> commands;
    private String[] command;
    private int programCounter;
    private MainPanel mainPanel;
    private double angle;
    private boolean repeat;
    private HashMap vars = new HashMap();
    private int countRepeat;
    private int repeats;
    private int startRepeat;
    private int endRepeat;
    private ArrayList<Loop> loops;

    public Interpreter(MainPanel panel) {
        mainPanel = panel;
        lr = new LineReader("Commands.txt");
        commands = lr.textFileToStringArrayList();
        angle = 0;
        oldPoint = new Point(400, 300);
        loops = new ArrayList<>();

        //   oldPoint = currentPoint;
    }

    public void performNextAction() {
        command = this.getCommands().get(getProgramCounter());
        //translateCommand(command);
        substituteVars(command);
//        System.out.println("Command: "+command[1]);
        if (shouldCalculate(command[0])) command[1]=calculateExp(command[1]);
//        System.out.println("Command after calculation: "+command[1]);
        interpretCommand(command);
        System.out.println(programCounter);
        programCounter++;

    }
    
    private boolean shouldCalculate(String command){
        return (command.equals("fd")||command.equals("bk")||command.equals("rt")||command.equals("lt"));
    }
    private void substituteVars(String[] command){
        String[] temp;
        if (!command[0].equals("let")){
        for (int i = 1; i < command.length; i++) {
            temp=command[i].split("-|\\+|\\/|\\*");
//            System.out.println("Temp[0] "+temp[0]);
            Arrays.sort(temp,new stringComp());
            for (int j = 0; j < temp.length; j++) {
            
            if (vars.containsKey(temp[j])){
//                System.out.println("Vars contains "+ temp[j]);
//                System.out.println("Value of: "+vars.get(temp[j]));
//                System.out.println("Old string"+this.command[i]);
                this.command[i]=this.command[i].replaceAll(temp[j], String.valueOf(vars.get(temp[j])));
//                System.out.println("New string"+this.command[i]);
            }
            }
        }
        }
        else{
            double valueOf=0;
            if (vars.containsKey(command[1])){
                valueOf=(double)vars.get(command[1]);
                vars.remove(command[1]);
            }
                for (int i = 2; i < command.length; i++) {
            temp=command[i].split("-|\\+|\\/|\\*");
            //System.out.println("Temp[0] "+temp[0]);
            Arrays.sort(temp,new stringComp());
            for (int j = 0; j < temp.length; j++) {
            
            if (vars.containsKey(temp[j])){
//                System.out.println("Vars contains "+ temp[j]);
//                System.out.println("Value of: "+vars.get(temp[j]));
//                System.out.println("Old string"+this.command[i]);
                this.command[i]=this.command[i].replaceAll(temp[j], String.valueOf(vars.get(temp[j])));
//                System.out.println("New string"+this.command[i]);
            }
            }
        }
                }
    
    }
    private String calculateExp(String cal){
//        System.out.println("We calculate");
        Expression e = new ExpressionBuilder(cal).build();
//        System.out.println(String.valueOf(e.evaluate()));
                return(String.valueOf(e.evaluate()));
                
    }
    
    private void translateCommand(String[] command){
        if (!command[0].equals("let")){
            for (int i = 1; i < command.length; i++) {
                if (vars.containsKey(command[i])) command[i]=String.valueOf(vars.get(command[i]));
            }
        }
        this.command=command;
    }

    private void interpretCommand(String[] command) {
        String tempString = command[0].toLowerCase();
        switch (tempString) {
            case "fd":
                if (isNumeric(command[1])) {
                    currentPoint = FindPoint.findNewPoint(oldPoint, (int)Double.parseDouble(command[1]), angle);
                }
                mainPanel.drawLine(oldPoint, new Point(FindPoint.getInt(currentPoint.getX()), FindPoint.getInt(currentPoint.getY())));
                oldPoint = currentPoint;
                break;
            case "bk":
                currentPoint = FindPoint.findNewPoint(oldPoint, (int)Double.parseDouble(command[1]) * -1, angle);
                mainPanel.drawLine(oldPoint, new Point(FindPoint.getInt(currentPoint.getX()), FindPoint.getInt(currentPoint.getY())));
                oldPoint = currentPoint;
                break;
            case "rt":
                angle = angle - Double.parseDouble(command[1]);
                break;
            case "lt":
                angle = angle + Double.parseDouble(command[1]);
                break;
            case "let":
                String expString = "";
                for (int i = 2; i < command.length; i++) {
                    expString += command[i];
                }
                Expression e = new ExpressionBuilder(expString).build();
                vars.putIfAbsent(command[1], e.evaluate());
//                System.out.println("Expression added: " + command[1] + " with the value " + e.evaluate());
                break;
            case "repeat":
                repeatCommands(command);
                break;
            case "color":
                changeColor(command);
                break;

        }

    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    
    private void repeatCommands(String[] command) {
        // mainPanel.timer.stop();
        if (command.length <= 1) {
            return;
        }
        if (command[1].equalsIgnoreCase("end") && countRepeat < repeats) {
            programCounter = startRepeat;
            countRepeat++;

        }

        if (isNumeric(command[1])) {
            startRepeat = programCounter;
            repeats = Integer.parseInt(command[1]);
            countRepeat = 0;

        }

    }

    /**
     * @return the currentPoint
     */
    public Point getCurrentPoint() {
        return currentPoint;
    }

    /**
     * @param currentPoint the currentPoint to set
     */
    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    /**
     * @return the oldPoint
     */
    public Point getOldPoint() {
        return oldPoint;
    }

    /**
     * @param oldPoint the oldPoint to set
     */
    public void setOldPoint(Point oldPoint) {
        this.oldPoint = oldPoint;
    }

    /**
     * @return the commands
     */
    public ArrayList<String[]> getCommands() {
        return commands;
    }

    /**
     * @param aCommands the commands to set
     */
    public void setCommands(ArrayList<String[]> aCommands) {
        commands = aCommands;
    }

    /**
     * @return the programCounter
     */
    public int getProgramCounter() {
        return programCounter;
    }

    /**
     * @param aProgramCounter the programCounter to set
     */
    public void setProgramCounter(int aProgramCounter) {
        programCounter = aProgramCounter;
    }

    private void changeColor(String[] s) {
        String temp = "";
        if (s.length > 1) {
            temp = s[1];
        }
        // s.toLowerCase();
        switch (temp) {
            case "red":
                mainPanel.setColor(Color.RED);
                break;
            case "yellow":
                mainPanel.setColor(Color.YELLOW);
                break;
            case "green":
                mainPanel.setColor(Color.GREEN);
                break;
            case "blue":
                mainPanel.setColor(Color.BLUE);
                break;
            case "black":
                mainPanel.setColor(Color.BLACK);
                break;
            default:
                mainPanel.setColor(Color.BLACK);
                break;
        }
    }

//    public static boolean isNumeric(String str) {
//        try {
//            double d = Double.parseDouble(str);
//        } catch (NumberFormatException nfe) {
//            return false;
//        }
//        return true;
//    }
}
