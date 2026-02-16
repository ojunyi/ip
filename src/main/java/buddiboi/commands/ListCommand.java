package buddiboi.commands;

import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand, displaying all tasks in the task list.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public String execute(TaskList taskList) {
        return Ui.showListCommand(taskList);
    }
}
