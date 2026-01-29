public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.markTask(taskIndex);
        ui.showMarkTask(taskList.getTasks().get(taskIndex));
    }
}
