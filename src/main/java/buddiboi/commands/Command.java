package buddiboi.commands;

import buddiboi.exceptions.CommandException;
import buddiboi.tasks.TaskList;

/**
 * Abstract base class to be used for all commands.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList) throws CommandException;

    public boolean isExit() {
        return false;
    }

    /**
     * Checks if a string represents a valid integer.
     *
     * @param args The string to check.
     * @return true if the string is a valid integer, false otherwise.
     */
    protected static boolean isIntegerRegex(String args) {
        if (args == null) {
            return false;
        }
        return args.matches("-?\\d+");
    }
}
