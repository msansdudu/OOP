package nsu.chebotareva;

public abstract class Expression {
    public void print() {
        System.out.println(this.realPrint());
    }
    abstract String realPrint();

    abstract Expression derivative(String a);

    abstract int eval(String expr);

    public static void main(String[] args) {

    }
}