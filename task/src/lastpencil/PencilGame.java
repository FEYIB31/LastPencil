package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class PencilGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    public static int pencilsTaken;

    public static int getNumberOfPencils() {
        int numberOfPencils;
        do {
            System.out.println("How many pencils would you like to use:");
            numberOfPencils = positiveValidation();
            if (numberOfPencils <= 0) {
                System.out.println("The number of pencils should be positive");
            }
        } while (numberOfPencils <= 0);

        return numberOfPencils;
    }

    public static int positiveValidation() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric.");
            }
        }
    }

    public static String getPlayer() {
        String firstPlayerName;
        do {
            System.out.println("Who will be the first (John, Jack):");
            firstPlayerName = scanner.nextLine();
            if (!firstPlayerName.equals("John") && !firstPlayerName.equals("Jack")) {
                System.out.println("Choose between 'John' and 'Jack'");
            }
        } while (!firstPlayerName.equals("John") && !firstPlayerName.equals("Jack"));
        return firstPlayerName;
    }

    public void printOutPencils(int numberOfPencils, String firstPlayerName) {
        for (int i = 1; i <= numberOfPencils; i++) {
            System.out.print("|");
        }
        System.out.println();

        if (numberOfPencils > 0) {
            System.out.println(firstPlayerName + "'s turn!");
        }
    }

    public static int johnsTurn(int numberOfPencils) {
        while (true) {
            try {
                pencilsTaken = Integer.parseInt(scanner.nextLine());
                if (pencilsTaken < 1 || pencilsTaken > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    continue;
                }
                if (pencilsTaken > numberOfPencils) {
                    System.out.println("Too many pencils were taken");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Possible values: '1', '2' or '3'");
            }
        }
        return pencilsTaken;
    }

    public static int jacksTurn(int numberOfPencils) {
        if (numberOfPencils % 4 == 1 && numberOfPencils != 1) {
            pencilsTaken = random.nextInt(3) + 1;
        } else if (numberOfPencils % 4 == 0) {
            pencilsTaken = 3;
        } else if (numberOfPencils % 4 == 3) {
            pencilsTaken = 2;
        } else {
            pencilsTaken = 1;
        }
        System.out.println(pencilsTaken);
        return pencilsTaken;
    }

    public void determineWinner(String firstPlayerName) {
        System.out.println(firstPlayerName.equals("John") ? "John Won!" : "Jack Won!");
    }

    public void play() {
        int numberOfPencils = getNumberOfPencils();
        String firstPlayerName = getPlayer();

        printOutPencils(numberOfPencils, firstPlayerName);

        while (numberOfPencils > 0) {
            int pencilsTaken;
            if (firstPlayerName.equals("John")) {
                pencilsTaken = johnsTurn(numberOfPencils);
            } else {
                pencilsTaken = jacksTurn(numberOfPencils);
            }
            numberOfPencils -= pencilsTaken;
            firstPlayerName = (firstPlayerName.equals("John")) ? "Jack" : "John";
            printOutPencils(numberOfPencils, firstPlayerName);

        }
        determineWinner(firstPlayerName);
    }
}
