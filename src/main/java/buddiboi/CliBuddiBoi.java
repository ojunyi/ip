package buddiboi;

import java.util.Scanner;

import buddiboi.commands.Command;
import buddiboi.exceptions.BuddiBoiException;
import buddiboi.exceptions.CommandException;
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
            StringBuilder stringBuilder = new StringBuilder();
            TaskList taskList = Storage.load();
            assert taskList != null : "TaskList should never be null after loading from storage";

            stringBuilder.append(Ui.showWelcome());

            while (true) {
                String input = scanner.nextLine().trim();
                ParseCommand commandParser = new ParseCommand(input);
                Command command = commandParser.getCommand();

                assert command != null : "Parser should never return null command";

                try {
                    System.out.print(Ui.showCommand(input));
                    String response = command.execute(taskList);
                    System.out.println(response);

                    if (command.isExit()) {

                        if (scanner.hasNextLine()) {
                            String saveChoice = scanner.nextLine().trim();
                            System.out.print(Ui.showCommand(saveChoice));

                            if (saveChoice.toLowerCase().equals(SAVE_CONFIRMATION)) {
                                System.out.println(Ui.showSaveConfirmation(true));
                                Storage.save(taskList.getTasks());
                            } else {
                                System.out.println(Ui.showSaveConfirmation(false));
                            }
                        } else {
                            System.out.println(Ui.showExitNoCommand());
                        }
                        break;
                    }

                } catch (CommandException e) {
                    System.out.println(Ui.showCommandError(e.getMessage()));
                } catch (Exception e) {
                    Ui.showError("An unexpected error occurred");
                    e.printStackTrace();
                }
            }
        } catch (BuddiBoiException e) {
            e.getMessage();
        }
    }
}
