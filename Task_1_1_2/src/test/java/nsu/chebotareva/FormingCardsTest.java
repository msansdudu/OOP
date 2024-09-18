package nsu.chebotareva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FormingCardsTest {
    @Test
    void testingNames() {
        System.out.println("Testing names of cards...");
        String name, mustBe;
        name = FormingCards.nameOfCard(2, 3);
        mustBe = "Четверка Трефы";
        assertEquals(name, mustBe);

        name = FormingCards.nameOfCard(10, 0);
        mustBe = "Пиковая Дама";
        assertEquals(name, mustBe);

        name = FormingCards.nameOfCard(10, 2);
        mustBe = "Бубновая Дама";
        assertEquals(name, mustBe);

        name = FormingCards.nameOfCard(11, 3);
        mustBe = "Трефовый Король";
        assertEquals(name, mustBe);
    }

    @Test
    void testingDealingCards() {
        System.out.println("Testing replacing ace costs...");
        Person player = new Person();
        player.cards[0] = "Девятка Пики";
        player.costs[0] = 9;
        player.cards[1] = "Туз Трефы";
        player.costs[1] = 11;
        player.sumOfCosts = 20;
        player.amountOfCards = 2;
        FormingCards.dealingCards(player, 1);
        assertEquals(player.costs[1], 1);
    }
}