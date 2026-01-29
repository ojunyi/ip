package buddiboi.commands;

public class ListCommand extends Command {

    @Override
    public void execute(CommandContext context) {
        context.ui.showMessage(context.taskList.toString());
    }
}
