package nsu.chebotareva;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SubstringFinder {
    public static List<Long> findSubstringIndex(String fileName, String substr) throws IOException {
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
        List<Long> index = new ArrayList<>();
        int substrLen = substr.length();
        int bufferSize = substrLen * 2;
        char[] buffer = new char[bufferSize];
        int amountOfOccurences = 0;
        int amountOfSymbols = 0;

        try {
            long pos = 0;
            int readChar;
            int EOF = substrLen * 2 + 1;

            for (int i = 0; i < substrLen; i++) {
                readChar = file.read();
                amountOfSymbols++;
                if (readChar == -1) {
                    EOF = i + substrLen;
                    break;
                }
                buffer[i] = (char) readChar;
            }

            while (EOF > substrLen) {
                for (int i = 0; i < substrLen; i++) {
                    readChar = file.read();
                    amountOfSymbols++;
                    if (readChar == -1) {
                        EOF = i;
                        break;
                    }
                    buffer[i + substrLen] = (char) readChar;
                }
                for (int i = 0; i <= bufferSize - substrLen; i++) {
                    if (isMatch(buffer, substr, i, substrLen, EOF)) {
                        if (amountOfOccurences == 0 || index.get(amountOfOccurences - 1) != pos + i) {
                            index.add(pos + i);
                            amountOfOccurences++;
                        }
                    }
                }

                pos += bufferSize - substrLen;

                for (int i = 0; i < substrLen; i++) {
                    buffer[i] = buffer[i + substrLen];
                }
            }
        }
        finally {
            file.close();
        }
        amountOfSymbols--;
        if (amountOfOccurences != 0 && index.get(amountOfOccurences - 1) == amountOfSymbols) {
            index.remove(amountOfOccurences - 1);
        }
        return index;
    }

    private static boolean isMatch(char[] buff, String substr, int beg, int substrLen, int EOF) {
        for (int i = 0; i < substrLen; i++) {
            if (buff[beg + i] != substr.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}