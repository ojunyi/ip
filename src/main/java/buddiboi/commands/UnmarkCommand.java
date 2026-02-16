package buddiboi.commands;

import buddiboi.exceptions.CommandException;
import buddiboi.ui.Ui;

/**
 * Command to unmark a task as not completed.
 */
public class UnmarkCommand extends Command {

    private int taskIndex;
    private String errorMessage;
    private final String format = "Format: unmark <task number>";

    public UnmarkCommand(String args) {
        parseArguments(args);
    }

    /**
     * Parses the command arguments and validates input.
     *
     * @param args The raw command arguments.
     */
    private void parseArguments(String args) {
        if (args == null || args.trim().isEmpty() || !isIntegerRegex(args)) {
            this.errorMessage = "Unmark must be populated with a integer.";
            return;
        }

        this.taskIndex = Integer.parseInt(args.trim()) - 1;
    }
    /**
     * Executes the UnmarkCommand, unmarking a task as not completed in the task list.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public String execute(CommandContext context) throws CommandException {
        if (errorMessage != null) {
            throw new CommandException(this.errorMessage + " " + format);
        }

        if (taskIndex < 0 || taskIndex >= context.getTaskList().getItemCount()) {
            this.errorMessage = "Unmark must be populated with an integer inside the existing list.";
            throw new CommandException(this.errorMessage + " " + format);
        }

        context.getTaskList().unmarkTask(taskIndex);
        return Ui.showUnmarkTask(context.getTaskList().getTasks().get(taskIndex));
    }
}
