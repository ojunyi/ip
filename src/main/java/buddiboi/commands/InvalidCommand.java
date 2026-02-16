package buddiboi.commands;

import buddiboi.exceptions.CommandException;

/**
 * Command to handle invalid commands.
 */
public class InvalidCommand extends Command {

    private final String INVALID_COMMAND = "Invalid Command";

    /**
     * Executes the InvalidCommand, displaying an error message.
     *
     * @param context The command context containing the task list and other necessary information.
     */
    @Override
    public String execute(CommandContext context) throws CommandException {
        throw new CommandException(INVALID_COMMAND);
    }
}
