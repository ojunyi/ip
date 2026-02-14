package buddiboi;

import buddiboi.commands.Command;
import buddiboi.commands.CommandContext;
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
            String response = command.execute(new CommandContext(taskList));
            assert response != null : "Command execution should always return a response";

            if (command.isExit()) {
                Storage.save(taskList.getTasks());
            }

            return response;

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
