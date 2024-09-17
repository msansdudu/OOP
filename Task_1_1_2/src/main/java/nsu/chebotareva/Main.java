package nsu.chebotareva;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Person player = new Person();
        Person dealer = new Person();
        Boolean isOpenDealer = Boolean.FALSE; // карта дилера пока закрыта

        Cards.dealingCards (player, 2);
        Cards.dealingCards (dealer, 2);
        System.out.println("Добро пожаловать в Блэкджек!\nРаунд 1\nДилер раздал карты");
        player.printingCards(Boolean.FALSE, isOpenDealer);
        dealer.printingCards(Boolean.TRUE, isOpenDealer);
        // раздали начальные карты

        System.out.println("\nВаш ход\n-------\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
        Scanner sc = new Scanner(System.in);
        while (sc.nextInt() == 1) {
            player.newCardForPlayer(dealer);
            System.out.println("\nВаш ход\n-------\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
        }
        // игрок набрал карты


    }
}