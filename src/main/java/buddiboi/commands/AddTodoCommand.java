package buddiboi.commands;

import buddiboi.tasks.Todo;
import buddiboi.ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(CommandContext context) {
        if (description.isEmpty()) {
            Ui.showErrorAddTodo();
            return;
        }
        Todo todo = new Todo(description);
        context.taskList.addTask(todo);
        Ui.showAddTask(todo);
    }
}
