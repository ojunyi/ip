package buddiboi.commands;

import buddiboi.ui.Ui;

public class UnmarkCommand extends Command {
    private final String args;

    public UnmarkCommand(String args) {
        this.args = args;
    }

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
