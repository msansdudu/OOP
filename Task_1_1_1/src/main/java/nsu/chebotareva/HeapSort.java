package nsu.chebotareva;

/**
 * Класс HeapSort, в котором реализована пирамидальная сортировка (сортировка кучей).
 */
public class HeapSort {
    /**
     * Меняет местами два элемента в массиве arr с индексами a и b.
     *
     * @param arr - входной массив
     * @param a   - индекс первого элемента
     * @param b   - индекс второго элемента
     */
    void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * Из массива arr длины n для элемента с индексом i создаем кучу вида: оба дочерних элемента меньше родителя.
     * Рекурсивный способ.
     *
     * @param arr - входной массив
     * @param n   - длина массива
     * @param i   - нужный индекс
     */
    void heapify(int[] arr, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    /**
     * Сортировка кучей для всех элементов, меняем порядок на нужный (по возрастанию).
     *
     * @param arr - входной массив
     * @param n   - длина массива
     */
    void sort(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    /**
     * Метод main с тестовым массивом.
     *
     * @param args -
     */
    public static void main(String[] args) {
        int[] arr = {2, 5, 4, 1, 3};
        int n = arr.length;

        HeapSort heap = new HeapSort();
        heap.sort(arr, n);

        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}