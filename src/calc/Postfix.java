package calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Postfix {
    private List<String> expr;
    private Stack<String> stack;


    public Postfix(List<String> exprToEvaluate) {
        stack = new Stack<>();
        expr = exprToEvaluate;
    }

    public int getPrecedence(String op){
        switch (op){
            case "+":
                return 1;
            case "-":
                return 1;
            case "/":
                return 2;
            case "*":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
         }
    }

    public boolean isOperator(String op){
        switch (op){
            case "+":
                return true;
            case "-":
                return true;
            case "/":
                return true;
            case "*":
                return true;
            case "^":
                return true;
                default:
                    return false;
        }
    }

    public void handleExpression(List<String> inputList){
        /**
         * Om Det är en operator, Så utför ett antal checks och basera därefter
         * 1) Om det är en bracket som stänger, poppa då ut allt innanför bracketsen
         * 2) Om det är en Operator med lägre Precedence, poppa då ut den med högre precedence, gör sedan om
         * Checken, och poppa ut samtliga tills dess att det närliggande operatorn:
         *      - Inte finns
         *      - Är en Bracket
         *      - Har lägre precedence
         *
         */
        for (int i = 0; i < inputList.size(); i++) {
            String toBeOperatedOn = inputList.get(i);

            if(isOperator(toBeOperatedOn)){

            }
        }
    }

    public boolean isClosingOperator(String operator){
        return "(".equals(operator);
    }

    public boolean isOpeningOperator(String operator){
        return ")".equals(operator);
    }

    public void putOnStack(String op){
        stack.push(op);
    }

    public void putInToList(String character){
        expr.add(character);
    }


    public static void main(String[] args) {
    }
}

