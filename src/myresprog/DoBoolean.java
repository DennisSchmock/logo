/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myresprog;

import static myresprog.Interpreter.isNumeric;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 *
 * @author Daniel
 */
public class DoBoolean {
    private static String evaluate;
    
    public static boolean DoBoolean(String evaluate){
        DoBoolean.evaluate=evaluate;
        boolean result=false;
        
        while(checkPar(evaluate)){
            replaceInPar(evaluate);
            evaluate=DoBoolean.evaluate;
        }
        result=exprEvaluation(evaluate);
        
        
        return result;
    }
    
    private static boolean checkPar(String str){
        if (str.length()<=0) return false;
        
        if (str.charAt(0)!='(') return checkPar(str.substring(1));
        else if(str.charAt(str.length()-1)!=')') return checkPar(str.substring(0,str.length()-1));
        else return true;
                
        
    }
    private static void replaceInPar(String evaluate){
        if (evaluate.length()<=0) return;
        if (evaluate.charAt(0)!='(') replaceInPar(evaluate.substring(1));
        else if(evaluate.charAt(evaluate.length()-1)!=')') replaceInPar(evaluate.substring(0,evaluate.length()-1));
        
        else{
            if (checkPar(evaluate.substring(1,evaluate.length()-1))){
                replaceInPar(evaluate.substring(1,evaluate.length()));
            }
            else{
                DoBoolean.evaluate=DoBoolean.evaluate.replaceAll(evaluate, exprEvaluationString(evaluate));
            }
        }
    }
    private static String exprEvaluationString(String evaluate){
        while (evaluate.contains("&&")){
            evaluateAND(evaluate);
        }
        if (evaluateSingleExp(DoBoolean.evaluate)) return "1<2";
        else return "1>2";
    }
    private static boolean exprEvaluation(String evaluate){
        while (evaluate.contains("&&")){
            evaluateAND(evaluate);
        }
        return evaluateSingleExp(DoBoolean.evaluate);
    }
    
    private static boolean evaluateSingleExp(String evaluate){
        System.out.println("Evaluate "+evaluate);
        boolean result = false;
        
            if (evaluate.contains("<=")) result= smallerThanOrEqual(evaluate.split("<="));
            else if (evaluate.contains("<=")) result= smallerThanOrEqual(evaluate.split("<="));
            else if (evaluate.contains(">=")) result= largerThanOrEqual(evaluate.split(">="));
            else if (evaluate.contains("==")) result= equal(evaluate.split("=="));
            
            else if (evaluate.contains("<")) result= smallerThan(evaluate.split("<"));
            else if (evaluate.contains(">")) result= largerThan(evaluate.split(">"));
            else System.out.println("There does not seem to be a boolean expression in the line "+evaluate);
        System.out.println("Result of evaluate single exp: "+ result);
        return result;
    }
    private static boolean evaluateAND(String evaluate){
        boolean result=false;
        String[] split=evaluate.split("&&");
        for (int i = 0; i < split.length; i++) {
            boolean bool1;
            boolean bool2;
            System.out.println("evaluate and String: "+ evaluate);
            System.out.println("After split first element reads: "+split[0]);
            String val1=split[i].split("'|''|'")[split[i].split("'|''|'").length-1];
            String val2=split[i].split("'|''|'")[0];
            System.out.println("Split by ||: "+split[0].split("'|''|'")[0]);
            System.out.println("Val1: "+val1);
            System.out.println("Val2: "+val2);
            bool1=evaluateSingleExp(val1);
            bool2=evaluateSingleExp(val2);
            if (bool1&&bool2) DoBoolean.evaluate=DoBoolean.evaluate.replace(val1+"&&"+val2, "1<2");
            else{
                DoBoolean.evaluate=DoBoolean.evaluate.replace(val1+"&&"+val2, "1>2");
            }
        }
        return true;
    }
    
    
    private static boolean largerThan(String[] val){
        
        boolean result=false;
        double val1;
        double val2;
        Expression e = new ExpressionBuilder(val[0]).build();
                val1=e.evaluate();
                e = new ExpressionBuilder(val[1]).build();
                val2=e.evaluate();
        if (val1>val2) result=true;
     
        return result;
    }
    private static boolean smallerThan(String[] val){
        boolean result=false;
        double val1;
        double val2;
        Expression e = new ExpressionBuilder(val[0]).build();
                val1=e.evaluate();
                e = new ExpressionBuilder(val[1]).build();
                val2=e.evaluate();
        if (val1<val2) result=true;
     
        return result;
    }
    private static boolean smallerThanOrEqual(String[] val){
        boolean result=false;
        double val1;
        double val2;
        Expression e = new ExpressionBuilder(val[0]).build();
                val1=e.evaluate();
                e = new ExpressionBuilder(val[1]).build();
                val2=e.evaluate();
        if (val1<=val2) result=true;
     
        return result;
    }
    private static boolean largerThanOrEqual(String[] val){
        boolean result=false;
        double val1;
        double val2;
        Expression e = new ExpressionBuilder(val[0]).build();
                val1=e.evaluate();
                e = new ExpressionBuilder(val[1]).build();
                val2=e.evaluate();
        if (val1>=val2) result=true;
     
        return result;
    }
    private static boolean equal(String[] val){
        boolean result=false;
        double val1;
        double val2;
        Expression e = new ExpressionBuilder(val[0]).build();
                val1=e.evaluate();
                e = new ExpressionBuilder(val[1]).build();
                val2=e.evaluate();
        if (val1==val2) result=true;
     
        return result;
    }
    
}
