package nsu.chebotareva;

/**
 * Абстрактный класс выражений.
 */
public abstract class Expression {
    /**
     * Печать выражений в обозначенном виде.
     */
    public void print() {
        System.out.println(this.realPrint());
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
    abstract int eval(String expr);

    /**
     * Main-метод.
     *
     * @param args -- аргументы основного метода.
     */
    public static void main(String[] args) {

    }
}