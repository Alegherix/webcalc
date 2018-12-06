package calc;

import java.util.*;

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

    public void handleExpression(){
        /**
         * Om Det är en operator, Så utför ett antal checks och basera därefter
         * 1) Om det är en brackets som öppnar, Pusha de till stacken.
         * 2) Om det är en bracket som stänger, poppa då ut allt innanför bracketsen
         * 3) Om det är en Operator med lägre Precedence, poppa då ut den med högre precedence, gör sedan om
         * Checken, och poppa ut samtliga tills dess att det närliggande operatorn:
         *      - Inte finns
         *      - Är en Bracket
         *      - Har lägre precedence
         *
         */
        for (int i = 0; i < this.expr.size(); i++) {
            String toBeOperatedOn = this.expr.get(i);
            if(isOpeningOperator(toBeOperatedOn)){
                putOnStack(toBeOperatedOn);
            }
            else if(isClosingOperator(toBeOperatedOn)){
                // Plocka ut De operatorer som finns mellan (, och )
                // Placera dessa I listan
                // Poppa sedan ( && ) ur stacken.
            }
            else if(isOperator(toBeOperatedOn)){
                // Här kommer majoriteten av all logik att finnas.

            }
            else if(!isOperator(toBeOperatedOn) && !isClosingOperator(toBeOperatedOn) && isOpeningOperator(toBeOperatedOn)){
                // Måste vara ett nummer, placera i listan
                putInToList(toBeOperatedOn);
            }
        }
    }

    //Todo Beräkna vilka operatorer som finns mellan ( )


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

    public List<String> getExpr() {
        return expr;
    }

    public Stack<String> getStack() {
        return stack;
    }

    public static void main(String[] args) {
        List<String> testList = Arrays.asList("1","2","3");
        Postfix testObj = new Postfix(testList);
        testObj.handleExpression();
        testObj.getExpr().stream().forEach(System.out::print);
    }
}

