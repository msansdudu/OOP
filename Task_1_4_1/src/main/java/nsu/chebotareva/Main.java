package nsu.chebotareva;

public class Main {
    public static void main(String[] args) {
        GradeBook book = new GradeBook(true);

        book.addRecord("Модели вычислений", controlTypes.exam(), 3, 5);
        book.addRecord("Цифровые платформы", controlTypes.exam(), 2, 4);
        book.addRecord("ООП", controlTypes.exam(), 2, 4);
        book.addRecord("Английский", controlTypes.credit(), 3, 1);
        book.addRecord("Философия", controlTypes.exam(), 4, 4);

        System.out.println(book);
    }
}