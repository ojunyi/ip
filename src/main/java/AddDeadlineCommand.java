public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String args) {
        String[] parts = args.split(" /by ", 2);
        if (parts.length == 2) {
            this.description = parts[0].trim();
            this.by = parts[1].trim();
        } else {
            this.description = "";
            this.by = "";
        }
    }

    @Override
    public void execute(CommandContext context) {
        Deadline deadline = new Deadline(description, by);
        context.taskList.addTask(deadline);
        context.ui.showAddTask(deadline);
    }
    
}
