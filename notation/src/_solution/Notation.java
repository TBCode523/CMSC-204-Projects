package _solution;
/** A Utility Class that specializes in Converting Notations
 *
 * @author Talal Brek
 *
 *
 */
public class Notation {

    public static double evaluatePostfixExpression(String postfixExpr)
            throws InvalidNotationFormatException{
        double expression = 0;
        NotationStack<String> notationStack = new NotationStack<>(30);
        try{
        for(int i = 0; i < postfixExpr.length(); i++){

                if(Character.isDigit(postfixExpr.charAt(i))){
                    String num = "" + postfixExpr.charAt(i);
                    notationStack.push(num);
                }
                else if(isOperator(postfixExpr.charAt(i))){
                    String second = ""+ notationStack.pop();
                    String first = "" + notationStack.pop();
                    String exp = "";
                    if(postfixExpr.charAt(i) == '+') exp+= (Double.parseDouble(first) + Double.parseDouble(second));
                    else if(postfixExpr.charAt(i) == '-') exp+= (Double.parseDouble(first) - Double.parseDouble(second));
                    else if(postfixExpr.charAt(i) == '*') exp+= (Double.parseDouble(first) * Double.parseDouble(second));
                    else if(postfixExpr.charAt(i) == '/') exp+= (Double.parseDouble(first) / Double.parseDouble(second));
                    notationStack.push(exp);


                }

            }
        expression = Double.parseDouble(notationStack.pop());
        }catch (ArithmeticException |  StackOverflowException | StackUnderflowException e){
                throw new InvalidNotationFormatException();
            }

        return expression;


    }
    public static String convertPostfixToInfix(String postfix)
            throws InvalidNotationFormatException{
        NotationStack<String> notationStack = new NotationStack<>(30);
            String infix = "";
            try {
                for (int i = 0; i < postfix.length(); i++) {
                    if (Character.isLetterOrDigit(postfix.charAt(i))) {
                        String temp = new String(String.valueOf(postfix.charAt(i)));

                        notationStack.push(temp);
                    }
                    else if(isOperator(postfix.charAt(i))){
                        String str = notationStack.pop();
                        String str2 = notationStack.pop();
                         String expression ="("+ str2 + postfix.charAt(i) + str + ")";


                        notationStack.push(expression);
                    }
                }
                infix = notationStack.pop();
            }catch(StackOverflowException e ){
                throw new InvalidNotationFormatException();
            }catch(StackUnderflowException exception){
                throw new InvalidNotationFormatException();
            }

            return infix;

    }
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
        NotationStack<Character> notationStack = new NotationStack<>(30);
        String postfix = "";

        try {
        for(int i = 0; i < infix.length(); i++){

                if (Character.isLetterOrDigit(infix.charAt(i))) {

                    postfix += infix.charAt(i);

                }
                else if (isOperator(infix.charAt(i))) {
                    while (!notationStack.isEmpty() && !isOpenParenthesis(notationStack.top()) && hasHigherPrecep(notationStack.top(), infix.charAt(i))) {
                        postfix += notationStack.top();
                        notationStack.pop();
                    }
                    notationStack.push(infix.charAt(i));

                } else if (infix.charAt(i) == '(') {
                    notationStack.push(infix.charAt(i));
                } else if (infix.charAt(i) == ')') {
                    while (!notationStack.isEmpty() && !isOpenParenthesis(notationStack.top())) {
                        postfix += notationStack.top();
                        notationStack.pop();
                    }
                    notationStack.pop();
                }



        }

        while(!notationStack.isEmpty()){

            postfix += notationStack.top();

        }

        } catch (StackOverflowException e){
            throw new InvalidNotationFormatException();
        } catch (StackUnderflowException e) {
            throw  new InvalidNotationFormatException();
        }
        return postfix;
    }

    private static boolean isOperator(char charAt) {
        return charAt == '+' || charAt == '-' || charAt == '*' || charAt == '/';
    }

    private static boolean isOpenParenthesis(Character top) {
        return top == '(';
    }


    private static boolean hasHigherPrecep(Character top, char charAt) {
        return Prec(top) >= Prec(charAt);
    }

    static int Prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;


        }
        return -1;
    }
}
