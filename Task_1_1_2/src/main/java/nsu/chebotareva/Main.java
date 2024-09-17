package nsu.chebotareva;

import java.util.Scanner;

public class Main {
    public static int round (int numberOfRound) {
        Person player = new Person();
        Person dealer = new Person();

        Cards.dealingCards (player, 2);
        Cards.dealingCards (dealer, 2);
        System.out.printf("Раунд %d%nДилер раздал карты%n", numberOfRound);
        player.printingCards(Boolean.FALSE, Boolean.FALSE);
        dealer.printingCards(Boolean.TRUE, Boolean.FALSE);
        // раздали начальные карты
        if (player.sumOfCosts == 21) {
            return 1;
        }

        System.out.println("\nВаш ход\n-------\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
        Scanner sc = new Scanner(System.in);
        while (sc.nextInt() == 1) {
            player.newCardForPlayer(dealer);
            if (player.sumOfCosts == 21) {
                sc.close();
                return 1;
            }
            if (player.sumOfCosts > 21) {
                sc.close();
                return 0;
            }
            System.out.println("\nВаш ход\n-------\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
        }
        // игрок набрал карты

        System.out.printf("%nХод дилера%n-------%nДилер открывает закрытую карту %s (%d)%n", dealer.cards[1], dealer.costs[1]);
        player.printingCards(Boolean.FALSE, Boolean.TRUE);
        dealer.printingCards(Boolean.TRUE, Boolean.TRUE);
        while (dealer.sumOfCosts < 17) {
            dealer.newCardForDealer(player);
            if (dealer.sumOfCosts > 21) {
                sc.close();
                return 1;
            }
        }
        if (player.sumOfCosts > dealer.sumOfCosts) {
            sc.close();
            return 1;
        }
        if (player.sumOfCosts == dealer.sumOfCosts) {
            sc.close();
            return 3;
        }
        sc.close();
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Блэкджек!");
        int plScore = 0, dlScore = 0;
        int win;
        win = round(1);
        if (win == 1) {
            plScore++;
            System.out.printf("%nВы выиграли раунд! Счет %d:%d%s.%n", plScore, dlScore, plScore > dlScore ? " в вашу пользу" : (plScore == dlScore) ? ". Счет сравнялся!" : " в пользу дилера");
        }
        else if (win == 0) {
            dlScore++;
            System.out.printf("%nВы проиграли раунд! Счет %d:%d%s.%n", plScore, dlScore, plScore > dlScore ? " в вашу пользу" : (plScore == dlScore) ? ". Счет сравнялся!" : " в пользу дилера");
        }
        else {
            System.out.printf("Ничья! Счет %d:%d%s.%n", plScore, dlScore, plScore > dlScore ? " в вашу пользу" : (plScore == dlScore) ? ". Счет сравнялся!" : " в пользу дилера");
        }
        System.out.println("Желаете продолжить игру? Введите “1”, чтобы продолжить, и “0”, чтобы прекратить игру.");


    }
}