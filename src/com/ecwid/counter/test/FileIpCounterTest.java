package com.ecwid.counter.test;

import java.io.IOException;

import static com.ecwid.counter.FileIpCounter.getUniqueCount;

/**
 * To enable assertion, add -enableassertions VM command line argument
 */
public class FileIpCounterTest {

    public static void main(String[] args) throws IOException {
        long count = getUniqueCount("./test/1.txt");
        assert count == 2;

        count = getUniqueCount("./test/2.txt");
        assert count == 1;
        System.out.println("Tests passed!");
    }
}
