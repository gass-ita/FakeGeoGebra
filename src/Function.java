
public class Function {

    private String f;
    private boolean isVisible;
    private boolean isPolyLine;

    public Function(String f, boolean isPolyLine) {
        setF(f);
        this.isVisible = true;
        this.isPolyLine = isPolyLine;
    }

    public double f(double x) {
        // replace the "x" in f with the value of x
        f = f.replace("E", Double.toString(Math.E));
        f = f.replace("PI", Double.toString(Math.PI));
        String f_x = f.replace("x", Double.toString(x));
        // evaluate the function
        return eval(f_x);

    }

    public String getF() {
        return f;
    }

    public void setF(String f) throws RuntimeException {
        try {
            eval(f.replace('x', '0'));
            this.f = f;
        } catch (RuntimeException e) {
            System.out.println("Invalid function");
            throw e;
        }

    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isPolyLine() {
        return isPolyLine;
    }

    public void setPolyLine(boolean isPolyLine) {
        this.isPolyLine = isPolyLine;
    }

    public static double eval(final String str) throws RuntimeException {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ')
                    nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length())
                    throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            // | functionName `(` expression `)` | functionName factor
            // | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+'))
                        x += parseTerm(); // addition
                    else if (eat('-'))
                        x -= parseTerm(); // subtraction
                    else
                        return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*'))
                        x *= parseFactor(); // multiplication
                    else if (eat('/'))
                        x /= parseFactor(); // division
                    else
                        return x;
                }
            }

            double parseFactor() {
                if (eat('+'))
                    return +parseFactor(); // unary plus
                if (eat('-'))
                    return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')'))
                        throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.')
                        nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z')
                        nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')'))
                            throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt"))
                        x = Math.sqrt(x);
                    else if (func.equals("sin"))
                        x = Math.sin(x);
                    else if (func.equals("cos"))
                        x = Math.cos(x);
                    else if (func.equals("tan"))
                        x = Math.tan(x);
                    else if (func.equals("atan"))
                        x = Math.atan(x);
                    else if (func.equals("asin"))
                        x = Math.asin(x);
                    else if (func.equals("acos"))
                        x = Math.acos(x);
                    else if (func.equals("abs"))
                        x = Math.abs(x);
                    else if (func.equals("log"))
                        x = Math.log(x);
                    else if (func.equals("ln"))
                        x = Math.log(x);
                    else if (func.equals("exp"))
                        x = Math.exp(x);
                    else if (func.equals("sinh"))
                        x = Math.sinh(x);
                    else if (func.equals("cosh"))
                        x = Math.cosh(x);
                    else if (func.equals("tanh"))
                        x = Math.tanh(x);
                    else if (func.equals("floor"))
                        x = Math.floor(x);
                    else if (func.equals("round"))
                        x = Math.round(x);
                    else if (func.equals("ceil"))
                        x = Math.ceil(x);
                    else if (func.equals("fact"))
                        x = factorial(x);
                    else
                        throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^'))
                    x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

   

    public static double factorial(double x) {
        x = x + 1;
        double[] p = { 0.99999999999980993, 676.5203681218851, -1259.1392167224028,
                771.32342877765313, -176.61502916214059, 12.507343278686905,
                -0.13857109526572012, 9.9843695780195716e-6, 1.5056327351493116e-7 };
        int g = 7;
        if (x < 0.5)
            return Math.PI / (Math.sin(Math.PI * x) * factorial(1 - x));
        x -= 1;
        double a = p[0];
        double t = x + g + 0.5;
        for (int i = 1; i < p.length; i++) {
            a += p[i] / (x + i);
        }

        return Math.sqrt(2 * Math.PI) * Math.pow(t, x + 0.5) * Math.exp(-t) * a;
    }
}
