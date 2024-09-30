package nsu.chebotareva;

/**
 * Класс чисел.
 */
public class Number extends Expression {
    private final int num;

    /**
     * Принимает число.
     *
     * @param num -- число.
     */
    public Number(int num) {
        this.num = num;
    }

    @Override
    public String realPrint() {
        return String.valueOf(num);
    }

    @Override
    public Expression derivative(String a) {
        Expression res = new Number(0);
        return res;
    }

    @Override
    public int eval(String expr) {
        return num;
    }
}