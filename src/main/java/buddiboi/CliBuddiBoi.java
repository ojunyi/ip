package buddiboi;

import java.util.Scanner;

import buddiboi.commands.Command;
import buddiboi.exceptions.BuddiBoiException;
import buddiboi.exceptions.CommandException;
import buddiboi.exceptions.StorageException;
import buddiboi.parser.ParseCommand;
import buddiboi.storage.Storage;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * CLI app to test BuddiBoi using runtest.bat
 */
public class CliBuddiBoi {

    private static final String SAVE_CONFIRMATION = "yes";
    // Ease of building response for testing purposes only
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            run(scanner);
        } catch (BuddiBoiException e) {
            sb.append(Ui.showBuddiBoiError(e.getMessage()));
        }
    }

    private static void run(Scanner scanner) throws BuddiBoiException {
        TaskList taskList = Storage.load();
        assert taskList != null : "TaskList should never be null after loading from storage";

        sb.append(Ui.showWelcome());

        while (true) {
            String input = scanner.nextLine().trim();
            ParseCommand commandParser = new ParseCommand(input);
            Command command = commandParser.getCommand();

            assert command != null : "Parser should never return null command";

            if (handleCommand(scanner, sb, taskList, command, input)) {
                break;
            }
        }

        System.out.print(sb.toString());
    }

    private static boolean handleCommand(Scanner scanner, StringBuilder sb, TaskList taskList,
            Command command, String input) {

        try {
            sb.append(Ui.showCommand(input));
            String response = command.execute(taskList);
            sb.append(response);

            if (command.isExit()) {
                handleExit(scanner, sb, taskList);
                return true;
            }

            return false;

        } catch (CommandException e) {
            sb.append(Ui.showCommandError(e.getMessage()));
            return false;
        }
    }

    private static void handleExit(Scanner scanner, StringBuilder sb, TaskList taskList) {
        if (!scanner.hasNextLine()) {
            sb.append(Ui.showExitNoCommand());
            return;
        }

        String saveChoice = scanner.nextLine().trim();
        sb.append(Ui.showCommand(saveChoice));

        if (saveChoice.toLowerCase().equals(SAVE_CONFIRMATION)) {
            sb.append(Ui.showSaveConfirmation(true));
            TaskList saveTaskList = taskList;
            try {
                Storage.save(saveTaskList);
            } catch (StorageException e) {
                System.err.println(Ui.showStorageError(e.getMessage()));
                System.err.println("Failed to save task list.");
            }
        } else {
            sb.append(Ui.showSaveConfirmation(false));
        }
    }
}
