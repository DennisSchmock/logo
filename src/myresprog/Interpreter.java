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
    private HashMap vars = new HashMap();
    private ArrayList<Loop> loops;
    private HashMap procedures = new HashMap();
    private ArrayList<Procedure> runningProcedures = new ArrayList<>();

    public Interpreter(MainPanel panel) {
        mainPanel = panel;
        lr = new LineReader("Commands.txt");
        commands = lr.textFileToStringArrayList();
        angle = 0;
        oldPoint = new Point(400, 300);
        currentPoint = oldPoint;
        loops = new ArrayList<>();
        scanForProcedures();

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
                angle = angle + Double.parseDouble(command[1]);
                break;
            case "lt":
                angle = angle - Double.parseDouble(command[1]);
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
            case "mf":
                movePointerForward(command);
                break;
            case "mb":
                movePointerBack(command);
                break;
            case "declare":
                skipProcedure(command);
                break;
            case "call":
                callProcedure(command);
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
        if (command[1].equalsIgnoreCase("end")) {
            if (!loops.isEmpty()) {
                Loop intLoop = loops.get(loops.size() - 1);

                if (intLoop.getIterations() <= 0) {
                    loops.remove(intLoop);
                    System.out.println("Ended loop");
                    return;
                }
                programCounter = intLoop.getStartIndex();
                intLoop.setIterations(intLoop.getIterations() - 1);

            }

        }

        if (isNumeric(command[1])) {
            loops.add(new Loop("Loop" + programCounter, programCounter, 0, Integer.parseInt(command[1]) - 1));

        }

    }

    private void scanForProcedures() {
        int tempInt = 0;
        int endProc;
        
        for (String[] command1 : commands) {
            if (command1.length<=1){
                System.out.println("Do nothing");
            }
            else if (command1[0].equalsIgnoreCase("declare") && !command1[1].equalsIgnoreCase("end")) {
                endProc = findEndProc(commands, tempInt);
                declareProcedure(command1, tempInt, endProc);
            }
            tempInt++;
        }

    }

    private int findEndProc(ArrayList<String[]> commands, int tempInt) {
        for (int i = tempInt; i < commands.size(); i++) {
            String tempCommand[] = commands.get(i);
            if (tempCommand[0].equalsIgnoreCase("declare") && tempCommand[1].equalsIgnoreCase("end")) {
                return i;
            }

        }
        return 0;

    }

    private void skipProcedure(String[] command) {
        if (command.length <= 1) {
            return;
        }
        if (command[1].equalsIgnoreCase("end")){
            Procedure tempProc = runningProcedures.get(runningProcedures.size()-1);
            programCounter = tempProc.getCallPoint();
            runningProcedures.remove(tempProc);
            return;
        }
        System.out.println(command[1]);
        Procedure tempProc = (Procedure)procedures.get(command[1]);
        System.out.println(tempProc);
        programCounter = tempProc.getProcEnd();

    }

    private void declareProcedure(String[] command, int start, int end) {
        if (command.length <= 1) {
            return;
        }
        Procedure tempProc = new Procedure(command[1], start, end);
        procedures.put(command[1], tempProc);
        if (command.length > 2) {
            for (int i = 2; i < command.length; i++) {
                tempProc.getLocalVars().put(command[i], 0);

            }

        }

    }

    private void callProcedure(String[] command) {
        System.out.println("Trying to call");
        if(command.length<=1){
            return;
        }
        Procedure tempProc = (Procedure)procedures.get(command[1]);
        System.out.println(tempProc.getProcName());
        tempProc.setCallPoint(programCounter);
        programCounter = tempProc.getProcStart();
        runningProcedures.add(tempProc);
        

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

    private void movePointerForward(String[] command) {
        if (isNumeric(command[1])) {
            currentPoint = FindPoint.findNewPoint(oldPoint, Integer.parseInt(command[1]), angle);
        }
        oldPoint = currentPoint;
    }

    private void movePointerBack(String[] command) {
        if (isNumeric(command[1])) {
            currentPoint = FindPoint.findNewPoint(oldPoint, Integer.parseInt(command[1]) * -1, angle);
        }
        oldPoint = currentPoint;
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

}
