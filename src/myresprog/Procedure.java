/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myresprog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dennisschmock
 */
public class Procedure {
    private String procName;
    private int procStart;
    private int procEnd;
    private int callPoint;
    private HashMap localVars = new HashMap();
    private String[] procedureCommand;
    
    
    public Procedure(String name, int procStart, int procEnd, String[] command){
        this.procName = name;
        this.procStart = procStart;
        this.procEnd = procEnd;
        System.out.println("Name: " + name + " Start: " + procStart + " End: " + procEnd);
        this.procedureCommand = command;
    }
    
    public void changeVar(HashMap m,String key, int value){
        m.replace(key, value);
    }
    
    public boolean containsParameters(){
        return !localVars.isEmpty();
    }

    /**
     * @return the procName
     */
    public String getProcName() {
        return procName;
    }

    /**
     * @param procName the procName to set
     */
    public void setProcName(String procName) {
        this.procName = procName;
    }

    /**
     * @return the procStart
     */
    public int getProcStart() {
        return procStart;
    }

    /**
     * @param procStart the procStart to set
     */
    public void setProcStart(int procStart) {
        this.procStart = procStart;
    }

    /**
     * @return the procEnd
     */
    public int getProcEnd() {
        return procEnd;
    }

    /**
     * @param procEnd the procEnd to set
     */
    public void setProcEnd(int procEnd) {
        this.procEnd = procEnd;
    }

    /**
     * @return the callPoint
     */
    public int getCallPoint() {
        return callPoint;
    }

    /**
     * @param callPoint the callPoint to set
     */
    public void setCallPoint(int callPoint) {
        this.callPoint = callPoint;
    }

    /**
     * @return the localVars
     */
    public HashMap getLocalVars() {
        return localVars;
    }

    /**
     * @param localVars the localVars to set
     */
    public void setLocalVars(HashMap localVars) {
        this.localVars = localVars;
    }

    /**
     * @return the procedureCommand
     */
    public String[] getProcedureCommand() {
        return procedureCommand;
    }

    /**
     * @param procedureCommand the procedureCommand to set
     */
    public void setProcedureCommand(String[] procedureCommand) {
        this.procedureCommand = procedureCommand;
    }
    
}
