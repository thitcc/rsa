package ic.ufal.br.command;

import ic.ufal.br.command.util.ExitCommand;

public class Invoker {

    private ICommand[] cmd;

    public Invoker(int slots) {
        this.cmd = new ICommand[slots];
        cmd[0] = new ExitCommand();
    }

    protected void setCmd(int slot, ICommand command) {
        this.cmd[slot] = command;
    }

    public boolean onSelect(int slot) {
        return this.cmd[slot].execute();
    }
}
