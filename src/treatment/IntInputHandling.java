package treatment;

import java.util.Scanner;

public class IntInputHandling {

    public static int intInput() {
        Scanner input = new Scanner(System.in);

        boolean correctInput = false;
        int value = 0;

        while (!correctInput) {
            try {
                System.out.print("\n>> ");
                value = Integer.parseInt(input.next());
                correctInput = true;
            } catch (NumberFormatException e) {
                System.out.print("You typed a value that is not a integer, please try again\n" +
                        e.getMessage());
            }
        }

        return value;
    }

}
