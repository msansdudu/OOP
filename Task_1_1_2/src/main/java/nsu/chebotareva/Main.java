package nsu.chebotareva;

import java.util.Scanner;

/**
 * Main.
 */
public class Main {
    /**
     * Метод, проводящий один раунд игры.
     *
     * @param numberOfRound -- номер раунда.
     * @param sc            -- сканнер.
     * @param player        -- ссылка на игрока.
     * @param dealer        -- ссылка на дилера.
     * @return -- выигрыш игрока(1), проигрыш(0), ничья(3), конец колоды(4).
     */
    public static int round(int numberOfRound, Scanner sc, Person player, Person dealer) {
        if (player.dealingCards(2) == 0) {
            System.out.println("Колода кончилась!");
            return 4;
        }
        if (dealer.dealingCards(2) == 0) {
            System.out.println("Колода кончилась!");
            return 4;
        }
        dealer.isOpenDealer = Boolean.FALSE;
        System.out.printf("Раунд %d%nДилер раздал карты%n", numberOfRound);
        player.printingCards();
        dealer.printingCards();
        // раздали начальные карты
        if (player.sumOfCosts == 21) {
            player.score++;
            return 1;
        }

        System.out.println("\nВаш ход\n-------\nВведите “1”, чтобы взять карту, и “0”,"
                + "чтобы остановиться...");
        while (sc.nextInt() == 1) {
            if (player.dealingCards(1) == 0) {
                System.out.println("Колода кончилась!");
                return 4;
            }
            player.printingNewCards();
            player.printingCards();
            dealer.printingCards();
            if (player.sumOfCosts == 21) {
                player.score++;
                return 1;
            }
            if (player.sumOfCosts > 21) {
                dealer.score++;
                return 0;
            }
            System.out.println("\nВаш ход\n-------\nВведите “1”, чтобы взять карту, и “0”,"
                    + "чтобы остановиться...");
        }
        // игрок набрал карты

        System.out.printf("%nХод дилера%n-------%nДилер открывает закрытую карту %s (%d)%n",
                dealer.cards.get(1).name, dealer.cards.get(1).cost);
        dealer.isOpenDealer = Boolean.TRUE;
        player.printingCards();
        dealer.printingCards();
        while (dealer.sumOfCosts < 17) {
            if (dealer.dealingCards(2) == 0) {
                System.out.println("Колода кончилась!");
                return 4;
            }
            dealer.printingNewCards();
            player.printingCards();
            dealer.printingCards();
            if (dealer.sumOfCosts > 21) {
                player.score++;
                return 1;
            }
        }
        if (player.sumOfCosts > dealer.sumOfCosts) {
            player.score++;
        } else if (player.sumOfCosts == dealer.sumOfCosts) {
            player.score++;
            dealer.score++;
            return 3;
        }
        dealer.score++;
        return 0;
    }

    /**
     * Метод, проводящий игру, пока игрок не захочет прекратить.
     */
    public static void game() {
        Person player = new Person();
        Person dealer = new Person();
        player.isDealer = Boolean.FALSE;
        dealer.isDealer = Boolean.TRUE;

        Scanner sc = new Scanner(System.in);
        int n;

        System.out.println("Добро пожаловать в Блэкджек!\n");
        System.out.println("Сколько колод желаете использовать?");
        n = sc.nextInt();

        Deck.generatingDeck(n);
        Deck.shufflingDeck();

        int win;
        int rounds = 1;
        win = round(rounds, sc, player, dealer);
        if (win == 4) {
            endOfGame(player, dealer, sc);
            return;
        }
        endOfRound(player, dealer, win);

        while (sc.nextInt() == 1) {
            rounds++;
            player.cleaningCards();
            dealer.cleaningCards();
            win = round(rounds, sc, player, dealer);
            if (win == 4) {
                endOfGame(player, dealer, sc);
                return;
            }
            endOfRound(player, dealer, win);
        }
        endOfGame(player, dealer, sc);
    }

    /**
     * Выводит конец раунда.
     *
     * @param player -- ссылка на игрока.
     * @param dealer -- ссылка на дилера.
     * @param win    -- выиграл игрок(1), дилер(0), ничья(3).
     */
    public static void endOfRound(Person player, Person dealer, int win) {
        if (win == 1) {
            System.out.printf("%nВы выиграли раунд! Счет %d:%d%s%n",
                    player.score, dealer.score,
                    player.score > dealer.score ? " в вашу пользу." :
                            (player.score == dealer.score) ? ". Счет сравнялся!" : " в пользу дилера.");
        } else if (win == 0) {
            System.out.printf("%nВы проиграли раунд! Счет %d:%d%s%n",
                    player.score, dealer.score,
                    player.score > dealer.score ? " в вашу пользу." :
                            (player.score == dealer.score) ? ". Счет сравнялся!" : " в пользу дилера.");
        } else {
            System.out.printf("Ничья! Счет %d:%d%s%n", player.score, dealer.score,
                    player.score > dealer.score ? " в вашу пользу." :
                            (player.score == dealer.score) ? ". Счет сравнялся!" :
                                    " в пользу дилера.");
        }
        System.out.println("\nЖелаете продолжить игру? Введите “1”, чтобы продолжить, и “0”, "
                + "чтобы прекратить.");
    }

    /**
     * Выводит конец игры.
     *
     * @param player -- ссылка на игрока.
     * @param dealer -- ссылка на дилера.
     * @param sc     -- сканер.
     */
    public static void endOfGame(Person player, Person dealer, Scanner sc) {
        sc.close();
        System.out.printf("\nИгра окончена!\nСчет %d:%d%s%n", player.score, dealer.score,
                player.score > dealer.score ? " в вашу пользу." :
                        (player.score == dealer.score) ? ". Ничья!" : " в пользу дилера.");
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