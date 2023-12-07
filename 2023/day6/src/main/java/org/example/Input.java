package org.example;

import lombok.val;

import java.io.IOException;
import java.util.Scanner;

public class Input {
    public static String getInput() {
        return readResourceFile("real.txt");
    }

    public static String readResourceFile(String fileName) {
        try (val inputStream = Input.class.getClassLoader().getResourceAsStream(fileName)) {
            try (val scanner = new Scanner(inputStream)
            ) {
                val content = new StringBuilder();
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
                return content.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
