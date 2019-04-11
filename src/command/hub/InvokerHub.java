package command.hub;

import command.Invoker;
import command.util.ExitCommand;

public class InvokerHub extends Invoker {

    public InvokerHub(int slots) {
        super(slots);
        super.setCmd(0, new ExitCommand());
        super.setCmd(1, new GeneratePublicKeyCommand());
    }
}
