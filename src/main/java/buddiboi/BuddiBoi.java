package buddiboi;

import java.util.Scanner;

import buddiboi.commands.Command;
import buddiboi.commands.CommandContext;
import buddiboi.parser.ParseCommand;
import buddiboi.storage.Storage;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * Main class for the BuddiBoi application.
 */
public class BuddiBoi {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            TaskList taskList = Storage.load();
            
            Ui.showWelcome();
            
            while (true) {
                String input = scanner.nextLine().trim();
                ParseCommand commandParser = new ParseCommand(input);
                Command command = commandParser.getCommand();
                
                Ui.showCommand(input);
                command.execute(new CommandContext(taskList, scanner));
                
                if (command.isExit()) {
                    break;
                }
            }
        }
    }
}
