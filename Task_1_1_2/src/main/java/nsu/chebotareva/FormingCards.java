package nsu.chebotareva;

import java.util.Objects;

/**
 * Класс формируем имена карт и их раздачу.
 */
public class FormingCards {
    /**
     * Создаем три массива.
     * deck (значение карты), suits (масти карт), cost (соответствующие стоимости к deck).
     */
    static String[] deck = {"Двойка", "Тройка", "Четверка", "Пятерка", "Шестерка", "Семерка",
            "Восьмерка", "Девятка", "Десятка", "Валет", "Дама", "Король", "Туз"};
    static String[] suits = {"Пики", "Червы", "Бубны", "Трефы"};
    static int[] cost = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

    /**
     * Метод, генерирующий правильное имя карты (порядок масть-значение и род).
     *
     * @param numCard -- номер карты в массиве deck.
     * @param numSuit -- номер масти в массиве suits.
     * @return -- возвращает имя карты.
     */
    public static String nameOfCard(int numCard, int numSuit) {
        String name;
        if (numCard < 9 || numCard == 12) {
            name = deck[numCard] + " " + suits[numSuit];
            // если цифра или туз, то имя вида "семерка червы"
        } else {
            if (Objects.equals(deck[numCard], "Дама")) {
                if (numSuit == 0) {
                    name = suits[numSuit].substring(0, 3) + "овая Дама";
                } else {
                    name = suits[numSuit].substring(0, 4) + "овая Дама";
                }
            } else {
                if (numSuit == 0) {
                    name = suits[numSuit].substring(0, 3) + "овый " + deck[numCard];
                } else {
                    name = suits[numSuit].substring(0, 4) + "овый " + deck[numCard];
                }
            }
        }
        return name;
    }

    /**
     * Раздача карт рандомным образом.
     *
     * @param person -- кому выдаем карты (игроку или дилеру)?
     * @param times -- сколько карт выдаем?
     */
    public static void dealingCards(Person person, int times) {
        for (int i = 0; i < times; i++) {
            int numCard = (int) (Math.random() * 13);
            int numSuit = (int) (Math.random() * 4);
            person.cards[person.amountOfCards] = nameOfCard(numCard, numSuit); // выдали карты
            person.costs[person.amountOfCards] = cost[numCard];
            person.amountOfCards++;
            person.sumOfCosts += cost[numCard]; // проверяем суммарную стоимость
            if (person.sumOfCosts > 21) { // если новая карта превысила стоимость
                for (int j = 0; j < person.amountOfCards; j++) {
                    if (person.costs[j] == 11) { // ищем туз и меняем его стоимость
                        person.sumOfCosts -= 10;
                        person.costs[j] = 1;
                    }
                }
            }
        }
    }
}
