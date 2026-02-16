package buddiboi.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import buddiboi.exceptions.CommandException;
import buddiboi.parser.NaturalDateParser;
import buddiboi.tasks.Event;
import buddiboi.ui.Ui;

/**
 * Command to add an event task.
 */
public class AddEventCommand extends Command {

    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private String errorMessage;
    private final String format = "Format: event <description> /from <start date> /to <end date>";

    /**
     * Constructs an AddEventCommand with the given arguments.
     *
     * @param args The arguments containing the description, start time, and end time.
     */
    public AddEventCommand(String args) {
        parseArguments(args);
    }

    /**
     * Parses the command arguments and validates input.
     *
     * @param args The raw command arguments.
     */
    private void parseArguments(String args) {
        if (args == null || args.trim().isEmpty()) {
            this.errorMessage = "Event must be populated with a description, a start date and an end date.";
            return;
        }

        if (!args.contains(" /from ")) {
            this.errorMessage = "Event must specify a start date using ' /from '.";
            return;
        }

        if (!args.contains(" /to ")) {
            this.errorMessage = "Event must specify an end date using ' /to '.";
            return;
        }

        int fromIndex = args.indexOf(" /from ");
        int toIndex = args.indexOf(" /to ");
        if (toIndex < fromIndex) {
            this.errorMessage = "Event must specify ' /from ' before ' /to '.";
            return;
        }

        String[] parts = args.split(" /from ", -1);

        if (parts.length > 2) {
            this.errorMessage = "Event must specify an event using no more than one ' /from '.";
            return;
        }

        this.description = parts[0].trim();
        String remainder = parts[1].trim();

        if (description.isEmpty()) {
            this.errorMessage = "Event must be populated with a description.";
            return;
        }

        String[] subParts = remainder.split(" /to ", -1);

        if (subParts.length > 2) {
            this.errorMessage = "Event must specify an event using no more than one ' /to '.";
            return;
        }

        String startDateString = subParts[0].trim();
        String endDateString = subParts[1].trim();

        if (startDateString.isEmpty()) {
            this.errorMessage = "Event must be populated with a start date.";
            return;
        }

        if (endDateString.isEmpty()) {
            this.errorMessage = "Event must be populated with a end date.";
            return;
        }

        try {
            this.start = NaturalDateParser.parse(subParts[0].trim());
            this.end = NaturalDateParser.parse(subParts[1].trim());

            if (this.start.isAfter(this.end)) {
                this.errorMessage = "Event start date must be before the end date.";
            }
        } catch (DateTimeParseException e) {
            this.errorMessage = "Event date format is invalid.";
        }
    }

    /**
     * Executes the AddEventCommand, adding a new event task to the task list.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public String execute(CommandContext context) throws CommandException {
        if (errorMessage != null) {
            throw new CommandException(this.errorMessage + " " + format);
        }

        Event event = new Event(description, start, end);
        context.getTaskList().addTask(event);
        return Ui.showAddTask(event);
    }
}
