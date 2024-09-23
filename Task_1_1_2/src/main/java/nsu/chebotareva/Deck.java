package nsu.chebotareva;

import java.util.*;

public class Deck {
    static String[] namesOfCards = {"Двойка", "Тройка", "Четверка", "Пятерка", "Шестерка", "Семерка",
            "Восьмерка", "Девятка", "Десятка", "Валет", "Дама", "Король", "Туз"};
    static String[] suits = {"Пики", "Червы", "Бубны", "Трефы"};
    static int[] costs = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

    static List<Cards> deck = new ArrayList<>();

    public static void generatingDeck(int n){
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 4; j++) {
                    Cards newCard = new Cards();
                    newCard.name = generatingName(i, j);
                    newCard.cost = costs[i];
                    deck.add(newCard);
                }
            }
        }
    }

    public static void shufflingDeck() {
        Collections.shuffle(deck);
    }

    private static String generatingName(int numCard, int numSuit) {
        String name;
        if (numCard < 9 || numCard == 12) {
            name = namesOfCards[numCard] + " " + suits[numSuit];
            // если цифра или туз, то имя вида "семерка червы"
        } else {
            if (Objects.equals(namesOfCards[numCard], "Дама")) {
                if (numSuit == 0) {
                    name = suits[numSuit].substring(0, 3) + "овая Дама";
                } else {
                    name = suits[numSuit].substring(0, 4) + "овая Дама";
                }
            } else {
                if (numSuit == 0) {
                    name = suits[numSuit].substring(0, 3) + "овый " + namesOfCards[numCard];
                } else {
                    name = suits[numSuit].substring(0, 4) + "овый " + namesOfCards[numCard];
                }
            }
        }
        return name;
    }
}
