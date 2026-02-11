package buddiboi.commands;

import buddiboi.tasks.Todo;
import buddiboi.ui.Ui;

/**
 * Command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddTodoCommand, adding a new todo task to the task list.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public String execute(CommandContext context) {
        if (description.isEmpty()) {
            return Ui.showErrorAddTodo();
        }
        Todo todo = new Todo(description);
        context.getTaskList().addTask(todo);
        return Ui.showAddTask(todo);
    }
}
