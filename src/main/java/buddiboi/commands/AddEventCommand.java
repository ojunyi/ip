package buddiboi.commands;

import buddiboi.tasks.Event;

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String args) {
        String[] parts = args.split(" /from ");
        if (parts.length == 2) {
            this.description = parts[0].trim();
            String[] subParts = parts[1].split(" /to ");
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
    public void execute(CommandContext context) {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            context.ui.showErrorAddEvent();
            return;
        }
        Event event = new Event(description, from, to);
        context.taskList.addTask(event);
        context.ui.showAddTask(event);
    }
}