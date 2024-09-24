package nsu.chebotareva;

import java.util.ArrayList;
import java.util.List;

/**
 * Персонаж.
 */
public class Person {
    List<Cards> cards = new ArrayList<>();
    Boolean isDealer;
    Boolean isOpenDealer;
    int amountOfCards = 0;
    int sumOfCosts = 0;
    int score = 0;

    /**
     * Печатает карты на руках персонажа.
     */
    public void printingCards() {
        if (!isDealer) {
            System.out.print("\tВаши карты: [");
            for (int i = 0; i < amountOfCards - 1; i++) {
                System.out.printf("%s (%d), ", cards.get(i).name, cards.get(i).cost);
            }
            System.out.printf("%s (%d)] => %d%n", cards.get(amountOfCards - 1).name,
                    cards.get(amountOfCards - 1).cost, sumOfCosts);
        } else {
            if (isOpenDealer) {
                System.out.print("\tКарты дилера: [");
                for (int i = 0; i < amountOfCards - 1; i++) {
                    System.out.printf("%s (%d), ", cards.get(i).name, cards.get(i).cost);
                }
                System.out.printf("%s (%d)] => %d%n", cards.get(amountOfCards - 1).name,
                        cards.get(amountOfCards - 1).cost, sumOfCosts);
            } else {
                System.out.printf("\tКарты дилера: [%s (%d), <Закрытая карта>]%n",
                        cards.get(0).name, cards.get(0).cost);
            }
        }
    }

    /**
     * Печатает новую карту.
     */
    public void printingNewCards() {
        if (!isDealer) {
            System.out.printf("Вы открыли карту %s (%d)%n",
                    cards.get(this.amountOfCards - 1).name,
                    cards.get(this.amountOfCards - 1).cost);
        } else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("%nДилер открывает карту %s (%d)%n",
                    cards.get(this.amountOfCards - 1).name,
                    cards.get(this.amountOfCards - 1).cost);
        }
    }

    /**
     * Раздача новых карт.
     *
     * @param times -- сколько новых карт выдаем.
     * @return -- колода кончилась (0), колода не пуста (1).
     */
    public int dealingCards(int times) {
        for (int i = 0; i < times; i++) {
            if (Deck.deck.isEmpty()) {
                return 0;
            }
            cards.add(Deck.deck.get(0)); // выдали карты
            Deck.deck.remove(0);
            sumOfCosts += cards.get(amountOfCards).cost; // обновляем суммарную стоимость
            amountOfCards++;
            if (sumOfCosts > 21) { // если новая карта превысила стоимость
                for (int j = 0; j < amountOfCards; j++) {
                    if (cards.get(j).cost == 11) { // ищем туз и меняем его стоимость
                        sumOfCosts -= 10;
                        Cards tmp = new Cards();
                        tmp.cost = 1;
                        tmp.name = cards.get(j).name;
                        cards.set(j, tmp);
                    }
                }
            }
        }
        return 1;
    }

    /**
     * Удаление всех карт с рук.
     */
    public void cleaningCards() {
        if (amountOfCards > 0) {
            cards.subList(0, amountOfCards).clear();
        }
        amountOfCards = 0;
        sumOfCosts = 0;
    }
}
