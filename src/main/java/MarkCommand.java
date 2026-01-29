public class MarkCommand extends Command {
    private String args;

    public MarkCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(CommandContext context) {
        if (args.trim().isEmpty() || !args.trim().matches("\\d+")) {
            context.ui.showErrorMark();
            return;
        }
        
        int taskIndex = Integer.parseInt(args.trim()) - 1;
        if (taskIndex < 0 || taskIndex >= context.taskList.getTasks().size()) {
            context.ui.showErrorMark();
            return;
        }
        context.taskList.markTask(taskIndex);
        context.ui.showMarkTask(context.taskList.getTasks().get(taskIndex));
    }
}
