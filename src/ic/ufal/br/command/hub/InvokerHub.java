package ic.ufal.br.command.hub;

import ic.ufal.br.command.Invoker;
import ic.ufal.br.command.util.ExitCommand;

public class InvokerHub extends Invoker {

    public InvokerHub(int slots) {
        super(slots);
        super.setCmd(0, new ExitCommand());
    }
}
