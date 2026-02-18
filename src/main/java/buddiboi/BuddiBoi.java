package buddiboi;

import java.util.ArrayList;

import buddiboi.commands.Command;
import buddiboi.exceptions.BuddiBoiException;
import buddiboi.exceptions.CommandException;
import buddiboi.exceptions.StorageException;
import buddiboi.parser.ParseCommand;
import buddiboi.storage.Storage;
import buddiboi.tasks.TaskList;
import buddiboi.ui.Ui;

/**
 * Main class for the BuddiBoi application.
 */
public class BuddiBoi {

    private final TaskList taskList;
    private boolean exitRequested = false;

    public BuddiBoi() {
        TaskList loadedTaskList;
        try {
            loadedTaskList = Storage.load();
        } catch (StorageException e) {
            System.err.println(Ui.showStorageError(e.getMessage()));
            System.err.println("Starting with empty task list.");
            loadedTaskList = new TaskList(new ArrayList<>());
        }
        this.taskList = loadedTaskList;
        assert taskList != null : "TaskList should never be null after loading from storage";
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            ParseCommand commandParser = new ParseCommand(input);
            Command command = commandParser.getCommand();
            assert command != null : "Parser should never return null command";
            String response = command.execute(taskList);
            assert response != null : "Command execution should always return a response";

            if (command.isExit()) {
                exitRequested = true;
            }

            return response;
        } catch (CommandException e) {
            return Ui.showCommandError(e.getMessage());
        } catch (BuddiBoiException e) {
            return Ui.showBuddiBoiError(e.getMessage());
        }
    }

    public boolean shouldExit() {
        return exitRequested;
    }

    public void save() {
        TaskList saveTaskList = this.taskList;
        try {
            Storage.save(saveTaskList);
        } catch (StorageException e) {
            System.err.println(Ui.showStorageError(e.getMessage()));
            System.err.println("Failed to save task list.");
        }
    }

    public String getWelcomeMessage() {
        return Ui.showWelcome();
    }
}
