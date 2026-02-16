package buddiboi.commands;

import buddiboi.exceptions.CommandException;
import buddiboi.tasks.Task;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * Command to finds and lists all tasks that contain the given keyword.
 */
public class FindCommand extends Command {

    private String description;
    private String errorMessage;
    private final String format = "Format: find <description>";

    public FindCommand(String args) {
        parseArguments(args);
    }

    /**
     * Parses the command arguments and validates input.
     *
     * @param args The raw command arguments.
     */
    private void parseArguments(String args) {
        if (args == null || args.trim().isEmpty()) {
            this.errorMessage = "Find must be populated with a description.";
            return;
        }

        this.description = args;
    }

    @Override
    public String execute(CommandContext context) throws CommandException {
        if (errorMessage != null) {
            throw new CommandException(this.errorMessage + " " + format);
        }

        TaskList matchingTasksList = new TaskList();
        for (Task task : context.getTaskList().getTasks()) {
            if (task.getDescription().contains(description)) {
                matchingTasksList.addTask(task);
            }
        }

        return Ui.showFindCommand(matchingTasksList);
    }
}
