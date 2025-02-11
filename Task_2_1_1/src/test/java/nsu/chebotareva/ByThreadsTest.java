package nsu.chebotareva;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ByThreadsTest {

    @Test
    void allPrime() throws InterruptedException {
        int[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertFalse(ByThreads.isAnyNotPrime(arr, 3));
    }

    @Test
    void notAllPrime() throws InterruptedException {
        int[] arr = {6, 8, 7, 13, 5, 9, 4};
        assertTrue(ByThreads.isAnyNotPrime(arr, 3));
        Integer[] arr1 = {6, 8, 7, 13, 5, 9, 4};
        assertTrue(ByThreads.isAnyNotPrime(arr1, 5));
    }

    @Test
    void isOnePrime() throws InterruptedException {
        int[] arr = {1};
        assertTrue(ByThreads.isAnyNotPrime(arr, 4));
    }

    @Test
    void timeTest() throws InterruptedException {
        int[] arr1 = AdditionalFunctions.generatingPrimeArr(10000);
        Integer[] arr = IntStream.of(arr1).boxed().toArray(Integer[]::new);
        for (int i = 1; i < 200; i += 3) {
            long start = System.currentTimeMillis();
            assertFalse(ByThreads.isAnyNotPrime(arr, i));
            long end = System.currentTimeMillis();
            System.out.format("By Threads with \t%d threads, time \t%d\n", i, end - start);
        }
    }
}