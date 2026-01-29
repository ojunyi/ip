package buddiboi.commands;

import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;
import java.util.Scanner;

public class CommandContext {
    public TaskList taskList;
    public Ui ui;
    public Scanner sc;

    public CommandContext(TaskList taskList, Ui ui, Scanner sc) {
        this.taskList = taskList;
        this.ui = ui;
        this.sc = sc;
    }
}
