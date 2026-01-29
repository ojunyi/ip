import java.util.Scanner;

public class CommandContext {
    public TaskList taskList;
    public Ui ui;
    public Storage storage;
    public Scanner sc;

    public CommandContext(TaskList taskList, Ui ui, Storage storage, Scanner sc) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
        this.sc = sc;
    }
}
