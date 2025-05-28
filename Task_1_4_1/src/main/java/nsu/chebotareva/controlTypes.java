package nsu.chebotareva;

public class controlTypes {
    private final static String examReal = "Экзамен";
    private final static String creditReal = "Зачет";
    private final static String difCreditReal = "Дифференцируемый зачет";
    private final static String vkrReal = "Защита ВКР";
    private final static String colloquiumReal = "Коллоквиум";
    private final static String taskReal = "Задание";
    private final static String controlReal = "Контрольная";

    public static String control() {
        return controlReal;
    }

    public static String colloquium() {
        return colloquiumReal;
    }

    public static String credit() {
        return creditReal;
    }

    public static String difCredit() {
        return difCreditReal;
    }

    public static String exam() {
        return examReal;
    }

    public static String task() {
        return taskReal;
    }

    public static String vkr() {
        return vkrReal;
    }
}
