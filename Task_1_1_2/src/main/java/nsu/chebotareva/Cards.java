package nsu.chebotareva;

/**
 * Одна карта -- ее имя и стоимость.
 */
public class Cards {
    private final String name;
    private final int cost;

    public Cards(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}
