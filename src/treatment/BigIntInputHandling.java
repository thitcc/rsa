package treatment;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BigIntInputHandling {

    public static BigInteger bigIntegerInput() {
        Scanner input = new Scanner(System.in);

        boolean correctInput = false;
        BigInteger value = new BigInteger("0");

        while (!correctInput) {
            try {
                System.out.print("\n>> ");
                value = new BigInteger(input.nextLine());
                correctInput = true;
            } catch (NumberFormatException e) {
                System.out.print("You typed an invalid value, please try again\n" +
                        e.getMessage());
            }
        }

        return value;
    }

}
