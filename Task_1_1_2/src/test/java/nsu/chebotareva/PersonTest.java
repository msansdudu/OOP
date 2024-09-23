package nsu.chebotareva;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class PersonTest {

    @Test
    void dealingCards() {
        Person player = new Person();
        Deck.generatingDeck(1);
        player.dealingCards(1);
        Cards card = player.cards.get(0);
        String name = "Двойка Пики";
        int cost = 2;
        assertEquals(card.name, name);
        assertEquals(card.cost, cost);
        assertEquals(player.amountOfCards, 1);
    }

    @Test
    void cleaningCards() {
        Person player = new Person();
        Deck.generatingDeck(1);
        player.dealingCards(3);
        player.cleaningCards();
        assert(player.cards.isEmpty());
    }

    @Test
    void printingPlayerCards() {
        Person player = new Person();
        player.isDealer = Boolean.FALSE;
        Deck.generatingDeck(1);
        player.dealingCards(3);
        String mustBe = "Ваши карты: [Двойка Пики (2), Двойка Червы (2), Двойка Бубны (2)] => 6";

        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            player.printingCards(); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    void printingClosedDealerCards() {
        Person player = new Person();
        player.isDealer = Boolean.TRUE;
        player.isOpenDealer = Boolean.FALSE;
        Deck.generatingDeck(1);
        player.dealingCards(2);
        String mustBe = "Карты дилера: [Двойка Пики (2), <Закрытая карта>]";

        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            player.printingCards(); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    void printingOpenedDealerCards() {
        Person player = new Person();
        player.isDealer = Boolean.TRUE;
        player.isOpenDealer = Boolean.TRUE;
        Deck.generatingDeck(1);
        player.dealingCards(2);
        String mustBe = "Карты дилера: [Двойка Пики (2), Двойка Червы (2)] => 4";

        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            player.printingCards(); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    void printingNewPlayerCards() {
        Person player = new Person();
        player.isDealer = Boolean.FALSE;
        Deck.generatingDeck(1);
        player.dealingCards(1);
        String mustBe = "Вы открыли карту Двойка Пики (2)";

        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            player.printingNewCards(); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    void printingNewDealerCards() {
        Person player = new Person();
        player.isDealer = Boolean.TRUE;
        Deck.generatingDeck(1);
        player.dealingCards(1);
        String mustBe = "Дилер открывает карту Двойка Пики (2)";

        // Создаем поток для захвата вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // Сохраняем оригинальный поток
        PrintStream originalOut = System.out;
        System.setOut(printStream); // Перенаправляем System.out

        try {
            player.printingNewCards(); // Получаем вывод
            String output = outputStream.toString().trim(); // Удаляем лишние пробелы и переносы
            assertEquals(mustBe, output); // Проверяем, что вывод соответствует ожидаемому
        } finally {
            System.setOut(originalOut); // Восстанавливаем оригинальный поток
        }
    }

    @Test
    void checkingAces() {
        Person player = new Person();
        player.isDealer = Boolean.FALSE;
        Cards ace = new Cards();
        Cards card = new Cards();
        ace.name = "Туз Пики";
        card.name = "Девятка Пики";
        ace.cost = 11;
        card.cost = 9;
        player.cards.add(ace);
        player.cards.add(card);
        player.amountOfCards = 2;
        player.sumOfCosts = 20;
        Deck.generatingDeck(1);
        player.dealingCards(1);
        assertEquals(1, player.cards.get(0).cost);
    }
}