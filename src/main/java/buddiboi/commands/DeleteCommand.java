package buddiboi.commands;

import buddiboi.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the DeleteCommand, removing a task from the task list.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public void execute(CommandContext context) {
        if (args.trim().isEmpty() || !args.trim().matches("\\d+")) {
            Ui.showErrorDelete();
            return;
        }

        int taskIndex = Integer.parseInt(args.trim()) - 1;
        if (taskIndex < 0 || taskIndex >= context.getTaskList().getItemCount()) {
            Ui.showErrorDelete();
            return;
        }
        Ui.showDeleteTask(context.getTaskList().getTasks().get(taskIndex),
                context.getTaskList().getItemCount());
        context.getTaskList().deleteTask(taskIndex);
    }
}
