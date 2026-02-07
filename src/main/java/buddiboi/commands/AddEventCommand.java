package buddiboi.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import buddiboi.tasks.Event;
import buddiboi.ui.Ui;

/**
 * Command to add an event task.
 */
public class AddEventCommand extends Command {

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

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
        }

        try {
            this.from = LocalDateTime.parse(subParts[0].trim(), INPUT_FORMAT);
            this.to = LocalDateTime.parse(subParts[1].trim(), INPUT_FORMAT);
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
    public void execute(CommandContext context) {
        if (description.isEmpty() || from == null || to == null) {
            Ui.showErrorAddEvent();
            return;
        }
        Event event = new Event(description, from, to);
        context.getTaskList().addTask(event);
        Ui.showAddTask(event);
    }
}
