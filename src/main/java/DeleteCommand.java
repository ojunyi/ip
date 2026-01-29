public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(CommandContext context) {
        context.taskList.deleteTask(taskIndex);
        context.ui.showDeleteTask(context.taskList.getTasks().get(taskIndex),
                context.taskList.getItemCount());
    }
}