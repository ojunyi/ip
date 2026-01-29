public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            ui.showErrorUnmark();
            return;
        }
        taskList.unmarkTask(taskIndex);
        ui.showUnmarkTask(taskList.getTasks().get(taskIndex));
    }
}
