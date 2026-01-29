package buddiboi.commands;

public class UnmarkCommand extends Command {
    private String args;

    public UnmarkCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(CommandContext context) {
        if (args.trim().isEmpty() || !args.trim().matches("\\d+")) {
            context.ui.showErrorUnmark();
            return;
        }
        
        int taskIndex = Integer.parseInt(args.trim()) - 1;
        if (taskIndex < 0 || taskIndex >= context.taskList.getTasks().size()) {
            context.ui.showErrorUnmark();
            return;
        }
        context.taskList.unmarkTask(taskIndex);
        context.ui.showUnmarkTask(context.taskList.getTasks().get(taskIndex));
    }
}
