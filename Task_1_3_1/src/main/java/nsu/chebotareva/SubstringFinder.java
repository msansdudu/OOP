package nsu.chebotareva;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SubstringFinder {
    public static List<Long> findSubstringIndex(String fileName, String substr) throws IOException {
        List<Long> index = new ArrayList<>();
        byte[] substrBytes = substr.getBytes(StandardCharsets.UTF_8);
        int substrLength = substrBytes.length;

        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            long fileLength = file.length();
            byte[] buff = new byte[substrLength * 2];

            long pos = 0;
            while (pos < fileLength) {
                int bytesRead = file.read(buff, 0, Math.min(buff.length, (int) (fileLength - pos)));

                if (bytesRead < substrLength) {
                    break;
                }
                for (int i = 0; i <= bytesRead - substrLength; i++) {
                    if (isMatch(buff, substrBytes, i)) {
                        index.add(pos + i);
                    }
                }
                pos += bytesRead - substrLength + 1;
                file.seek(pos);
            }
        }
        return index;
    }

    private static boolean isMatch(byte[] buff, byte[] substrBytes, int beg) {
        for (int i = 0; i < substrBytes.length; i++) {
            if (buff[beg + i] != substrBytes[i]) {
                return false;
            }
        }
        return true;
    }
}