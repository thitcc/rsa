package command.hub;

import command.ICommand;

import java.math.BigInteger;

import static treatment.BigIntInputHandling.bigIntegerInput;
import static treatment.FilesHandling.*;

public class DecryptCommand implements ICommand {

    @Override
    public boolean execute() {

        BigInteger p, q, e;

        System.out.print("Please enter \"p\":");
        p = bigIntegerInput();
        System.out.print("Please enter \"q\":");
        q = bigIntegerInput();
        System.out.print("Please enter \"e\":");
        e = bigIntegerInput();

        BigInteger n, phi, d, um;
        um = new BigInteger("1");

        n = p.multiply(q);
        phi = (p.subtract(um)).multiply(q.subtract(um));
        d = e.modInverse(phi);

        System.out.println("\nReading message...");
        StringBuilder content = new StringBuilder();
        String[] encryptedMessage = {};

        if (readFile("src/files/encrypted_message.txt", content)) {
            encryptedMessage = content.toString().split(",");
        }

        String decryptedMessage = "";
        for(String character: encryptedMessage) {
            decryptedMessage += (char) (new BigInteger(character).modPow(d, n).intValue() + 'A');
        }

        decryptedMessage = decryptedMessage.replaceAll("\\[", " ");
        if (writeFile("src/files/decrypted_message.txt", decryptedMessage)){
            System.out.println("\nThe decrypted message has been saved in decrypted_message.txt file");
        }

        return true;

    }
}
