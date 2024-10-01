package nsu.chebotareva;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
     * Создает выражение, читая файл.
     *
     * @param file -- ссылка на файл.
     * @return -- возвращает выражение.
     */
    public static Expression create(String file) {
        return realCreate(file, Boolean.TRUE, null);
    }

    /**
     * Создает выражение, читая консоль.
     *
     * @param sc -- ссылка на сканер.
     * @return -- возвращает выражение.
     */
    public static Expression create(Scanner sc) {
        return realCreate("", Boolean.FALSE, sc);
    }

    /**
     * Создает выражение.
     *
     * @param file -- ссылка на файл.
     * @param isFile -- мы читаем из файла?
     * @param sc -- ссылка на сканер.
     * @return -- возвращает выражение.
     */
    public static Expression realCreate(String file, Boolean isFile, Scanner sc) {
        if (isFile) {
            try {
                sc = new Scanner(new File(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
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