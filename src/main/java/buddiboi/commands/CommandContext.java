package buddiboi.commands;

import java.util.Scanner;

import buddiboi.tasks.TaskList;

/**
 * Context for executing commands, containing necessary information such as the task list and scanner.
 */
public class CommandContext {
    private final TaskList taskList;
    private final Scanner scanner;

    /**
     * Constructor for Command Context
     *
     * @param taskList Task list context for commands
     * @param scanner Scanner context for commands
     */
    public CommandContext(TaskList taskList, Scanner scanner) {
        this.taskList = taskList;
        this.scanner = scanner;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
