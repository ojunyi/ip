package buddiboi.commands;

import buddiboi.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(CommandContext context) {
        Ui.showMessage(context.taskList.toString());
    }
}
