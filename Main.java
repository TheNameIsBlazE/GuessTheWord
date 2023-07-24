package com.company;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

import static com.company.ProjectConstants.*;

public class Main {

    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        Random rand = new Random();

        String word = null;
        String garbage;
        boolean amongUs;
        boolean done;
        int option = 0;
        int randomInt = 0;
        int tries;
        String letter;
        boolean guessedAlready = false;
        boolean breakout = false;
        boolean wordOrLetter;
        String guess;
        boolean masterDone;

        String guessedLetters[] = new String[26];

        do {
            masterDone = false;
            do {
                amongUs = false;
                tries = 0;
                System.out.println(SEPARATOR);
                System.out.println("Word bank or enter word");
                System.out.println("1. Word Bank");
                System.out.println("2. Enter Word");
                System.out.println("3. Exit");

                if (s.hasNextInt()) {
                    option = s.nextInt();

                    garbage = s.nextLine();

                    switch (option) {
                        case 1: {

                            randomInt = rand.nextInt(NUMWORDS);

                            if (randomInt == 1) {
                                word = AMONGUS;
                            } else if (randomInt == 2) {
                                word = GUHBEAN;
                            } else if (randomInt == 3) {
                                word = PHOTOSYNTHESIS;
                            } else if (randomInt == 4) {
                                word = ENCRYPTINGCODES;
                            } else if (randomInt == 5) {
                                word = ELEVATEDARTS;
                            } else if (randomInt == 6) {
                                word = UNIFORMCIRCULARMOTION;
                            } else if (randomInt == 7) {
                                word = KOEGAWA;
                            } else if (randomInt == 8) {
                                word = SIMPLEHARMONICMOTION;
                            } else if (randomInt == 9) {
                                word = PULLUPSONTHETRAINBAR;
                            } else if (randomInt == 10) {
                                word = THISPROGRAMSUCKS;
                            } else if (randomInt == 11) {
                                word = ROCKETLEAGUE;
                            } else if (randomInt == 12) {
                                word = MATHJEOPARDY;
                            }
                            amongUs = true;
                            break;

                        }
                        case 2: {
                            System.out.println("Please enter a word or words");
                            word = s.nextLine().toUpperCase(Locale.ROOT);
                            amongUs = true;
                            break;



                        } case 3: {

                            System.exit(0);
                            break;

                        }
                        default: {
                            System.out.println("Enter an integer lol");
                            break;
                        }
                    }

                } else {
                    System.out.println("enter an integer lol");
                    garbage = s.nextLine();
                }
            } while (!amongUs);

            // making char array
            char array[] = new char[word.length()];

            // clearing the other array
            Arrays.fill(guessedLetters, null);


            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != ' ') {
                    array[i] = '-';
                } else {
                    array[i] = word.charAt(i);
                }
            }

            tries = 0;


            do {
                amongUs = false;
                System.out.println(SEPARATOR);
                System.out.println("NUMBER OF TRIES: " + tries);
                System.out.println();
                for (int i = 0; i < word.length(); i++) {
                    System.out.print(array[i]);
                }

                System.out.println("\n");

                System.out.println("Guessed letters:");

                for (int i = 0; i < guessedLetters.length; i++) {
                    if (guessedLetters[i] == null) {
                        break;
                    } else {
                        System.out.print(guessedLetters[i] + " ");
                    }
                }
                System.out.println();

                do {
                    wordOrLetter = false;
                    System.out.println(SEPARATOR);
                    System.out.println("Guess the word or a letter?");
                    System.out.println("1. Word");
                    System.out.println("2. Letter");

                    if (s.hasNextInt()) {
                        option = s.nextInt();

                        switch (option) {

                            case 1: {

                                garbage = s.nextLine();

                                System.out.println("What do you think the word(s) is/are");
                                guess = s.nextLine().toUpperCase(Locale.ROOT);

                                if (guess.equals(word)) {
                                    breakout = true;
                                } else {
                                    System.out.println("You did not guess the word correctly");
                                }

                                wordOrLetter = true;
                                break;

                            }
                            case 2: {

                                garbage = s.nextLine();

                                do {
                                    done = false;
                                    System.out.println(SEPARATOR);
                                    System.out.println("Enter a letter to guess");
                                    letter = s.nextLine().toUpperCase(Locale.ROOT);
                                    if (letter.length() > 1) {
                                        System.out.println("Letter must be one character long");
                                    } else {
                                        if (Character.isLetter(letter.charAt(0))) {
                                            guessedAlready = false;
                                            for (int i = 0; i < guessedLetters.length; i++) {
                                                if (letter.equals(guessedLetters[i])) {
                                                    System.out.println("Already guessed that one!");
                                                    guessedAlready = true;
                                                    break;
                                                }
                                            }

                                            if (!guessedAlready) {
                                                for (int i = 0; i < word.length(); i++) {
                                                    if (word.charAt(i) == letter.charAt(0)) {
                                                        array[i] = word.charAt(i);
                                                    }
                                                }

                                                guessedLetters[tries] = letter;
                                            }

                                            done = true;

                                        } else {
                                            System.out.println("Enter a valid letter");
                                        }
                                    }

                                } while (!done);

                                breakout = true;

                                for (int i = 0; i < word.length(); i++) {
                                    if (array[i] == '-') {
                                        breakout = false;
                                    }
                                }

                                wordOrLetter = true;

                                break;

                            }
                            default: {
                                System.out.println("Please enter a valid integer");
                                break;
                            }
                        }

                    } else {
                        System.out.println("Please enter a valid integer");
                        garbage = s.nextLine();
                    }
                } while (!wordOrLetter);

                tries++;

                if (breakout) {
                    amongUs = true;
                    System.out.println(SEPARATOR);
                    System.out.println("You have successfully guessed the word!");
                    System.out.println("The word was: " + word);
                    System.out.println("Number of tries: " + tries);
                }


            } while (!amongUs);

        } while (!masterDone);


    }
}
