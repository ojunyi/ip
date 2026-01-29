public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String args) {
        String[] parts = args.split(" /from ", 2);
        if (parts.length == 2) {
            this.description = parts[0].trim();
            String[] subParts = parts[1].split(" /to ", 2);
            if (subParts.length == 2) {
                this.from = subParts[0].trim();
                this.to = subParts[1].trim();
            } else {
                this.from = "";
                this.to = "";
            }
        } else {
            this.description = "";
            this.from = "";
            this.to = "";
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Event event = new Event(description, from, to);
        taskList.addTask(event);
        ui.showAddTask(event);
    }
}