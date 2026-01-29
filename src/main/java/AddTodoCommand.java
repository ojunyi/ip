public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Todo todo = new Todo(description);
        taskList.addTask(todo);
        ui.showAddTask(todo);
    }
}
