package nsu.chebotareva;

import java.io.*;
import java.util.Scanner;

/**
 * Абстрактный класс выражений.
 */
public abstract class Expression {
    /**
     * Печать выражений в обозначенном виде.
     */
    public void print(OutputStream outputStream) throws IOException {
        outputStream.write((this.realPrint() + "\n").getBytes());
    }

    /**
     * Выражение в нужном виде.
     *
     * @return -- выражение в виде строки.
     */
    abstract String realPrint();

    /**
     * Дифференцирование выражения по переменной a.
     *
     * @param a -- переменная, относительно которой дифференцируем.
     * @return -- дифференцированное выражение.
     */
    abstract Expression derivative(String a);

    /**
     * Вычисляет численное значение выражения.
     *
     * @param expr -- выражение, которое требуется вычислить.
     * @return -- полученное значение.
     */
    abstract double eval(String expr);

    /**
     * Создает выражение.
     *
     * @param inputStream -- поток ввода.
     * @return -- возвращает выражение.
     */
    public static Expression create(InputStream inputStream){
        Scanner sc = new Scanner(inputStream);
        String str = sc.nextLine();
        sc.close();
        Parser pars = new Parser(str);
        return pars.parse();
    }

    /**
     * Main-метод.
     *
     * @param args -- аргументы основного метода.
     */
    public static void main(String[] args) {
    }
}