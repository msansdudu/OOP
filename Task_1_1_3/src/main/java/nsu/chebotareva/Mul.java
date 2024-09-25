package nsu.chebotareva;

public class Mul extends Expression {
    private final Expression left;
    private final Expression right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String realPrint() {
        String e = "(" + left.realPrint() + " * " + right.realPrint() + ")";
        return e;
    }

    @Override
    public Expression derivative(String a) {
        Expression derLeft = new Mul(left.derivative(a), right);
        Expression derRight = new Mul(left, right.derivative(a));
        Expression res = new Add(derLeft, derRight);
        return res;
    }

    @Override
    public int eval(String expr) {
        return left.eval(expr) * right.eval(expr);
    }
}
