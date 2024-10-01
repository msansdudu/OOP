package nsu.chebotareva;

/**
 * Класс деления.
 */
public class Div extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Принимает два выражения.
     *
     * @param left -- числитель.
     * @param right -- знаменатель.
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String realPrint() {
        String e = "(" + left.realPrint() + " / " + right.realPrint() + ")";
        return e;
    }

    @Override
    public Expression derivative(String a) {
        Expression derLeft = new Mul(left.derivative(a), right);
        Expression derRight = new Mul(left, right.derivative(a));
        Expression num = new Sub(derLeft, derRight);
        Expression den = new Mul(right, right);
        Expression res = new Div(num, den);
        return res;
    }

    @Override
    public int eval(String expr) {
        return left.eval(expr) / right.eval(expr);
    }
}