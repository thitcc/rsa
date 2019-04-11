import command.hub.InvokerHub;

import static treatment.IntInputHandling.intInput;

public class Main {

    public static void main(String[] args) {

        InvokerHub hub = new InvokerHub(4);

        do {
            System.out.println("RSA Encryption Menu \n\n" +
                    "1 - Generate Public Key\n" +
                    "2 - Encrypt\n" +
                    "3 - Decrypt\n" +
                    "0 - Exit");
        } while (hub.onSelect(intInput()));

    }
}
