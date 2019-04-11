package command;

public class Invoker {

    private ICommand[] cmd;

    public Invoker(int slots) {
        this.cmd = new ICommand[slots];
    }

    protected void setCmd(int slot, ICommand command) {
        try {
            this.cmd[slot] = command;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("This index: " + slot + " is out of bounds, please try again");
        }
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
