package ic.ufal.br;

import ic.ufal.br.command.hub.InvokerHub;

import static ic.ufal.br.treatment.IntInputHandling.intInput;

public class Main {

    public static void main(String[] args) {

        System.out.print("RSA Encryption: \n" +
                "1 - Generate Public Key\n" +
                "2 - Encrypt\n" +
                "3 - Decrypt\n" +
                "0 - Exit");

        InvokerHub hub = new InvokerHub(4);

        while (hub.onSelect(intInput())) {
            System.out.println("teste");
        }

    }
}
