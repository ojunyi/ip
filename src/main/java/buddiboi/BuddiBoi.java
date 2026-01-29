package buddiboi;

import buddiboi.commands.Command;
import buddiboi.commands.CommandContext;
import buddiboi.parser.ParseCommand;
import buddiboi.storage.Storage;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;
import java.util.Scanner;

public class BuddiBoi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        
        taskList = (TaskList) Storage.load();

        ui.showWelcome();

        while (true) { 
            String input = sc.nextLine().trim();
            ParseCommand parser = new ParseCommand(input);
            Command commandToExecute = parser.getCommand();
            
            ui.showCommand(input);
            commandToExecute.execute(new CommandContext(taskList, ui, sc));
            
            if (commandToExecute.isExit()) {
                break;
            }
        }

        sc.close();
    }
}
