package nsu.chebotareva;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "input.txt";
        String target = "nn";
        List<Long> result = SubstringFinder.findSubstringIndex(fileName, target);
        System.out.println("Индексы вхождений подстроки: " + result);
    }
}