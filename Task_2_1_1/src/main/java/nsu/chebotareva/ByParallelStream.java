package nsu.chebotareva;

import java.util.Arrays;
import java.util.List;

public class ByParallelStream {
    /**
     * Основная функция.
     *
     * @param arr1 -- массив чисел.
     * @return -- true если есть хотя бы одно простое, иначе false.
     */
    public static boolean isAnyNotPrime(Integer[] arr1) {
        List<Integer> arr = Arrays.asList(arr1);
        return arr.parallelStream().anyMatch(n -> !isPrime(n));
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
