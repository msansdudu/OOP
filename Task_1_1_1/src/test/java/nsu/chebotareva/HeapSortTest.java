package nsu.chebotareva;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Random;

class HeapSortTest {

    @Test
    void test_swap() {
        System.out.println("Testing swap");
        int[] arr = {1, 2, 3};
        int[] mustBe = {3, 2, 1};
        HeapSort ob = new HeapSort();
        ob.swap(arr, 2, 0);
        Assertions.assertArrayEquals(mustBe, arr);
    }

    @Test
    void test_heapify() {
        System.out.println("Testing heapify");
        int[] arr1 = {1, 2, 3};
        int[] mustBe1 = {3, 2, 1};
        HeapSort ob = new HeapSort();
        ob.heapify(arr1, 3, 0);
        Assertions.assertArrayEquals(mustBe1, arr1);
        int[] arr2 = {1, 3, 2, 4};
        int[] mustBe2 = {3, 4, 2, 1};
        ob.heapify(arr2, 4, 0);
        Assertions.assertArrayEquals(mustBe2, arr2);
    }

    @Test
    void test_sort() {
        System.out.println("Testing sort");
        int[] a = {1, 2, 3, 4, 5};
        int[] testArray = {2, 3, 1, 4, 5};
        HeapSort ob = new HeapSort();
        ob.sort(testArray, 5);
        Assertions.assertArrayEquals(a, testArray);
    }

    void random_array(int size){
        int[] randomArray = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt(1001); // Генерация случайного числа от 0 до 1000
        }
        HeapSort ob = new HeapSort();

        long start = System.nanoTime();
        ob.sort(randomArray, size);
        long end = System.nanoTime();
        long duration = end - start;
        System.out.println(duration / 15);
    }

    @Test
    void difAlg() {
        for (int size = 5; size < 10000; size+=100) {
            random_array(size);
        }
    }
}
