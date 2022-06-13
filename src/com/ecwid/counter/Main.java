package com.ecwid.counter;

import java.io.IOException;

import static com.ecwid.counter.FileIpCounter.getUniqueCount;

public class Main {

    /**
     * You should pass file path as the first command line argument
     */
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String filePath = args[0];
        System.out.println("Start parsing the file: " + filePath);
        long uniqueCount = getUniqueCount(filePath);
        System.out.println("Unique ip count: " + uniqueCount);
        System.out.println("Time took: " + ((double) (System.currentTimeMillis() - start)) / 1000 + " sec.");
        System.in.read();
    }


}
