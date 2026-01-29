public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.unmarkTask(taskIndex);
        ui.showUnmarkTask(taskList.getTasks().get(taskIndex));
    }
}
