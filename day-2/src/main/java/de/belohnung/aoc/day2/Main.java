package de.belohnung.aoc.day2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int validP1 = 0;
        int validP2 = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("input.txt");
            try (Scanner scanner = new Scanner(fileInputStream)) {
                while (scanner.hasNextLine()) {
                    String current = scanner.nextLine();
                    if (checkLinePart1(current)) {
                        System.out.println(current);
                        validP1++;
                    }
                    if (checkLinePart2(current)) {
                        validP2++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(validP1 + " passwords are valid in Part1");
        System.out.println(validP2 + " passwords are valid in Part2");
    }

    private static boolean checkLinePart1(String line) {
        String requirements = line.split(": ")[0];
        int minOccurrences = Integer.parseInt(requirements.split("-")[0]);
        int maxOccurrences = Integer.parseInt(requirements.split("-")[1].split(" ")[0]);
        char letter = requirements.split("-")[1].split(" ")[1].charAt(0);
        String pw = line.split(": ")[1];
        System.out.println("Letter: " + letter + " min:" + minOccurrences + " max:" + maxOccurrences + " pw: " + pw);
        long occurrences = pw.chars().filter(character -> character == letter).count();
        return occurrences >= minOccurrences && occurrences <= maxOccurrences;
    }

    private static boolean checkLinePart2(String line) {
        String requirements = line.split(": ")[0];
        int checkPoint1 = Integer.parseInt(requirements.split("-")[0]) -1;
        int checkPoint2 = Integer.parseInt(requirements.split("-")[1].split(" ")[0]) -1;
        char letter = requirements.split("-")[1].split(" ")[1].charAt(0);
        String pw = line.split(": ")[1];
        return (pw.charAt(checkPoint1) == letter) ^ (pw.charAt(checkPoint2) == letter);
    }
}
