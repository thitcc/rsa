package command.hub;

import command.ICommand;

import java.math.BigInteger;
import java.util.Scanner;

import static treatment.FilesHandling.*;

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

        BigInteger n, e;
        StringBuilder content = new StringBuilder();

        if (readFile("src/files/public_key.txt", content)) {
            String[] publicKey = content.toString().split(",");
            n = new BigInteger(publicKey[0]);
            e = new BigInteger(publicKey[1]);
        }
        else {
            return true;
        }

        System.out.println("\nUsing public key (" + n + "," + e + ") stored at public_key.txt\n" +
                "In order to change the default key, generate another one in the menu");

        char[] codedMessage = message.replace(' ','[').toCharArray();
        BigInteger[] encryptedMessage = new BigInteger[codedMessage.length];
        message = "";

        for (int i = 0; i < codedMessage.length; i++) {
            BigInteger character = BigInteger.valueOf(codedMessage[i] - 'A');
            encryptedMessage[i] = character.modPow(e, n);
            message += encryptedMessage[i] + ",";
        }

        if (writeFile("src/files/encrypted_message.txt", message.split(",$")[0]))
            System.out.println("\nThe encrypted message has been saved in encrypted_message.txt file\n");

        return true;
    }

}
