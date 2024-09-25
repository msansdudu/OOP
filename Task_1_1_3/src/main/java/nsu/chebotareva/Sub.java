package nsu.chebotareva;

public class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String realPrint() {
        String e = "(" + left.realPrint() + " - " + right.realPrint() + ")";
        return e;
    }

    @Override
    public Expression derivative(String a) {
        Expression derLeft = left.derivative(a);
        Expression derRight = right.derivative(a);
        Expression res = new Sub(derLeft, derRight);
        return res;
    }

    @Override
    public int eval(String expr) {
        return left.eval(expr) - right.eval(expr);
    }
}
