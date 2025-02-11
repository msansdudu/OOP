package nsu.chebotareva;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Integer[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        System.out.println(ByThreads.isAnyNotPrime(arr, 3));
    }
}