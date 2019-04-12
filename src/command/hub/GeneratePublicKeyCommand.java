package command.hub;

import command.ICommand;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import static treatment.BigIntInputHandling.bigIntegerInput;

public class GeneratePublicKeyCommand implements ICommand {
    @Override
    public boolean execute() {
        boolean correctInput = false;
        BigInteger p, q;

        do {
            System.out.print("Please enter the first prime or 0 to exit ");
            p = bigIntegerInput();

            System.out.print("Please enter the second prime or 0 to exit ");
            q = bigIntegerInput();


            try {
                if (p.compareTo(BigInteger.ZERO) == 0 || q.compareTo(BigInteger.ZERO) == 0) {
                    System.out.println("\nReturning to menu\n");
                    return true;
                }
                else if (!p.isProbablePrime(7) || !q.isProbablePrime(13)) throw new NumberFormatException();
                correctInput = true;
            } catch (NumberFormatException e) {
                System.out.println("You entered a non-prime value, try again\n" +
                        "Suggestion: " + p.nextProbablePrime() + " and " + q.nextProbablePrime());
            }
        } while (!correctInput);

        BigInteger n = p.multiply(q);
        BigInteger pn = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e;

        correctInput = false;

        do {
            System.out.print("Please enter a coprime with " + pn + " (Phi(n)) or 0 to exit");
            e = bigIntegerInput();
            try {
                if (e.compareTo(BigInteger.ZERO) < 0) {
                    if (e.compareTo(BigInteger.ZERO) == 0) {
                        System.out.println("\nReturning to menu\n");
                        return true;
                    } else throw new UnsupportedOperationException();
                }
                else if (e.compareTo(pn) > 0) throw new IllegalStateException();
                else if (!e.isProbablePrime(7)) throw new NumberFormatException();
                else if (pn.gcd(e).compareTo(BigInteger.ONE) != 0) throw new ArithmeticException();
                correctInput = true;
            } catch (UnsupportedOperationException f){
                System.out.println("You entered a negative number, try again");
            } catch (IllegalStateException f) {
                System.out.println("You entered a prime number bigger than Phi(n), try again\n" +
                        "Suggestion: " + n.subtract(pn).nextProbablePrime());
            } catch (NumberFormatException f) {
                System.out.println("You entered a non-prime value, try again\n" +
                        "Suggestion: " + e.nextProbablePrime());
            } catch (ArithmeticException f) {
                System.out.println("You entered a non-coprime value, try gain\n" +
                        "Suggestion: " + e.nextProbablePrime());
            }
        } while (!correctInput);

        try {
            FileWriter fw = new FileWriter("src/files/public_keys.txt");
            fw.write(n + "," + e);
            fw.close();
            System.out.println("\nThe public key has been saved in public_keys.txt file as n,e\n" +
                    "Where n is (p * q) = " + p + " * " + q + " | and e is " + e + "\n");
        } catch (IOException f) {
            System.out.println("An error occurred");
            f.printStackTrace();
        }

        return true;
    }
}
