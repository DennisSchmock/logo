/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myresprog;

import java.awt.Color;
import java.util.ArrayList;
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
        interpretCommand(command);
        System.out.println(programCounter);
        programCounter++;

    }

    private void interpretCommand(String[] command) {
        String tempString = command[0].toLowerCase();
        switch (tempString) {
            case "fd":
                if (isNumeric(command[1])) {
                    currentPoint = FindPoint.findNewPoint(oldPoint, Integer.parseInt(command[1]), angle);
                }
                mainPanel.drawLine(oldPoint, new Point(FindPoint.getInt(currentPoint.getX()), FindPoint.getInt(currentPoint.getY())));
                oldPoint = currentPoint;
                break;
            case "bk":
                currentPoint = FindPoint.findNewPoint(oldPoint, Integer.parseInt(command[1]) * -1, angle);
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
                System.out.println("Expression added: " + command[1] + " with the value " + e.evaluate());
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
