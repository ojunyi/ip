package buddiboi;

import java.util.Scanner;

import buddiboi.commands.Command;
import buddiboi.commands.CommandContext;
import buddiboi.parser.ParseCommand;
import buddiboi.storage.Storage;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

public class BuddiBoi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        
        taskList = Storage.load();

        ui.showWelcome();

        while (true) { 
            String input = scanner.nextLine().trim();
            ParseCommand commandParser = new ParseCommand(input);
            Command command = commandParser.getCommand();
            
            ui.showCommand(input);
            command.execute(new CommandContext(taskList, ui, scanner));
            
            if (command.isExit()) {
                break;
            }
        }

        scanner.close();
    }
}
