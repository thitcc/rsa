package command.hub;

import command.ICommand;

import java.math.BigInteger;

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

        System.out.println("\nMessage:");
        String[] encryptedMessage = "205282708,5089002,21598992,28643815,0,184013150,5089002,205282708,38247466,0,115852397,160650536,149878655".split(",");

        String decryptedMessage = "";
        for(String character: encryptedMessage) {
            decryptedMessage += (char) (new BigInteger(character).modPow(d, n).intValue() + 'A');
        }

        System.out.println(decryptedMessage.replaceAll("\\[", " "));
        System.out.println("\nThe decrypted message has been saved in decrypted_message.txt file");

        return true;

    }
}
