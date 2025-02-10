package nsu.chebotareva;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {6, 8, 7, 13, 5, 9, 4};
        System.out.println(ByParallelStream.isAnyPrime(arr));
    }
}