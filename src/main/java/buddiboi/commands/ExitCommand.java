package buddiboi.commands;

import buddiboi.storage.Storage;
import buddiboi.ui.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand, prompting the user to save tasks before exiting.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public void execute(CommandContext context) {
        Ui.showMessage("Would you like me to save your tasks before exiting? (yes/no)");
        if (!context.getScanner().hasNextLine()) {
            Ui.showExitNoCommand();
            return;
        }

        String input = context.getScanner().nextLine();

        if (input.toLowerCase().equals("yes")) {
            Ui.showExitSaveCommand(true);
            Storage.save(context.getTaskList().getTasks());
        } else {
            Ui.showExitSaveCommand(false);
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
