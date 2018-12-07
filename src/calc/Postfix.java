package calc;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

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
         *      - Har samma precedence
         *      - Har högre precedence
         */

        for (int i = 0; i < this.expr.size(); i++) {
            String stringExpr = this.expr.get(i);

            if (isOpeningOperator(stringExpr)) {
                pushToStack(stringExpr);
            }
            else if (isClosingOperator(stringExpr)) {
                handleClosingParenthesis();
            }
            else if (isOperator(stringExpr)) {
                //Om tom, så kan vi alltid pusha till stacken
                //Annars jämför precedence, och agera därefter
                if (stack.isEmpty()) {
                    pushToStack(stringExpr);
                }
                else if (samePrecende(stringExpr)) {
                    //Same Precedence
                    // Osäker men rätt säker på att det räcker med att poppa stacken 1 gång
                    // Och därefter pusha nuvarande string Expr till stacken.

                }
                else if (lowerPrecedence(stringExpr)) {
                    // Om Strängen för nuvarande iteration har lägre precedence än den som ligger ytterst i stacken
                    // Då behöver vi poppa yttersta ifrån stacken rekursivt tills dess att detta expr har den högsta
                    // Precedence

                }
                else if (higerPrecedence(stringExpr)) {
                    //Om strängen för nuvarande iteration har högre precedence än den som för närvarande ligger
                    // ytterst i stacken så se bara till att pusha till stacken.
                    stack.push(stringExpr);
                }

            } else if (!isOperator(stringExpr) && !isClosingOperator(stringExpr) && !isOpeningOperator(stringExpr)) {
                //Här lägger vi till alla siffror till Listan
                addToList(stringExpr);
            }
        }
    }


    private boolean higerPrecedence(String newExprToCompareWith) {
        return getPrecedence(newExprToCompareWith) > getPrecedence(stack.peek());
    }

    public boolean samePrecende(String newExprToCompareWith) {
        return getPrecedence(newExprToCompareWith) == getPrecedence(stack.peek());
    }

    private boolean lowerPrecedence(String newExprToCompareWith) {
        return getPrecedence(newExprToCompareWith) < getPrecedence(stack.peek());
    }


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

    public void pushToStack(String op) {
        stack.push(op);
    }

    public void addToList(String character) {
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

        System.out.println();

    }
}

