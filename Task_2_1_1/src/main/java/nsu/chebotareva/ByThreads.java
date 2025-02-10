package nsu.chebotareva;

public class ByThreads {
    /**
     * Основная функция.
     *
     * @param arr -- массив чисел.
     * @param n -- количество потоков.
     * @return -- true если есть хотя бы одно простое, иначе false.
     */
    public static boolean isAnyPrime(int[] arr, int n) {
        Runnable task = () -> {

        };
        Thread thread = new Thread(task);
        thread.start();
        return false;
    }

    /**
     * Вспомогательная функция для отдельного числа.
     *
     * @param n -- число.
     * @return -- true, если число простое, иначе false.
     */
    private static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return true;
            }
        }
        return false;
    }
}
