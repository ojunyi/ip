package buddiboi.commands;

import buddiboi.exceptions.CommandException;
import buddiboi.tasks.Task;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {

    private int taskIndex;
    private String errorMessage;
    private final String format = "Format: delete <task number>";

    public DeleteCommand(String args) {
        parseArguments(args);
    }

    /**
     * Parses the command arguments and validates input.
     *
     * @param args The raw command arguments.
     */
    private void parseArguments(String args) {
        if (args == null || args.trim().isEmpty() || !isIntegerRegex(args)) {
            this.errorMessage = "Delete must be populated with a integer.";
            return;
        }

        this.taskIndex = Integer.parseInt(args.trim()) - 1;
    }

    /**
     * Executes the DeleteCommand, removing a task from the task list.
     *
     * @param taskList The taskList containing the task list and other necessary information.
     */
    @Override
    public String execute(TaskList taskList) throws CommandException {
        if (errorMessage != null) {
            throw new CommandException(this.errorMessage + "\n" + format);
        }

        if (taskIndex < 0 || taskIndex >= taskList.getItemCount()) {
            this.errorMessage = "Delete must be populated with an integer inside the existing list.";
            throw new CommandException(this.errorMessage + "\n" + format);
        }

        Task deletedTask = taskList.getTasks().get(taskIndex);
        taskList.deleteTask(taskIndex);
        return Ui.showDeleteTask(deletedTask, taskList.getItemCount());
    }
}
