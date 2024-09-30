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

    public static Expression create(String str) {
        Parser pars = new Parser(str);
        return pars.parse();
    }

    /**
     * Main-метод.
     *
     * @param args -- аргументы основного метода.
     */
    public static void main(String[] args) {
        Expression e = create("(((2 + x) / 3) * (Ax - (-4 * y)))");
        e.print();
        int res = e.eval("x = 4; Ax = 2; y = 1");
        System.out.println(res);
        Expression de = e.derivative("x");
        de.print();
    }
}