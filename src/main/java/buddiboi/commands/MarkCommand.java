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
    public String execute(CommandContext context) {
        if (args.trim().isEmpty() || !args.trim().matches("\\d+")) {
            return Ui.showErrorMark();
        }

        int taskIndex = Integer.parseInt(args.trim()) - 1;
        if (taskIndex < 0 || taskIndex >= context.getTaskList().getTasks().size()) {
            return Ui.showErrorMark();
        }
        context.getTaskList().markTask(taskIndex);
        return Ui.showMarkTask(context.getTaskList().getTasks().get(taskIndex));
    }
}
