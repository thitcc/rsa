package command.hub;

import command.ICommand;

import java.math.BigInteger;
import java.util.Scanner;

import static treatment.FilesHandling.writeFile;

public class EncryptCommand implements ICommand {
    
    @Override
    public boolean execute() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPlease enter the message or 0 to exit");
        String message = input.nextLine().toUpperCase();
        String invalidChars = message.replaceAll("[ A-Z]", "");

        if (invalidChars.equals("0")){
            System.out.println("\nReturning to menu\n");
            return true;
        } else if(!invalidChars.isEmpty()) {
            System.out.println("Message contains invalid characters\nTry again");
            return execute();
        }

        System.out.print("\nPublic Key (n):\n>> ");
        BigInteger n = input.nextBigInteger();

        System.out.print("Exponent (e):\n>> ");
        BigInteger e = input.nextBigInteger();

        char[] codedMessage = message.replace(' ','[').toCharArray();
        BigInteger[] encryptedMessage = new BigInteger[codedMessage.length];
        message = "";

        for (int i = 0; i < codedMessage.length; i++) {
            BigInteger character = BigInteger.valueOf(codedMessage[i] - 'A');
            encryptedMessage[i] = character.modPow(e, n);

            message += encryptedMessage[i] + ",";
        }

        if (writeFile("src/files/encrypted_message.txt", message.split(",$")[0])) {
            System.out.println("\nThe encrypted message has been saved in encrypted_message.txt file");
        }

        return true;
    }

}
