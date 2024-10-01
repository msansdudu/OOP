package nsu.chebotareva;

import static nsu.chebotareva.Expression.create;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    void parseFromFile() {
        try {
            FileWriter writer = new FileWriter("file.txt");
            writer.write("(((2 + x) / 3) * (Ax - (-4 * y)))");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Expression e = Expression.create("file.txt");
        Expression mustBe = new Mul(new Div(new Add(new Number(2), new Variable("x")),
                new Number(3)), new Sub(new Variable("Ax"),
                new Mul(new Number(-4), new Variable("y"))));
        assertEquals(e.realPrint(), mustBe.realPrint());
    }

    @Test
    void parseFromInput() {
        Scanner sc = new Scanner("(((2 + x) / 3) * (Ax - (-4 * y)))");
        Expression e = create(sc);
        sc.close();
        Expression mustBe = new Mul(new Div(new Add(new Number(2), new Variable("x")),
                new Number(3)), new Sub(new Variable("Ax"),
                new Mul(new Number(-4), new Variable("y"))));
        assertEquals(e.realPrint(), mustBe.realPrint());
    }
}