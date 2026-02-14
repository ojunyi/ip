package buddiboi;

import java.util.Scanner;

import buddiboi.commands.Command;
import buddiboi.commands.CommandContext;
import buddiboi.exceptions.BuddiBoiException;
import buddiboi.parser.ParseCommand;
import buddiboi.storage.Storage;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * CLI app to test BuddiBoi using runtest.bat
 */
public class CliBuddiBoi {

    private static final String SAVE_CONFIRMATION = "yes";
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            TaskList taskList = Storage.load();
            assert taskList != null : "TaskList should never be null after loading from storage";

            System.out.println(Ui.showWelcome());

            while (true) {
                String input = scanner.nextLine().trim();
                ParseCommand commandParser = new ParseCommand(input);
                Command command = commandParser.getCommand();

                assert command != null : "Parser should never return null command";

                System.out.print(Ui.showCommand(input));
                String response = command.execute(new CommandContext(taskList));

                assert response != null : "Command execution should always return a response";

                System.out.println(response);

                if (command.isExit()) {
                    System.out.print(Ui.showCommand(input));
                    System.out.println(Ui.showMessage("Preparing to exit..."));
                    System.out.println(Ui.showMessage("Would you like me to save your tasks before exiting? (yes/no)"));

                    if (scanner.hasNextLine()) {
                        String saveChoice = scanner.nextLine().trim();
                        System.out.print(Ui.showCommand(saveChoice));

                        if (saveChoice.toLowerCase().equals(SAVE_CONFIRMATION)) {
                            System.out.println(Ui.showExitSaveCommand(true));
                            Storage.save(taskList.getTasks());
                        } else {
                            System.out.println(Ui.showExitSaveCommand(false));
                        }
                    } else {
                        System.out.println(Ui.showExitNoCommand());
                    }
                    break;
                }

                System.out.print(Ui.showCommand(input));
                System.out.println(command.execute(new CommandContext(taskList)));
            }
        } catch (BuddiBoiException e) {
            e.getMessage();
        }
    }
}
