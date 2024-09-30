package nsu.chebotareva;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parse() {
        Expression e = Expression.create("(((2 + x) / 3) * (Ax - (-4 * y)))");
        Expression mustBe = new Mul(new Div(new Add(new Number(2), new Variable("x")),
                new Number(3)), new Sub(new Variable("Ax"),
                new Mul(new Number(-4), new Variable("y"))));
        assertEquals(e.realPrint(), mustBe.realPrint());
    }
}