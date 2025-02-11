package nsu.chebotareva;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class InSeriesTest {

    @Test
    void main(){
        Main.main(new String[0]);
        assertTrue(true);
    }

    @Test
    void allPrime(){
        int[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertFalse(InSeries.isAnyNotPrime(arr));
        Integer[] arr1 = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertFalse(InSeries.isAnyNotPrime(arr1));
    }

    @Test
    void notAllPrime(){
        int[] arr = {6, 8, 7, 13, 5, 9, 4};
        assertTrue(InSeries.isAnyNotPrime(arr));
    }

    @Test
    void isOnePrime() {
        int[] arr = {1};
        assertTrue(InSeries.isAnyNotPrime(arr));
    }

    @Test
    void timeTest() {
        int[] arr1 = AdditionalFunctions.generatingPrimeArr(10000);
        Integer[] arr = IntStream.of(arr1).boxed().toArray(Integer[]::new);
        long start = System.currentTimeMillis();
        assertFalse(InSeries.isAnyNotPrime(arr));
        long end = System.currentTimeMillis();
        System.out.format("In series time %d\n", end - start);
    }
}