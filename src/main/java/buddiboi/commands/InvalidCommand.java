package buddiboi.commands;

import buddiboi.ui.Ui;

public class InvalidCommand extends Command {

    @Override
    public void execute(CommandContext context) {
        Ui.showMessage("Invalid command");
    }
}
