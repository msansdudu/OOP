package nsu_chebotareva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    void test_swap() {
        System.out.println("Testing swap");
        int[] arr = {1, 2, 3};
        int[] must_be = {3, 2, 1};
        HeapSort ob = new HeapSort();
        ob.swap(arr, 2, 0);
        Assertions.assertArrayEquals(must_be, arr);
    }

    @Test
    void test_heapify() {
        System.out.println("Testing heapify");
        int[] arr = {1, 2, 3};
        int[] must_be = {3, 2, 1};
        HeapSort ob = new HeapSort();
        ob.heapify(arr, 3, 0);
        Assertions.assertArrayEquals(must_be, arr);
    }

    @Test
    void test_sort() {
        System.out.println("Testing sort");
        int[] a = {1, 2, 3, 4, 5};
        int[] test_array = {2, 3, 1, 4, 5};
        HeapSort ob = new HeapSort();
        ob.sort(test_array, 5);
        Assertions.assertArrayEquals(a, test_array);
    }
}