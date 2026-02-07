package buddiboi.commands;

import buddiboi.ui.Ui;

/**
 * Command to handle invalid commands.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the InvalidCommand, displaying an error message.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public void execute(CommandContext context) {
        Ui.showMessage("Invalid command");
    }
}
