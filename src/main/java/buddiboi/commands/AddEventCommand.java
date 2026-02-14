package buddiboi.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import buddiboi.parser.NaturalDateParser;
import buddiboi.tasks.Event;
import buddiboi.ui.Ui;

/**
 * Command to add an event task.
 */
public class AddEventCommand extends Command {

    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an AddEventCommand with the given arguments.
     *
     * @param args The arguments containing the description, start time, and end time.
     */
    public AddEventCommand(String args) {
        String[] parts = args.split(" /from ");
        if (parts.length != 2) {
            this.description = "";
            return;
        }

        this.description = parts[0].trim();

        String[] subParts = parts[1].split(" /to ");
        if (subParts.length != 2) {
            from = null;
            to = null;
            return;
        }

        try {
            this.from = NaturalDateParser.parse(subParts[0].trim());
            this.to = NaturalDateParser.parse(subParts[1].trim());
        } catch (DateTimeParseException e) {
            this.from = null;
            this.to = null;
        }

        if (from != null && to != null && to.isBefore(from)) {
            from = null;
            to = null;
        }
    }

    /**
     * Executes the AddEventCommand, adding a new event task to the task list.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public String execute(CommandContext context) {
        if (description.isEmpty() || from == null || to == null) {
            return Ui.showErrorAddEvent();
        }
        Event event = new Event(description, from, to);
        context.getTaskList().addTask(event);
        return Ui.showAddTask(event);
    }
}
