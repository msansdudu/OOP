package nsu.chebotareva;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class ExpressionTest {

    @Test
    void print() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        String mustBe = "(3 + (2 * x))";
        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            e.print(); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    void realPrint1() {
        Expression e = new Div(new Number(3), new Mul(new Variable("y"), new Variable("x")));
        String res = e.realPrint();
        assert Objects.equals(res, "(3 / (y * x))");
    }

    @Test
    void realPrint2() {
        Expression e = new Div(new Add(new Number(3), new Variable("x")), new Sub(new Variable("y"), new Variable("x")));
        String res = e.realPrint();
        assert Objects.equals(res, "((3 + x) / (y - x))");
    }
}