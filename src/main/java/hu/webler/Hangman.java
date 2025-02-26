package hu.webler;

import java.util.Random;
import java.util.Scanner;

public class Hangman {

    private static int wrongTry = 158;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = generateRandomWord(getWords());
        String res = hidedWord(word);
        System.out.println(res);
        String userGuess = getUserInput(scanner, "Kérek egy betűt:");
        String whatThe = changeAGuess(word, res, userGuess);
        System.out.println(whatThe);
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
        return word.toLowerCase().contains(letter.toLowerCase());
    }

    private static String changeAGuess(String word, String hidedWord, String userGuess) {
        if (checkIfValidGuess(word, userGuess)) {
            StringBuilder updatedWord = new StringBuilder(hidedWord);
            for (int i = 0; i < word.length(); i++) {
                if (word.toLowerCase().charAt(i) == userGuess.toLowerCase().charAt(0)) {
                    // akkor ki kell cserélni
                    // akkor a hided word-ben az alsóvonást kicserélni a megtalált betű vagy betűkre!
                    updatedWord.setCharAt(i + 1, word.charAt(i));
                }
            }
            return updatedWord.toString();
        }
        return hidedWord;
    }
}
