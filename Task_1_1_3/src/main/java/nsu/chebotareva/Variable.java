package nsu.chebotareva;

import java.util.Objects;

/**
 * Класс переменных.
 */
public class Variable extends Expression {
    private final String var;

    /**
     * Принимает название переменной.
     *
     * @param var -- название типа String.
     */
    public Variable(String var) {
        this.var = var;
    }

    @Override
    public String realPrint() {
        return var;
    }

    @Override
    public Expression derivative(String a) {
        Expression res;
        if (Objects.equals(a, this.var)) {
            res = new Number(1);
        } else {
            res = new Number(0);
        }
        return res;
    }

    @Override
    public int eval(String expr) {
        String[] den = expr.split("; ");
        for (String a : den) {
            String[] nums = a.split(" = ");
            if (Objects.equals(nums[0], var)) {
                return Integer.parseInt(nums[1]);
            }
        }
        return 0;
    }
}
