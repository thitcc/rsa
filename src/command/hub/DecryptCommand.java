package command.hub;

import command.ICommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

import static treatment.BigIntInputHandling.bigIntegerInput;

public class DecryptCommand implements ICommand {

    @Override
    public boolean execute() {

        BigInteger p, q, e;

        System.out.print("Please enter \"p\" ");
        p = bigIntegerInput();
        System.out.print("Please enter \"q\" ");
        q = bigIntegerInput();
        System.out.print("Please enter \"e\" ");
        e = bigIntegerInput();

        BigInteger n, phi, d, um;
        um = new BigInteger("1");

        n = p.multiply(q);
        phi = (p.subtract(um)).multiply(q.subtract(um));
        d = e.modInverse(phi);

        System.out.println("\nReading message");
        String[] encryptedMessage = {};
        try {
            File encryptedFile = new File("src/files/encrypted_message.txt");
            Scanner messageReader = new Scanner(encryptedFile);

            while (messageReader.hasNextLine()) {
                encryptedMessage = messageReader.nextLine().split(",");
            }

            messageReader.close();
        } catch (FileNotFoundException f) {
            System.out.println("An error ocurred.\n" + f.getMessage());
        }


        String decryptedMessage = "";
        for(String character: encryptedMessage) {
            decryptedMessage += (char) (new BigInteger(character).modPow(d, n).intValue() + 'A');
        }

        try {
            FileWriter decryptedFile = new FileWriter("src/files/decrypted_message.txt");
            decryptedFile.write(decryptedMessage.replaceAll("\\[", " "));
            decryptedFile.close();
            System.out.println("\nThe decrypted message has been saved in decrypted_message.txt file");
        } catch (IOException f) {
            System.out.println("An error ocurred.\n" + f.getMessage());
        }

        return true;
    }
}