package buddiboi.commands;

import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

public class HelloCommand extends Command {

    /**
     * Executes the HelloCommand, displaying a greeting back to user.
     *
     * @param taskList The taskList containing the task list and other necessary information.
     */
    @Override
    public String execute(TaskList taskList) {
        return Ui.showHello();
    }
}
