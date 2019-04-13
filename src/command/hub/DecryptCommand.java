package command.hub;

import command.ICommand;

import java.math.BigInteger;

import static treatment.BigIntInputHandling.bigIntegerInput;
import static treatment.FilesHandling.*;
import static treatment.GeneratePublicKeyHandling.*;

public class DecryptCommand implements ICommand {

    @Override
    public boolean execute() {

        BigInteger p, q, e;
        BigInteger n, pn, d;
        boolean correctInput;

        do {
            System.out.print("\nPlease enter value of \"p\" or 0 to exit");
            p = bigIntegerInput();

            System.out.print("Please enter value of \"q\" or 0 to exit");
            q = bigIntegerInput();

            if (p.compareTo(BigInteger.ZERO) == 0 || q.compareTo(BigInteger.ZERO) == 0) {
                System.out.println("\nReturning to menu\n");
                return true;
            }

            correctInput = primesTreatment(p, q);
        } while (!correctInput);

        n = p.multiply(q);
        pn = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        do {
            System.out.print("Please enter \"e\" or 0 to exit");
            e = bigIntegerInput();

            if (e.compareTo(BigInteger.ZERO) == 0) {
                System.out.println("\nReturning to menu\n");
                return true;
            }

            correctInput = coprimeTreatment(e, pn, n);
        } while (!correctInput);

        d = e.modInverse(pn);

        System.out.println("\nReading message...");
        StringBuilder content = new StringBuilder();
        String[] encryptedMessage;

        if (readFile("src/files/encrypted_message.txt", content))
            encryptedMessage = content.toString().split(",");
        else
            return true;


        String decryptedMessage = "";
        for(String character: encryptedMessage) {
            decryptedMessage += (char) (new BigInteger(character).modPow(d, n).intValue() + 'A');
        }

        decryptedMessage = decryptedMessage.replaceAll("\\[", " ");

        System.out.println("Decrypted Message:\n" + decryptedMessage);

        if (writeFile("src/files/decrypted_message.txt", decryptedMessage))
            System.out.println("\nThe decrypted message has been saved in decrypted_message.txt file\n");


        return true;
    }

}