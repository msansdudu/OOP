package nsu.chebotareva;

public class Person {
    String[] cards = new String[21];
    int[] costs = new int[21];
    int amountOfCards = 0;
    int sumOfCosts = 0;

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

    public void newCardForPlayer (Person dealer) {
        Cards.dealingCards(this, 1);
        System.out.printf("Вы открыли карту %s (%d)%n", this.cards[this.amountOfCards - 1], this.costs[this.amountOfCards - 1]);
        printingCards(Boolean.FALSE, Boolean.FALSE);
        dealer.printingCards(Boolean.TRUE, Boolean.FALSE);
    }

    public void newCardForDealer (Person player) {
        Cards.dealingCards(this, 1);
        System.out.printf("%nДилер открывает карту %s (%d)%n", this.cards[this.amountOfCards - 1], this.costs[this.amountOfCards - 1]);
        player.printingCards(Boolean.FALSE, Boolean.FALSE);
        printingCards(Boolean.TRUE, Boolean.TRUE);
    }
}
