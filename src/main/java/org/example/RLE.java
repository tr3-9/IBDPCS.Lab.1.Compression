package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RLE {

    public static void main(String[] args) throws FileNotFoundException {
        String decompressedString = textToString("src/main/resources/COVID-19");
        String compressedString = compress(decompressedString);
        System.out.println(compressedString);

        // Test decompression
        String decompressed = decompress(compressedString);
        System.out.println("Original equals decompressed: " + decompressedString.equals(decompressed));
    }

    /** This method converts the information stored in a text file into a String. */
    public static String textToString(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            String subSeq = sc.next();
            for (int i = 0; i < subSeq.length(); i++) {
                sb.append(subSeq.charAt(i));
            }
        }
        return sb.toString();
    }

    /** TODO 1: Given a String (a genome sequence of COVID-19) implement the RLE algorithm that will use RLE to compress a String. Returns the compressed String. */
    public static String compress(String uncompressed) {
        if (uncompressed == null || uncompressed.isEmpty()) {
            return "";
        }

        StringBuilder compressed = new StringBuilder();
        int count = 1;
        char currentChar = uncompressed.charAt(0);

        for (int i = 1; i < uncompressed.length(); i++) {
            if (uncompressed.charAt(i) == currentChar) {
                count++;
            } else {
                compressed.append(currentChar).append(count);
                currentChar = uncompressed.charAt(i);
                count = 1;
            }
        }
        compressed.append(currentChar).append(count);

        return compressed.toString();
    }

    /** TODO 2: Given a String (a genome sequence of COVID-19) implement the RLE algorithm that will use RLE to decompress a String. Returns the uncompressed String. */
    public static String decompress(String compressed) {
        if (compressed == null || compressed.isEmpty()) {
            return "";
        }

        StringBuilder decompressed = new StringBuilder();
        int i = 0;

        while (i < compressed.length()) {
            char character = compressed.charAt(i++);
            StringBuilder countStr = new StringBuilder();

            while (i < compressed.length() && Character.isDigit(compressed.charAt(i))) {
                countStr.append(compressed.charAt(i));
                i++;
            }

            int count = Integer.parseInt(countStr.toString());
            for (int j = 0; j < count; j++) {
                decompressed.append(character);
            }
        }

        return decompressed.toString();
    }
}