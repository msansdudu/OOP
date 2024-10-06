package nsu.chebotareva;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class DerivateTest {

    @Test
    void derivative1() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        Expression de = e.derivative("x");
        String res = de.realPrint();
        String mustBe = "(0 + ((0 * x) + (2 * 1)))";
        assert Objects.equals(res, mustBe);
    }

    @Test
    void derivative2() {
        Expression e = new Div(new Number(3), new Sub(new Number(2), new Variable("x")));
        Expression de = e.derivative("x");
        String res = de.realPrint();
        String mustBe = "(((0 * (2 - x)) - (3 * (0 - 1))) / ((2 - x) * (2 - x)))";
        assert Objects.equals(res, mustBe);
    }

    @Test
    void derivative3() {
        Expression e = new Mul(new Variable("x"), new Variable("y"));
        Expression de = e.derivative("x");
        String res = de.realPrint();
        String mustBe = "((1 * y) + (x * 0))";
        assert Objects.equals(res, mustBe);
    }
}