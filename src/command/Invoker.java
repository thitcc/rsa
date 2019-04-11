package command;

import command.hub.ExitCommand;

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
        try {
            return this.cmd[slot].execute();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("This command: " + slot + " does not exist, please try again");
        }

        return true;
    }
}
