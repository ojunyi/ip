public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(String args) {
        super("mark " + args);
        this.taskNumber = Integer.parseInt(args.trim()) - 1;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
    
}
