package command.util;

import command.ICommand;

public class ExitCommand implements ICommand {
    @Override
    public boolean execute() {
        System.out.println("System shutdown");
        return false;
    }
}
