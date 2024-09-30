package nsu.chebotareva;

/**
 * Создает парсер для математических выражений.
 */
public class Parser {
    private final String str;
    private int curChar;
    private final int len;

    /**
     * Входные данные парсера.
     *
     * @param str1 -- строка для парсинга.
     */
    public Parser(String str1) {
        this.str = str1.replace(" ", "");
        this.curChar = 0;
        this.len = str.length();
    }

    /**
     * Запускает парсинг.
     *
     * @return -- возвращает полученное выражение.
     */
    public Expression parse() {
        return expr();
    }

    /**
     * Выделяет выражения в скобках.
     *
     * @return -- возвращает полученное выражение.
     */
    private Expression expr() {
        Expression e;
        Expression left;
        Expression right;
        if (str.charAt(curChar) == '(' && curChar < len - 1) {
            curChar++;
            left = expr();
            curChar++;
        } else {
            left = term();
        }

        if (curChar == len) {
            return left;
        }
        char op = str.charAt(curChar);
        curChar++;

        if (str.charAt(curChar) == '(' && curChar < len - 1) {
            curChar++;
            right = expr();
            curChar++;
        } else {
            right = term();
        }

        e = switch (op) {
            case ('+') -> new Add(left, right);
            case ('-') -> new Sub(left, right);
            case ('*') -> new Mul(left, right);
            case ('/') -> new Div(left, right);
            default -> {
                System.out.println("Error: couldn't identify operation " + op + "!");
                yield null;
            }
        };
        return e;
    }

    /**
     * Выделяет простейшие выражения -- числа и переменные.
     *
     * @return -- возвращает число либо переменную.
     */
    private Expression term() {
        Expression e;
        Boolean lz = Boolean.FALSE;
        if (str.charAt(curChar) == '-') {
            lz = Boolean.TRUE;
            curChar++;
        }
        if (Character.isDigit(str.charAt(curChar))) {
            int num = 0;
            while (Character.isDigit(str.charAt(curChar)) && curChar < len - 1) {
                num *= 10;
                num += Character.getNumericValue(str.charAt(curChar));
                curChar++;
            }
            if (lz) {
                num *= -1;
            }
            e = new Number(num);
        } else if (Character.isAlphabetic(str.charAt(curChar))) {
            StringBuilder var = new StringBuilder();
            while (Character.isAlphabetic(str.charAt(curChar)) && curChar < len - 1) {
                var.append(str.charAt(curChar));
                curChar++;
            }
            e = new Variable(var.toString());
        } else {
            System.out.println("Error: couldn't identify Variable or number!");
            e = null;
        }
        return e;
    }
}
