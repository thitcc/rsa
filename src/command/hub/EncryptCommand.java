package command.hub;

import command.ICommand;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

import static treatment.BigIntInputHandling.bigIntegerInput;

public class EncryptCommand implements ICommand {

    @Override
    public boolean execute() {

        Scanner input = new Scanner(System.in);

        System.out.println("\nMessage:");
        String message = input.nextLine().toUpperCase();
        String invalidChars = message.replaceAll("[ A-Z]", "");

        if (!invalidChars.isEmpty()) {
            System.out.println("Message contains invalid characters");
            return false;
        }

        System.out.print("\nPublic Key (n):\n>> ");
        BigInteger publicKey = bigIntegerInput();

        System.out.print("Exponent (e):\n>> ");
        BigInteger exponent = bigIntegerInput();

        char[] codedMessage = message.replace(' ','[').toCharArray();
        BigInteger[] encryptedMessage = new BigInteger[codedMessage.length];
        message = "";

        for (int i = 0; i < codedMessage.length; i++) {
            BigInteger aux = BigInteger.valueOf(codedMessage[i] - 'A');
            encryptedMessage[i] = aux.modPow(exponent, publicKey);

            message  += encryptedMessage[i] + ",";
        }

        try {
            FileWriter encryptedFile = new FileWriter("src/files/encrypted_message.txt");
            encryptedFile.write(message.split(",$")[0]);
            encryptedFile.close();
            System.out.println("\nThe encrypted message has been saved in encrypted_message.txt file");
        } catch (IOException e) {
            System.out.println("An error ocurred.\n" + e.getMessage());
        }

        return true;
    }
}