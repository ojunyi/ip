package buddiboi.commands;

import buddiboi.exceptions.CommandException;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * Command to mark a task as completed.
 */
public class MarkCommand extends Command {

    private int taskIndex;
    private String errorMessage;
    private final String format = "Format: mark <task number>";

    public MarkCommand(String args) {
        parseArguments(args);
    }

    /**
     * Parses the command arguments and validates input.
     *
     * @param args The raw command arguments.
     */
    private void parseArguments(String args) {
        if (args == null || args.trim().isEmpty() || !isIntegerRegex(args)) {
            this.errorMessage = "Mark must be populated with a integer.";
            return;
        }

        this.taskIndex = Integer.parseInt(args.trim()) - 1;
    }

    /**
     * Executes the MarkCommand, marking a task as completed in the task list.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public String execute(TaskList taskList) throws CommandException{
        if (errorMessage != null) {
            throw new CommandException(this.errorMessage + "\n" + format);
        }

        if (taskIndex < 0 || taskIndex >= taskList.getItemCount()) {
            this.errorMessage = "Mark must be populated with an integer inside the existing list.";
            throw new CommandException(this.errorMessage + "\n" + format);
        }

        taskList.markTask(taskIndex);
        return Ui.showMarkTask(taskList.getTasks().get(taskIndex));
    }
}
