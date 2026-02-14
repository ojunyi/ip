package buddiboi;

import buddiboi.commands.Command;
import buddiboi.commands.CommandContext;
import buddiboi.exceptions.BuddiBoiException;
import buddiboi.parser.ParseCommand;
import buddiboi.storage.Storage;
import buddiboi.tasks.TaskList;

/**
 * Main class for the BuddiBoi application.
 */
public class BuddiBoi {

    private final TaskList taskList;

    public BuddiBoi() {
        this.taskList = Storage.load();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            ParseCommand commandParser = new ParseCommand(input);
            Command command = commandParser.getCommand();

            String response = command.execute(new CommandContext(taskList));

            if (command.isExit()) {
                Storage.save(taskList.getTasks());
            }

            return response;

        } catch (BuddiBoiException e) {
            return e.getMessage();

        } catch (Exception e) {
            return "An unexpected error occurred. Please try again.";
        }
    }
}
