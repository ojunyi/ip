package buddiboi.commands;

import buddiboi.ui.Ui;

/**
 * Command to unmark a task as not completed.
 */
public class UnmarkCommand extends Command {
    private final String args;

    public UnmarkCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the UnmarkCommand, unmarking a task as not completed in the task list.
     * 
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public void execute(CommandContext context) {
        if (args.trim().isEmpty() || !args.trim().matches("\\d+")) {
            Ui.showErrorUnmark();
            return;
        }
        
        int taskIndex = Integer.parseInt(args.trim()) - 1;
        if (taskIndex < 0 || taskIndex >= context.taskList.getTasks().size()) {
            Ui.showErrorUnmark();
            return;
        }
        context.taskList.unmarkTask(taskIndex);
        Ui.showUnmarkTask(context.taskList.getTasks().get(taskIndex));
    }
}
