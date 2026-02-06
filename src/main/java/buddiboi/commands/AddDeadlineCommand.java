package buddiboi.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import buddiboi.tasks.Deadline;
import buddiboi.ui.Ui;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructs an AddDeadlineCommand with the given arguments.
     *
     * @param args The arguments containing the description and deadline.
     */
    public AddDeadlineCommand(String args) {
        String[] parts = args.split(" /by ");
        if (parts.length != 2) {
            this.description = "";
            return;
        }

        this.description = parts[0].trim();

        try {
            this.by = LocalDateTime.parse(parts[1].trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            this.by = null;
        }
    }

    /**
     * Executes the AddDeadlineCommand, adding a new deadline task to the task list.
     * 
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public void execute(CommandContext context) {
        if (description.isEmpty() || by == null) {
            Ui.showErrorAddDeadline();
            return;
        }
        Deadline deadline = new Deadline(description, by);
        context.taskList.addTask(deadline);
        Ui.showAddTask(deadline);
    }
    
}
