package nsu.chebotareva;

public class InSeries {
    /**
     * Основная функция.
     *
     * @param arr -- массив чисел в виде int[].
     * @return -- true если есть хотя бы одно простое, иначе false.
     */
    public static boolean isAnyNotPrime(int[] arr) {
        for(int n:arr) {
            if (!isPrime(n)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Основная функция.
     *
     * @param arr -- массив чисел в виде Integer[].
     * @return -- true если есть хотя бы одно простое, иначе false.
     */
    public static boolean isAnyNotPrime(Integer[] arr) {
        for(int n:arr) {
            if (!isPrime(n)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Вспомогательная функция для отдельного числа.
     *
     * @param n -- число.
     * @return -- true, если число простое, иначе false.
     */
    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
