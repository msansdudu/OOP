package nsu.chebotareva;

/**
 * Отдельная запись в книжке.
 */
public class RecordEntry {
    private final String subject;
    private final String controlType;
    private final int sem;
    private final int mark;

    /**
     * Конструктор записи.
     *
     * @param subject -- предмет.
     * @param controlType -- тип контроля.
     * @param sem -- номер семестра.
     * @param mark -- оценка.
     */
    public RecordEntry(String subject, String controlType, int sem, int mark) {
        this.subject = subject;
        this.controlType = controlType;
        this.sem = sem;
        this.mark = mark;
    }

    public String getSubject() {
        return subject;
    }

    public String getControlType() {
        return controlType;
    }

    public int getSem() {
        return sem;
    }

    public int getMark() {
        return mark;
    }
}
