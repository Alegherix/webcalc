package calc;

import java.lang.reflect.Array;
import java.util.*;

public class Postfix {
    private List<String> expr;
    private Stack<String> stack;
    private List<String> listToAddTo;


    public Postfix(ArrayList<String> exprToEvaluate) {
        stack = new Stack<>();
        expr = exprToEvaluate;
        listToAddTo = new ArrayList<>();

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
                handleClosingParenthesis();
            }

            else if(isOperator(toBeOperatedOn)){
                stack.push(toBeOperatedOn);
                // Här kommer majoriteten av all logik att finnas.

            } else if (!isOperator(toBeOperatedOn) && !isClosingOperator(toBeOperatedOn) && !isOpeningOperator(toBeOperatedOn)) {
                putInToList(toBeOperatedOn);
            }
        }
    }

    //Todo Beräkna vilka operatorer som finns mellan ( )
    //
    public void handleClosingParenthesis() {
        while (!isOpeningOperator(stack.peek())) {
            listToAddTo.add(stack.pop());
        }
    }


    public boolean isClosingOperator(String operator){
        return ")".equals(operator);
    }

    public boolean isOpeningOperator(String operator){
        return "(".equals(operator);
    }

    public String popFromStack() {
        return stack.pop();
    }

    public void putOnStack(String op){
        stack.push(op);
    }

    public void putInToList(String character){
        listToAddTo.add(character);
    }

    public void printStack() {
        System.out.println("The current stack looks like: ");
        stack.stream().forEach(System.out::print);
        System.out.println(" ");
    }

    public void printList() {
        listToAddTo.stream().forEach(System.out::print);
    }

    public List<String> getExpr() {
        return expr;
    }

    public Stack<String> getStack() {
        return stack;
    }

    public static void main(String[] args) {

        ArrayList<String> testList = new ArrayList<>(Arrays.asList("1", "2", "3", "(", "-", "+", ")"));
        Postfix testObj = new Postfix(testList);
        testObj.handleExpression();
        //System.out.println("\n");
        testObj.printList();


    }
}

