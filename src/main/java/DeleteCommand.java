public class DeleteCommand extends Command {
    private String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(CommandContext context) {
        if (args.trim().isEmpty() || !args.trim().matches("\\d+")) {
            context.ui.showErrorDelete();
            return;
        }
        
        int taskIndex = Integer.parseInt(args.trim()) - 1;
        if (taskIndex < 0 || taskIndex >= context.taskList.getItemCount()) {
            context.ui.showErrorDelete();
            return;
        }
        context.ui.showDeleteTask(context.taskList.getTasks().get(taskIndex), context.taskList.getItemCount());
        context.taskList.deleteTask(taskIndex);
    }
}