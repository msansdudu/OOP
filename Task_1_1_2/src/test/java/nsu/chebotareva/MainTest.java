package nsu.chebotareva;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    public void endOfRound1(){
        Person player = new Person();
        Person dealer = new Person();
        player.score = 2;
        dealer.score = 1;
        int win = 1;
        String mustBe = """
                Вы выиграли раунд! Счет 2:1 в вашу пользу.
                
                Желаете продолжить игру? Введите “1”, чтобы продолжить, и “0”, чтобы прекратить.""";
        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            Main.endOfRound(player, dealer, win); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    public void endOfRound2(){
        Person player = new Person();
        Person dealer = new Person();
        player.score = 1;
        dealer.score = 2;
        int win = 0;
        String mustBe = """
                Вы проиграли раунд! Счет 1:2 в пользу дилера.
                
                Желаете продолжить игру? Введите “1”, чтобы продолжить, и “0”, чтобы прекратить.""";
        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            Main.endOfRound(player, dealer, win); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    public void endOfRound3(){
        Person player = new Person();
        Person dealer = new Person();
        player.score = 3;
        dealer.score = 1;
        int win = 3;
        String mustBe = """
                Ничья! Счет 3:1 в вашу пользу.
                
                Желаете продолжить игру? Введите “1”, чтобы продолжить, и “0”, чтобы прекратить.""";
        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            Main.endOfRound(player, dealer, win); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    public void endOfGame1(){
        Person player = new Person();
        Person dealer = new Person();
        Scanner sc = new Scanner(System.in);
        player.score = 2;
        dealer.score = 1;
        String mustBe = """
                Игра окончена!
                Счет 2:1 в вашу пользу.""";
        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            Main.endOfGame(player, dealer, sc); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    public void endOfGame2(){
        Person player = new Person();
        Person dealer = new Person();
        Scanner sc = new Scanner(System.in);
        player.score = 3;
        dealer.score = 3;
        String mustBe = """
                Игра окончена!
                Счет 3:3. Ничья!""";
        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            Main.endOfGame(player, dealer, sc); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }
}