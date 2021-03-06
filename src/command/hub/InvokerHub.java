package command.hub;

import command.Invoker;

public class InvokerHub extends Invoker {

    public InvokerHub(int slots) {
        super(slots);
        super.setCmd(0, new ExitCommand());
        super.setCmd(1, new GeneratePublicKeyCommand());
        super.setCmd(2, new EncryptCommand());
        super.setCmd(3, new DecryptCommand());
    }
}
