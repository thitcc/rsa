package command.hub;

import command.ICommand;

import java.math.BigInteger;
import java.util.Scanner;

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
        BigInteger publicKey = input.nextBigInteger();

        System.out.print("Exponent (e):\n>> ");
        BigInteger exponent = input.nextBigInteger();

        char[] codedMessage = message.replace(' ','[').toCharArray();
        BigInteger[] encryptedMessage = new BigInteger[codedMessage.length];
        message = "";

        for (int i = 0; i < codedMessage.length; i++) {
            BigInteger aux = BigInteger.valueOf(codedMessage[i] - 'A');
            encryptedMessage[i] = aux.modPow(exponent, publicKey);

            message += encryptedMessage[i] + ",";
        }

        System.out.println("Encrypted message: " + message.split(",$")[0]);

        System.out.println("\nThe encrypted message has been saved in encrypted_message.txt file");

        return true;
    }

}
