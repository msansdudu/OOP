package nsu.chebotareva;

/**
 * Класс вычитания.
 */
public class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Принимает два выражения.
     *
     * @param left -- уменьшаемое.
     * @param right -- вычитаемое.
     */
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
    public double eval(String expr) {
        return (double) left.eval(expr) - right.eval(expr);
    }
}
