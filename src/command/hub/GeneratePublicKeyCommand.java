package command.hub;

import command.ICommand;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import static treatment.BigIntInputHandling.bigIntegerInput;
import static treatment.GeneratePublicKeyHandling.*;

public class GeneratePublicKeyCommand implements ICommand {
    @Override
    public boolean execute() {
        boolean correctInput;
        BigInteger p, q;

        do {
            System.out.print("Please enter the first prime or 0 to exit ");
            p = bigIntegerInput();

            System.out.print("Please enter the second prime or 0 to exit ");
            q = bigIntegerInput();

            if (p.compareTo(BigInteger.ZERO) == 0 || q.compareTo(BigInteger.ZERO) == 0) {
                System.out.println("\nReturning to menu\n");
                return true;
            }

            correctInput = primesTreatment(p, q);
        } while (!correctInput);

        BigInteger n = p.multiply(q);
        BigInteger pn = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e;

        do {
            System.out.print("Please enter a coprime with " + pn + " (Phi(n)) or 0 to exit");
            e = bigIntegerInput();

            if (e.compareTo(BigInteger.ZERO) == 0) {
                System.out.println("\nReturning to menu\n");
                return true;
            }

            correctInput = coprimeTreatment(e, pn, n);
        } while (!correctInput);

        try {
            FileWriter fw = new FileWriter("src/files/public_key.txt");
            fw.write(n + "," + e);
            fw.close();
            System.out.println("\nThe public key has been saved in public_key.txt file as n,e\n" +
                    "Where n is (p * q) = " + p + " * " + q + " = " + n + " | and e is " + e + "\n");
        } catch (IOException f) {
            System.out.println("An error occurred");
            f.printStackTrace();
        }

        return true;
    }
}
