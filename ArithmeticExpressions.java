package JavaBasic;

import java.util.Scanner;

public class ArithmeticExpressions {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read variables from user
        System.out.print("Enter variable name for x: ");
        String xName = scanner.next();
        System.out.print("Enter value for " + xName + ": ");
        int xVal = scanner.nextInt();

        System.out.print("Enter variable name for y: ");
        String yName = scanner.next();
        System.out.print("Enter value for " + yName + ": ");
        int yVal = scanner.nextInt();

        Variable x = Expressions.var(xName, xVal);
        Variable y = Expressions.var(yName, yVal);

        // Build expression: (x + (-3 * (10 - y)) + (7 / 2))
        Expression expr = Expressions.sum(
                x,
                Expressions.product(
                        Expressions.val(-3),
                        Expressions.difference(Expressions.val(10), y)
                ),
                Expressions.fraction(Expressions.val(7), Expressions.val(2))
        );

        System.out.println("Expression: " + expr.toExpressionString());
        System.out.println("Evaluated: " + expr.evaluate());

        // Optional: allow user to update x
        System.out.print("Enter new value for " + xName + ": ");
        x.setValue(scanner.nextInt());
        System.out.println("Updated Expression: " + expr.toExpressionString());
        System.out.println("Re-evaluated: " + expr.evaluate());

        scanner.close();
    }

    // Expression Interface
    public interface Expression {
        int evaluate();
        String toExpressionString();
    }

    // Variable class
    public static class Variable implements Expression {
        private final String name;
        private int value;

        public Variable(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public int evaluate() {
            return value;
        }

        @Override
        public String toExpressionString() {
            return name;
        }
    }

    // DSL builder
    public static class Expressions {
        public static Variable var(String name, int value) {
            return new Variable(name, value);
        }

        public static Expression val(int value) {
            return new Expression() {
                @Override
                public int evaluate() {
                    return value;
                }

                @Override
                public String toExpressionString() {
                    return value < 0 ? "(" + value + ")" : String.valueOf(value);
                }
            };
        }

        public static Expression sum(Expression... members) {
            return new Expression() {
                @Override
                public int evaluate() {
                    int sum = 0;
                    for (Expression e : members) sum += e.evaluate();
                    return sum;
                }

                @Override
                public String toExpressionString() {
                    StringBuilder sb = new StringBuilder("(");
                    for (int i = 0; i < members.length; i++) {
                        if (i > 0) sb.append(" + ");
                        sb.append(members[i].toExpressionString());
                    }
                    sb.append(")");
                    return sb.toString();
                }
            };
        }

        public static Expression product(Expression... members) {
            return new Expression() {
                @Override
                public int evaluate() {
                    int product = 1;
                    for (Expression e : members) product *= e.evaluate();
                    return product;
                }

                @Override
                public String toExpressionString() {
                    StringBuilder sb = new StringBuilder("(");
                    for (int i = 0; i < members.length; i++) {
                        if (i > 0) sb.append(" * ");
                        sb.append(members[i].toExpressionString());
                    }
                    sb.append(")");
                    return sb.toString();
                }
            };
        }

        public static Expression difference(Expression minuend, Expression subtrahend) {
            return new Expression() {
                @Override
                public int evaluate() {
                    return minuend.evaluate() - subtrahend.evaluate();
                }

                @Override
                public String toExpressionString() {
                    return "(" + minuend.toExpressionString() + " - " + subtrahend.toExpressionString() + ")";
                }
            };
        }

        public static Expression fraction(Expression dividend, Expression divisor) {
            return new Expression() {
                @Override
                public int evaluate() {
                    return dividend.evaluate() / divisor.evaluate(); // integer division
                }

                @Override
                public String toExpressionString() {
                    return "(" + dividend.toExpressionString() + " / " + divisor.toExpressionString() + ")";
                }
            };
        }
    }
}
