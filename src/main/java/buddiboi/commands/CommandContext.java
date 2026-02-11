package buddiboi.commands;

import buddiboi.tasks.TaskList;

/**
 * Context for executing commands, containing necessary information such as the task list and scanner.
 */
public class CommandContext {
    private final TaskList taskList;

    /**
     * Constructor for Command Context
     *
     * @param taskList Task list context for commands
     */
    public CommandContext(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
