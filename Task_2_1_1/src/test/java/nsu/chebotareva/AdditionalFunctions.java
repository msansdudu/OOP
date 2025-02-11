package nsu.chebotareva;

import java.util.Arrays;

public class AdditionalFunctions {
    public static boolean isPrime(int n) {
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

    public static int[] generatingPrimeArr(int cnt) {
        int n = 2;
        int[] arr = new int[cnt];
        int i = 0;
        while (i < cnt){
            if (AdditionalFunctions.isPrime(n)) {
                arr[i] = n;
                i++;
            }
            n++;
        }
        return arr;
    }
}
