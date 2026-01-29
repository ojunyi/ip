public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(CommandContext context) {
        context.taskList.unmarkTask(taskIndex);
        context.ui.showUnmarkTask(context.taskList.getTasks().get(taskIndex));
    }
}
