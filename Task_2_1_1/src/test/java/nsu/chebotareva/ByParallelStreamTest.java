package nsu.chebotareva;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ByParallelStreamTest {

    @Test
    void allPrime(){
        int[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertFalse(ByParallelStream.isAnyNotPrime(arr));
    }

    @Test
    void notAllPrime(){
        int[] arr = {6, 8, 7, 13, 5, 9, 4};
        assertTrue(ByParallelStream.isAnyNotPrime(arr));
        Integer[] arr1 = {6, 8, 7, 13, 5, 9, 4};
        assertTrue(ByParallelStream.isAnyNotPrime(arr1));
    }

    @Test
    void isOnePrime() {
        int[] arr = {1};
        assertTrue(ByParallelStream.isAnyNotPrime(arr));
    }

    @Test
    void timeTest() {
        int[] arr1 = AdditionalFunctions.generatingPrimeArr(10000);
        Integer[] arr = IntStream.of(arr1).boxed().toArray(Integer[]::new);
        long start = System.currentTimeMillis();
        assertFalse(ByParallelStream.isAnyNotPrime(arr));
        long end = System.currentTimeMillis();
        System.out.format("By ParallelStream time %d\n", end - start);
    }
}