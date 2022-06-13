package com.ecwid.counter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.Short.parseShort;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileIpCounter {

    public static long getUniqueCount(String filePath) throws IOException {
        byte [] data = getArray();
        long counter = 0;
        File file = new File(filePath);
        try (InputStream inputStream = new FileInputStream(file);
             Scanner scanner = new Scanner(inputStream, UTF_8)) {
            while (scanner.hasNext()) {
                String line = getLine(scanner);
                long currentIpIn10Notation = getIpIn10Notation(line);
                int arrayIndex = (int) (currentIpIn10Notation / 8);
                int bitIndex = (int) (currentIpIn10Notation % 8);
                if ((data[arrayIndex] & (1 << bitIndex)) == 0) {
                    counter++;
                    data[arrayIndex] = (byte) (data[arrayIndex] | (1 << bitIndex));
                }
            }
        } catch (Exception ex) {
            System.err.println("An exception occurred during the parsing process. " + ex);
            throw ex;
        }
        return counter;
    }

    private static byte[] getArray() {
        // 255.255.255.255 is the IPv4 maximum value in 256 scale of notation
        long maxIpV4In10NotationValue
                = 255 * 1 + 255 * 256 + 255 * (long) Math.pow(256, 2) + 255 * (long) Math.pow(256, 3);
        int maxIpV4ByteNumber = (int) (maxIpV4In10NotationValue / 8);
        return new byte[maxIpV4ByteNumber + 1];
    }

    private static String getLine(Scanner scanner) throws IOException {
        String line = scanner.nextLine();
        if (scanner.ioException() != null) {
            throw scanner.ioException();
        }
        return line;
    }

    private static Long getIpIn10Notation(String line) {
        String[] ipIn256NotationNumbers = line.split("\\.");
        return (long) parseShort(ipIn256NotationNumbers[3])
                + (long) parseShort(ipIn256NotationNumbers[2]) * 256
                + (long) parseShort(ipIn256NotationNumbers[1]) * 65_536
                + (long) parseShort(ipIn256NotationNumbers[0]) * 16_777_216;
    }
}
