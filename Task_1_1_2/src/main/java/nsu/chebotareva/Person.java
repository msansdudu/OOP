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
                System.out.printf("%s (%d), ", cards.get(i).getName(), cards.get(i).getCost());
            }
            System.out.printf("%s (%d)] => %d%n", cards.get(amountOfCards - 1).getName(),
                    cards.get(amountOfCards - 1).getCost(), sumOfCosts);
        } else {
            if (isOpenDealer) {
                System.out.print("\tКарты дилера: [");
                for (int i = 0; i < amountOfCards - 1; i++) {
                    System.out.printf("%s (%d), ", cards.get(i).getName(), cards.get(i).getCost());
                }
                System.out.printf("%s (%d)] => %d%n", cards.get(amountOfCards - 1).getName(),
                        cards.get(amountOfCards - 1).getCost(), sumOfCosts);
            } else {
                System.out.printf("\tКарты дилера: [%s (%d), <Закрытая карта>]%n",
                        cards.get(0).getName(), cards.get(0).getCost());
            }
        }
    }

    /**
     * Печатает новую карту.
     */
    public void printingNewCards() {
        if (!isDealer) {
            System.out.printf("Вы открыли карту %s (%d)%n",
                    cards.get(this.amountOfCards - 1).getName(),
                    cards.get(this.amountOfCards - 1).getCost());
        } else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("%nДилер открывает карту %s (%d)%n",
                    cards.get(this.amountOfCards - 1).getName(),
                    cards.get(this.amountOfCards - 1).getCost());
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
            sumOfCosts += cards.get(amountOfCards).getCost(); // обновляем суммарную стоимость
            amountOfCards++;
            if (sumOfCosts > 21) { // если новая карта превысила стоимость
                for (int j = 0; j < amountOfCards; j++) {
                    if (cards.get(j).getCost() == 11) { // ищем туз и меняем его стоимость
                        sumOfCosts -= 10;
                        Cards tmp = new Cards(cards.get(j).getName(), 1);
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
