public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(CommandContext context) {
        context.taskList.markTask(taskIndex);
        context.ui.showMarkTask(context.taskList.getTasks().get(taskIndex));
    }
}
