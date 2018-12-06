package calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Postfix {
    private List<String> expr;
    private Stack<Character> stack;


    public Postfix(List<String> exprToEvaluate) {
        stack = new Stack();
        expr = exprToEvaluate;
    }

    public int getPrecedence(char op){
     if('+'==op || '-'==op){
         return 1;
     }
     else if('*'==op || '/'==op){
         return 2;
     }
     else if('^'==op){
         return 3;
     }
     else{
         return 0;
     }
    }

    public boolean isOperator(char op){
        switch (op){
            case '+':
                return true;
            case '-':
                return true;
            case '/':
                return true;
            case '*':
                return true;
            case '^':
                return true;
                default:
                    return false;
        }
    }

    public void putOnStack(char op){
        stack.push(op);
    }

    public void putInToList(String character){
        expr.add(character);
    }


    public static void main(String[] args) {
    }
}

