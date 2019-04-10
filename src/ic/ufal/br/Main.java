package ic.ufal.br;

import ic.ufal.br.command.hub.InvokerHub;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("RSA Encryption: \n" +
                "1 - Generate Public Key\n" +
                "2 - Encrypt\n" +
                "3 - Decrypt\n" +
                "0 - Exit\n" +
                ">> ");

        Scanner input = new Scanner(System.in);

        InvokerHub hub = new InvokerHub(4);

        while (hub.onSelect(input.nextInt())) {

        }

    }
}
