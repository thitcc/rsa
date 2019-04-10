package ic.ufal.br.command.util;

import ic.ufal.br.command.ICommand;

public class ExitCommand implements ICommand {
    @Override
    public boolean execute() {
        System.out.println("System shutdown");
        return false;
    }
}
