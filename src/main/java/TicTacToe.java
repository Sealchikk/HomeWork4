import java.util.Random;
import java.util.Scanner;
import java.util.Locale;

public class TicTacToe {

    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_EMPTY = '.';

    public static final Random random = new Random();
    public static final Scanner scanner = new Scanner(System.in);

    public static char[][] field;
    public static char dotHuman;
    public static char dotAi;
    public static int fieldSizeX;
    public static int fieldSizeY;
    public static int scoreHuman;
    public static int scoreAi;
    public static int roundCounter;
    public static int winlength;

    public static void main(String[] args) {
        startNewGame();


    }

    public static void startNewGame() {
        while (true) {
            chooseDot();
            playRound();

            System.out.printf("SCORE:    HUMAN     AI\n" +
                    "            %d       %d\n", scoreHuman, scoreAi);

            System.out.print("Want to play again ? (Y or N) >>> ");

            if (!scanner.next().toLowerCase(Locale.ROOT).equals("y")) {
                System.out.println("BYE");
                break;
            }
        }
    }

    public static void playRound() {
        System.out.printf("Round %d start\n", ++roundCounter);
        initField(3, 3);
        printField();
        if (dotHuman == DOT_X) {
            humanFirst();
        } else {
            aiFirst();
        }
    }

    public static void humanFirst() {
        while (true) {
            humanTurn();
            printField();
            if (gameCheck(dotHuman)) {
                break;
            }
            aiTurn();
            printField();
            if (gameCheck(dotAi)) {
                break;
            }
        }
    }

    public static void aiFirst() {
        while (true) {
            aiTurn();
            printField();
            if (gameCheck(dotAi)) {
                break;
            }
            humanTurn();
            printField();
            if (gameCheck(dotHuman)) {
                break;
            }
        }
    }

    public static boolean gameCheck(char dot) {
        if (checkWin(dot) && dot == dotHuman) {
            System.out.println("Human win!");
            scoreHuman++;
            return true;
        } else if (checkWin(dot) && dot == dotAi) {
            System.out.println("AI win!");
            scoreAi++;
            return true;
        }
        return checkDraw();
    }

    public static void chooseDot() {
        System.out.print("Type 'X' to play with X and for 0 type anything >>> ");

        if (scanner.next().toLowerCase(Locale.ROOT).equals("x")) {
            dotHuman = DOT_X;
            dotAi = DOT_O;
        } else {
            dotHuman = DOT_O;
            dotAi = DOT_X;
        }
    }

    public static void aiTurn() {
        int x;
        int y;

        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCallValid(x, y));

        field[y][x] = dotAi;
    }

    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.print("Please enter coordinates x & y >>>> ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCallValid(x, y));

        field[x][y] = dotHuman;
    }

    public static boolean checkWin(char dot) {
        for (int x = 0; x < fieldSizeX; x++) {
            if (field[x][0] == dot && field[x][1] == dot && field[x][2] == dot) {
                return true;
            }
        }
        for (int y = 0; y < fieldSizeY; y++) {
            if (field[0][y] == dot && field[1][y] == dot && field[2][y] == dot) {
                return true;
            }
        }
        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
        if (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) return true;

        return false;

    }

    public static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (field[x][y] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        System.out.println("DRAW!");
        return true;
    }

    public static boolean isCallValid(int x, int y) {
        return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY && field[x][y] == DOT_EMPTY;
    }

    public static void initField(int sizeY, int sizeX) {
        fieldSizeX = sizeX;
        fieldSizeY = sizeY;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    public static void printField() {
        System.out.print('+');
        for (int i = 0; i < fieldSizeY * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? "-" : i / 2 + 1);
        }
        System.out.println();
        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int l = 0; l < fieldSizeX; l++) {
                System.out.print(field[i][l] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < fieldSizeY * 2 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();

    }


}

