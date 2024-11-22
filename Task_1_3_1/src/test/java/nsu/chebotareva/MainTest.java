package nsu.chebotareva;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MainTest {
    @Test
    public void testA() throws IOException {
        generatingFile("a", 10000);
        List<Long> result = SubstringFinder.findSubstringIndex("input.txt", "a");
//        assertEquals(10000, result.size());
        List<Long> mustBe = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            mustBe.add((long) i);
        }
        assertEquals(mustBe, result);
    }
    @Test
    public void testB() throws IOException {
        generatingFile("a", 1000000);
        List<Long> result = SubstringFinder.findSubstringIndex("input.txt", "b");
        assertEquals(0, result.size());
    }

    @Test
    public void testAbracadabra() throws IOException {
        generatingFile("абракадабра", 10000);
        List<Long> result = SubstringFinder.findSubstringIndex("input.txt", "бра");
//        assertEquals(20000, result.size());
        List<Long> mustBe = new ArrayList<>();
        for (int i = 0; i < 110000; i += 11) {
            mustBe.add((long) 1 + i);
            mustBe.add((long) 8 + i);
        }
        assertEquals(mustBe, result);
    }



    private static void generatingFile() {
        try (FileWriter writer = new FileWriter("input.txt")) {
            for (int i = 0; i < 100000; i++) {
                writer.append(generateRandomUTF8Char());
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generatingFile(String str, int times) {
        try (FileWriter writer = new FileWriter("input.txt")) {
            for (int i = 0; i < times; i++) {
                int len = str.length();
                for (int j = 0; j < len; j ++) {
                    writer.append(str.charAt(j));
                }
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static char generateRandomUTF8Char() {
        Random random = new Random();
        boolean isUpper = random.nextBoolean();
        if (isUpper) {
            return (char) ('A' + random.nextInt(26));
        } else {
            return (char) ('a' + random.nextInt(26));
        }
    }
}