package nsu.chebotareva;

/**
 * Класс персонажа. Храним информацию о персонаже и работаем с его набором карт.
 */
public class Person {
    /**
     * Для конкретного Person имеем его набор карт, их соответствующие стоимости, их количество и сумму очков.
     */
    String[] cards = new String[21];
    int[] costs = new int[21];
    int amountOfCards = 0;
    int sumOfCosts = 0;

    /**
     * Метод, выводящий карты, которые сейчас на руках у заданного персонажа.
     * @param isDealer -- персонаж, чьи карты выводим -- дилер?
     * @param isOpenDealer -- открыта ли вторая карта у дилера?
     */
    public void printingCards (Boolean isDealer, Boolean isOpenDealer) {
        if (!isDealer) {
            System.out.print("\tВаши карты: [");
            for (int i = 0; i < amountOfCards - 1; i++){
                System.out.printf("%s (%d), ", cards[i], costs[i]);
            }
            System.out.printf("%s (%d)] => %d%n", cards[amountOfCards - 1], costs[amountOfCards - 1], sumOfCosts);
        } else {
            if (isOpenDealer) {
                System.out.print("\tКарты дилера: [");
                for (int i = 0; i < amountOfCards - 1; i++){
                    System.out.printf("%s (%d), ", cards[i], costs[i]);
                }
                System.out.printf("%s (%d)] => %d%n", cards[amountOfCards - 1], costs[amountOfCards - 1], sumOfCosts);
            } else {
                System.out.printf("\tКарты дилера: [%s (%d), <закрытая карта>]%n", cards[0], costs[0]);
            }
        }
    }

    /**
     * Выдаем карту игроку.
     * @param dealer -- ссылка на дилера.
     */
    public void newCardForPlayer (Person dealer) {
        FormingCards.dealingCards(this, 1);
        System.out.printf("Вы открыли карту %s (%d)%n", this.cards[this.amountOfCards - 1], this.costs[this.amountOfCards - 1]);
        printingCards(Boolean.FALSE, Boolean.FALSE);
        dealer.printingCards(Boolean.TRUE, Boolean.FALSE);
    }

    /**
     * Выдаем карту дилеру.
     * @param player -- ссылка на игрока.
     */
    public void newCardForDealer (Person player){
        FormingCards.dealingCards(this, 1);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%nДилер открывает карту %s (%d)%n", this.cards[this.amountOfCards - 1], this.costs[this.amountOfCards - 1]);
        player.printingCards(Boolean.FALSE, Boolean.FALSE);
        printingCards(Boolean.TRUE, Boolean.TRUE);
    }
}
