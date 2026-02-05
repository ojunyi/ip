package buddiboi.commands;

import java.util.Scanner;

import buddiboi.tasks.TaskList;

/**
 * Context for executing commands, containing necessary information such as the task list and scanner.
 */
public class CommandContext {
    public TaskList taskList;
    public Scanner sc;

    public CommandContext(TaskList taskList, Scanner sc) {
        this.taskList = taskList;
        this.sc = sc;
    }
}
