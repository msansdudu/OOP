package nsu.chebotareva;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void generatingDeckAndNames() {
        Deck.generatingDeck(1);
        String name = "Двойка Пики";
        int cost = 2;
        Cards cardActual = Deck.deck.get(0);
        assertEquals(cardActual.getName(), name);
        assertEquals(cardActual.getCost(), cost);

        name = "Пиковый Валет";
        cost = 10;
        cardActual = Deck.deck.get(36);
        assertEquals(cardActual.getName(), name);
        assertEquals(cardActual.getCost(), cost);
    }
}