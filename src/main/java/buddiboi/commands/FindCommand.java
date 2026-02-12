package buddiboi.commands;

import buddiboi.tasks.Task;
import buddiboi.ui.Ui;

/**
 * Command to finds and lists all tasks that contain the given keyword.
 */
public class FindCommand extends Command {

    private final String args;

    public FindCommand(String args) {
        this.args = args;
    }

    @Override
    public String execute(CommandContext context) {
        int count = 0;
        StringBuilder taskList = new StringBuilder();
        taskList.append("Here are the matching tasks in your list:\n");
        for (Task task : context.getTaskList().getTasks()) {
            if (task.getDescription().contains(args)) {
                taskList.append(count)
                        .append(". ")
                        .append(task.toString()).append("\n");
                count++;
            }
        }

        if (count == 0) {
            taskList.append("No matching tasks found");
        }

        return Ui.showMessage(taskList.toString());
    }
}
