package geekbrains.hw_1_4;

import java.util.Random;
import java.util.Scanner;

public class XOgame {

    static final int SIZE = 6;
//    static final int DOTS_TO_WIN = 5;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Ты победил! ");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил! ");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }

        }
    }


    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x, y;

        do {
            System.out.println("Input X, Y ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));

        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x, y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

//    public static boolean checkWin(char c) {
//        if (map[0][0] == c && map[0][1] == c && map[0][2] == c) {return true; }
//        if (map[1][0] == c && map[1][1] == c && map[1][2] == c) {return true; }
//        if (map[2][0] == c && map[2][1] == c && map[2][2] == c) {return true; }
//
//        if (map[0][0] == c && map[1][0] == c && map[2][0] == c) {return true; }
//        if (map[0][1] == c && map[1][1] == c && map[2][1] == c) {return true; }
//        if (map[0][2] == c && map[1][2] == c && map[2][2] == c) {return true; }
//
//        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {return true; }
//        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) {return true; }

    public static boolean checkWin (char c) {                       // Программа не работает, к сожалению;

        int n = 4;
        int delta = SIZE - n;
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        // Описание логики:
        for (int i = 0; i <= delta; i++) {                          // Сначала перебираю точки начала верхнего левого угла квадрата со сторонами n x n, внутри которого существуют все возможные выигрыши;
            for (int j = 0; j <= delta; j++) {                      // Получаю возможные точки начала в зоне delta x delta;

                for (int k = i; k < (i + n); k++) {                 // От каждой возможной точки начала просматриваю внутри каждого квадрата n x n ходы человека и ИИ;
                    for (int l = j; l < (j + n); l++) {
                        if (k == l && map[k][l] == c) {             // Смотрю диагональ СЗ - ЮВ;
                            num1++;                                 // Если нахожу ход, то считаю;
                        }
                        if (l == n - k + 1 && map[k][l] == c) {     // Аналогично диагональ СВ - ЮЗ;
                            num2++;
                        }
                    }
                }
                if (num1 == n || num2 == n) {                       // Если досчитался до n, то говорим о выигрыше;
                    return true;
                }

                for (int k = i; k < (i + n); k++) {                 // Аналогично с горизонталями и вертикалями;
                    for (int l = j; l < (j + n); l++) {
                        if (map[k][l] == c) {
                            num3++;
                        }
                        if (map[l][k] == c) {
                            num4++;
                        }
                    }
                }
                if (num3 == n || num4 == n) {
                    return true;
                }
            }
        }
        return false;
    }

}









