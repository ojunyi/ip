package buddiboi.commands;

import buddiboi.exceptions.CommandException;
import buddiboi.tasks.TaskList;
import buddiboi.tasks.Todo;
import buddiboi.ui.Ui;

/**
 * Command to add a todo task.
 */
public class AddTodoCommand extends Command {

    private String description;
    private String errorMessage;
    private final String format = "Format: todo <description>";

    public AddTodoCommand(String args) {
        parseArguments(args);
    }

    /**
     * Parses the command arguments and validates input.
     *
     * @param args The raw command arguments.
     */
    private void parseArguments(String args) {
        if (args == null || args.trim().isEmpty()) {
            this.errorMessage = "Todo must be populated with a description.";
        }

        this.description = args;
    }

    /**
     * Executes the AddTodoCommand, adding a new todo task to the task list.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public String execute(TaskList taskList) throws CommandException {
        if (errorMessage != null) {
            throw new CommandException(this.errorMessage + "\n" + format);
        }

        Todo todo = new Todo(description);
        taskList.addTask(todo);
        return Ui.showAddTask(todo);
    }
}
