package nsu.chebotareva;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ByThreads {
    public static volatile boolean hasNonPrime;
    /**
     * Основная функция.
     *
     * @param arr1 -- массив чисел в виде int[].
     * @param n -- количество потоков.
     * @return -- true если есть хотя бы одно простое, иначе false.
     */
    public static boolean isAnyNotPrime(int[] arr1, int n) throws InterruptedException {
        Integer[] arr = IntStream.of(arr1).boxed().toArray(Integer[]::new);
        hasNonPrime = false;
        int len = arr.length;
        int numInThread = (int) Math.ceil((double) len / n);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int start = i * numInThread;
            final int end = Math.min(start + numInThread, len);

            Thread thread = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (!isPrime(arr[j])) {
                        hasNonPrime = true;
                        return;
                    } else if (hasNonPrime) {
                        return;
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        return hasNonPrime;
    }

    /**
     * Основная функция.
     *
     * @param arr -- массив чисел в виде Integer[].
     * @param n -- количество потоков.
     * @return -- true если есть хотя бы одно простое, иначе false.
     */
    public static boolean isAnyNotPrime(Integer[] arr, int n) throws InterruptedException {
        hasNonPrime = false;
        int len = arr.length;
        int numInThread = (int) Math.ceil((double) len / n);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int start = i * numInThread;
            final int end = Math.min(start + numInThread, len);

            Thread thread = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (!isPrime(arr[j])) {
                        hasNonPrime = true;
                        return;
                    } else if (hasNonPrime) {
                        return;
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        return hasNonPrime;
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
