package nsu.chebotareva;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EvalTest {

    @Test
    void eval1() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        int res = e.eval("x = 10; y = 13");
        assertEquals(23, res);
    }

    @Test
    void eval2() {
        Expression e = new Sub(new Variable("y"), new Div(new Number(20), new Variable("x")));
        int res = e.eval("x = 10; y = 13");
        assertEquals(11, res);
    }
}