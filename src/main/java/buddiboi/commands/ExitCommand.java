package buddiboi.commands;

import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand, prompting the user to save tasks before exiting.
     *
     * @param taskList The taskList containing the task list and other necessary information.
     */
    @Override
    public String execute(TaskList taskList) {
        return Ui.showExitSaveCommand();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
