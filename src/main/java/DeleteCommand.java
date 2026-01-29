public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.deleteTask(taskIndex);
        ui.showDeleteTask(taskList.getTasks().get(taskIndex), taskList.getItemCount());
    }
}