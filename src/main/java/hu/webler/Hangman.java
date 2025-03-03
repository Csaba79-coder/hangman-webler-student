package hu.webler;

import java.util.Random;
import java.util.Scanner;

public class Hangman {

    private static int wrongTry = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        do {
            wrongTry = 3;
            playAgain = playGame(scanner);
        } while (playAgain);
    }

    private static boolean playGame(Scanner scanner) {
        String word = generateRandomWord(getWords());
        String res = hidedWord(word);
        System.out.println(res);
        StringBuilder updatedWord = new StringBuilder(res);
        while (wrongTry > 0) {
            String userGuess = getUserInput(scanner, "Kérek egy betűt:");
            String whatThe = changeAGuess(word, updatedWord, userGuess);
            System.out.println(whatThe);
            if (!updatedWord.toString().contains("_")) {
                System.out.println("Nyertél!");
                break;
            }
        }
        if (wrongTry == 0) {
            System.out.println("Vesztettél");
        }
        return askPlayAgain(scanner);
    }

    private static boolean askPlayAgain(Scanner scanner) {
        System.out.println("Új játék?");
        String answer = scanner.next().toLowerCase();
        return answer.equalsIgnoreCase("i");
    }

    private static String[] getWords() {
        return new String[]{"Budapest", "Amsterdam", "Berlin"};
    }

    private static String generateRandomWord(String[] words) {
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index];
        //return words[new Random().nextInt(words.length)];
    }

    private static String hidedWord(String word) {
        int length = word.length();
        char[] charsOfWord = new char[length];
        for (int i = 0; i < length; i++) {
            char letter = '_';
            charsOfWord[i] = letter;
        }
        StringBuilder hidedWord = new StringBuilder();
        for (char c : charsOfWord) {
            hidedWord.append(c).append(" ");
        }
        return hidedWord.toString();
    }

    private static String getUserInput(Scanner scanner, String text) {
        System.out.println(text);
        return scanner.next();
    }

    private static boolean checkIfValidGuess(String word, String letter) {
        boolean isValidGuess = word.toLowerCase().contains(letter.toLowerCase());
        if (!isValidGuess) {
            wrongTry--;
            System.out.println("You have " + wrongTry + " guess left");
        }
        return isValidGuess;
    }

    private static String changeAGuess(String word, StringBuilder updatedWord, String userGuess) {
        if (checkIfValidGuess(word, userGuess)) {
            for (int i = 0; i < word.length(); i++) {
                if (word.toLowerCase().charAt(i) == userGuess.toLowerCase().charAt(0)) {
                    int index = i * 2;
                    updatedWord.setCharAt(index, word.charAt(i));
                }
            }
        }
        return updatedWord.toString();
    }
}
