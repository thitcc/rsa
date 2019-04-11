package command.hub;

import command.ICommand;

import static treatment.IntInputHandling.intInput;
import static treatment.PrimeHandling.isPrime;

public class GeneratePublicKeyCommand implements ICommand {
    @Override
    public boolean execute() {
        boolean correctInput = false;
        int p, q;

        do {
            System.out.print("Please enter the first prime ");
            p = intInput();
            System.out.print("Please enter the second prime ");
            q = intInput();
            try {
                if (!isPrime(p) || !isPrime(q)) throw new NumberFormatException();
                correctInput = true;
            } catch (NumberFormatException e) {
                System.out.println("You entered a non-prime value, try again");
            }
        } while (!correctInput);

        return true;
    }
}
