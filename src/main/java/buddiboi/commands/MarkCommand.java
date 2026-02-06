package buddiboi.commands;

import buddiboi.ui.Ui;

/**
 * Command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final String args;

    public MarkCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the MarkCommand, marking a task as completed in the task list.
     * 
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public void execute(CommandContext context) {
        if (args.trim().isEmpty() || !args.trim().matches("\\d+")) {
            Ui.showErrorMark();
            return;
        }
        
        int taskIndex = Integer.parseInt(args.trim()) - 1;
        if (taskIndex < 0 || taskIndex >= context.taskList.getTasks().size()) {
            Ui.showErrorMark();
            return;
        }
        context.taskList.markTask(taskIndex);
        Ui.showMarkTask(context.taskList.getTasks().get(taskIndex));
    }
}
