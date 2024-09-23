package nsu.chebotareva;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void generatingDeckAndNames() {
        Deck.generatingDeck(1);
        String name = "Двойка Пики";
        int cost = 2;
        Cards cardActual = Deck.deck.get(0);
        assertEquals(cardActual.name, name);
        assertEquals(cardActual.cost, cost);

        name = "Пиковый Валет";
        cost = 10;
        cardActual = Deck.deck.get(36);
        assertEquals(cardActual.name, name);
        assertEquals(cardActual.cost, cost);
    }
}