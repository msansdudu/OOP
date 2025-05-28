package nsu.chebotareva;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Класс GradeBook моделирует зачётную книжку студента
 */
public class GradeBook {
    private final List<RecordEntry> records;
    private final boolean budgetaryBasis;

    /**
     * Создание зачетной книжки.
     *
     * @param budgetaryBasis - true, если обучение на бюджете.
     */
    public GradeBook(boolean budgetaryBasis) {
        this.records = new ArrayList<>();
        this.budgetaryBasis = budgetaryBasis;

    }

    /**
     * Добавление оценки в зачетную книжку.
     */
    public void addRecord(String subject, String controlType, int sem, int mark) {
        if (sem < 1 || sem > 8) {
            throw new IllegalArgumentException("Семестров всего 8, от первого до восьмого.");
        }
        if (Objects.equals(controlType, "Зачет")) {
            if (mark < 0 || mark > 1) {
                throw new IllegalArgumentException("Зачет может быть либо сдан (1), либо не сдан (0)");
            }
        } else if (mark < 2 || mark > 5) {
            throw new IllegalArgumentException("Оценка может быть 2, 3, 4, либо 5");
        }
        records.add(new RecordEntry(subject, controlType, sem, mark));
    }

    /**
     * Средний балл за весь период обучения.
     *
     * @return -- Средний балл.
     */
    public double AverageMark() {
        if (records.isEmpty()) {
            return 0;
        }
        double sum = records.stream().filter(record -> !Objects.equals(record.getControlType(), "Зачет"))
                .mapToInt(RecordEntry::getMark).sum();
        return sum / records.size();
    }

    /**
     * Текущий семестр.
     */
    private int getCurrentSemester() {
        return records.stream().mapToInt(RecordEntry::getSem).max().orElse(1);
    }

    /**
     * Возможность перевода на бюджет.
     */
    public String checkBudgetAbility() {
        if (budgetaryBasis) {
            return "Студент уже учится на бюджетной основе.";
        }

        int curSem = getCurrentSemester();
        if (curSem <= 1) {
            return "На данном этапе обучения перевестись невозможно.";
        }
        boolean ability = true;
        for (RecordEntry entry : records) {
            if (entry.getSem() == curSem || entry.getSem() == curSem - 1) {
                if (entry.getControlType().equals("Экзамен") && entry.getMark() < 4) {
                    ability = false;
                } else if (entry.getMark() < 3) {
                    ability = false;
                }
            }
        }
        return ability ? "Студент может перевестись на бюджет." : "Студент может не перевестись на бюджет.";
    }

    /**
     * Проверяет возможность получения диплома с отличием.
     *
     */
    public boolean honorDiplomaAbility() {
        boolean noBadMarks = records.stream().allMatch(record -> record.getMark() >= 4);

        // Подсчитываем количество отличных оценок (оценка 5)
        long amountOfExcellentMarks = records.stream().filter(record -> record.getMark() == 5).count();

        // Проверяем, есть ли ВКР с оценкой 5
        boolean excellentVKR = records.stream()
                .anyMatch(record -> record.getControlType().equals("Защита ВКР") && record.getMark() == 5) ||
                records.stream().noneMatch(record -> record.getControlType().equals("Защита ВКР"));

        // Студент должен иметь все оценки не ниже 4, не менее 75% оценок 5 и ВКР с оценкой 5
        return noBadMarks &&
                amountOfExcellentMarks >= records.size() * 0.75 &&
                excellentVKR;
    }

    /**
     * Возможность повышенной стипендии.
     */
    public boolean increasedScholarshipAbility() {
        int curSem = getCurrentSemester();
        return records.stream()
                .filter(record -> record.getSem() == curSem)
                .allMatch(record -> record.getMark() == 5);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Зачетная книжка:\n\n");
        builder.append(String.format("%-20s | %-22s | %-7s | %-6s |%n", "Предмет", "Тип контроля", "Семестр", "Оценка"));
        builder.append("-".repeat(66));
        builder.append("\n");
        records.stream().sorted(Comparator.comparing(RecordEntry::getSem)).forEach(
                record -> builder.append(String.format("%-20s | %-22s | %4d    |    %-3d |%n",
                        record.getSubject(), record.getControlType(), record.getSem(), record.getMark()))
        );
        builder.append("-".repeat(66));
        builder.append("\n\n");
        builder.append(String.format("Средний балл: %.2f%n", AverageMark()));
        builder.append("Перевод на бюджет: ").append(checkBudgetAbility()).append("\n");
        builder.append("Красный диплом: ").append(honorDiplomaAbility() ? "Да" : "Нет").append("\n");
        builder.append("Повышенная стипендия: ").append(increasedScholarshipAbility() ? "Да" : "Нет").append("\n");
        return builder.toString();
    }
}