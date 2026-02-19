package buddiboi.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import buddiboi.exceptions.CommandException;
import buddiboi.parser.NaturalDateParser;
import buddiboi.tasks.Deadline;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {

    private String description;
    private LocalDateTime by;
    private String errorMessage;
    private final String format = "Format: deadline <description> /by <date>";

    /**
     * Constructs an AddDeadlineCommand with the given arguments.
     *
     * @param args The arguments containing the description and deadline.
     */
    public AddDeadlineCommand(String args) {
        parseArguments(args);
    }

    /**
     * Parses the command arguments and validates input.
     *
     * @param args The raw command arguments.
     */
    private void parseArguments(String args) {
        if (args == null || args.trim().isEmpty()) {
            this.errorMessage = "Deadline must be populated with a description and a date.";
            return;
        }

        if (!args.contains(" /by ")) {
            this.errorMessage = "Deadline must specify a date using ' /by '.";
            return;
        }

        String[] parts = args.split(" /by ", -1);

        if (parts.length > 2) {
            this.errorMessage = "Deadline must specify a deadline using no more than one ' /by '.";
            return;
        }

        this.description = parts[0].trim();
        String dateString = parts[1].trim();

        if (description.isEmpty()) {
            this.errorMessage = "Deadline must be populated with a description.";
            return;
        }

        if (dateString.isEmpty()) {
            this.errorMessage = "Deadline must be populated with a date.";
            return;
        }

        try {
            this.by = NaturalDateParser.parse(dateString);
        } catch (DateTimeParseException e) {
            this.errorMessage = "Deadline date format is invalid.";
            this.by = null;
        }
    }


    /**
     * Executes the AddDeadlineCommand, adding a new deadline task to the task list.
     *
     * @param taskList The taskList containing the task list and other necessary information.
     */
    @Override
    public String execute(TaskList taskList) throws CommandException {
        if (errorMessage != null) {
            throw new CommandException(this.errorMessage + "\n" + format);
        }

        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        return Ui.showAddTask(deadline, taskList.getItemCount());
    }

}
