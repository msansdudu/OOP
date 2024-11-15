package nsu.chebotareva;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class MainTest {
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