public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(CommandContext context) {
        if (description.isEmpty()) {
            context.ui.showErrorAddTodo();
            return;
        }
        Todo todo = new Todo(description);
        context.taskList.addTask(todo);
        context.ui.showAddTask(todo);
    }
}
