package nsu.chebotareva;

import java.util.Scanner;

public class Main {
    /**
     * Метод, проводящий один раунд игры.
     *
     * @param numberOfRound -- номер текущего раунда.
     * @param sc -- сканнер, поток ввода.
     * @return -- выиграл ли игрок? (1 - выиграл, 0 - проиграл, 3 - ничья).
     */
    public static int round(int numberOfRound, Scanner sc) {
        Person player = new Person();
        Person dealer = new Person();

        FormingCards.dealingCards(player, 2);
        FormingCards.dealingCards(dealer, 2);
        System.out.printf("Раунд %d%nДилер раздал карты%n", numberOfRound);
        player.printingCards(Boolean.FALSE, Boolean.FALSE);
        dealer.printingCards(Boolean.TRUE, Boolean.FALSE);
        // раздали начальные карты
        if (player.sumOfCosts == 21) {
            return 1;
        }

        System.out.println("\nВаш ход\n-------\nВведите “1”, чтобы взять карту, и “0”,"
                + " чтобы остановиться...");
        while (sc.nextInt() == 1) {
            player.newCardForPlayer(dealer);
            if (player.sumOfCosts == 21) {
                return 1;
            }
            if (player.sumOfCosts > 21) {
                return 0;
            }
            System.out.println("\nВаш ход\n-------\nВведите “1”, чтобы взять карту, и “0”, "
                    + "чтобы остановиться...");
        }
        // игрок набрал карты

        System.out.printf("%nХод дилера%n-------%nДилер открывает закрытую карту %s (%d)%n",
                dealer.cards[1], dealer.costs[1]);
        player.printingCards(Boolean.FALSE, Boolean.TRUE);
        dealer.printingCards(Boolean.TRUE, Boolean.TRUE);
        while (dealer.sumOfCosts < 17) {
            dealer.newCardForDealer(player);
            if (dealer.sumOfCosts > 21) {
                return 1;
            }
        }
        if (player.sumOfCosts > dealer.sumOfCosts) {
            return 1;
        }
        if (player.sumOfCosts == dealer.sumOfCosts) {
            return 3;
        }
        return 0;
    }

    /**
     * Метод, проводящий игру, пока игрок не захочет прекратить.
     */
    public static void game() {
        System.out.println("Добро пожаловать в Блэкджек!");
        int plScore = 0;
        int dlScore = 0;
        int round = 1;
        int win;
        Scanner sc = new Scanner(System.in);
        win = round(round, sc);
        if (win == 1) {
            plScore++;
            System.out.printf("%nВы выиграли раунд! Счет %d:%d в вашу пользу.%n",
                    plScore, dlScore);
        } else if (win == 0) {
            dlScore++;
            System.out.printf("%nВы проиграли раунд! Счет %d:%d в пользу дилера.%n",
                    plScore, dlScore);
        } else {
            plScore++;
            dlScore++;
            System.out.printf("Ничья! Счет %d:%d. Счет сравнялся!%n", plScore, dlScore);
        }
        System.out.println("\nЖелаете продолжить игру? Введите “1”, чтобы продолжить, и “0”, "
                + "чтобы прекратить.");
        while (sc.nextInt() == 1) {
            round++;
            win = round(round, sc);
            if (win == 1) {
                plScore++;
                System.out.printf("%nВы выиграли раунд! Счет %d:%d%s%n",plScore, dlScore,
                        plScore > dlScore ? " в вашу пользу." : (plScore == dlScore)
                                ? ". Счет сравнялся!" : " в пользу дилера.");
            } else if (win == 0) {
                dlScore++;
                System.out.printf("%nВы проиграли раунд! Счет %d:%d%s%n", plScore, dlScore,
                        plScore > dlScore ? " в вашу пользу." : (plScore == dlScore)
                                ? ". Счет сравнялся!" : " в пользу дилера.");
            } else {
                plScore++;
                dlScore++;
                System.out.printf("Ничья! Счет %d:%d%s%n", plScore, dlScore,
                        plScore > dlScore ? " в вашу пользу." : (plScore == dlScore)
                                ? ". Счет сравнялся!" : " в пользу дилера.");
            }
            System.out.println("\nЖелаете продолжить игру? Введите “1”"
                    + ", чтобы продолжить, и “0”, чтобы прекратить.");
        }
        sc.close();
        System.out.printf("\nИгра окончена!\nСчет %d:%d%s%n", plScore, dlScore,
                plScore > dlScore ? " в вашу пользу." :
                        (plScore == dlScore) ? ". Ничья!" : " в пользу дилера.");

    }

    /**
     * Main.
     *
     * @param args -- входные данные.
     */
    public static void main(String[] args) {
        game();
    }
}