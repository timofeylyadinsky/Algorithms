package by.bsu.sorts.reader;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InfoReader {
    public int oneIntRead(InputStream is) {
        int number = 0;
        Scanner con = new Scanner(is);
        boolean continueInput = true;
        do {
            try {
                System.out.print("Enter an integer: ");
                number = con.nextInt();
                System.out.println(
                        "The number entered is " + number);
                if(number > 0) {
                    continueInput = false;
                }else{
                    System.out.println(
                            "You need positive number ");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Try again. (" +
                        "Incorrect input: an integer is required)");
                con.nextLine();
            }
        }
        while (continueInput);
        return number;
    }

    public static void inputRandomToArray(int[][] arr, int M){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (int) Math.round(Math.random() * M);
            }
        }

    }
}
