package buddiboi.parser;

import buddiboi.commands.AddDeadlineCommand;
import buddiboi.commands.AddEventCommand;
import buddiboi.commands.AddTodoCommand;
import buddiboi.commands.Command;
import buddiboi.commands.DeleteCommand;
import buddiboi.commands.EchoCommand;
import buddiboi.commands.ExitCommand;
import buddiboi.commands.FindCommand;
import buddiboi.commands.InvalidCommand;
import buddiboi.commands.ListCommand;
import buddiboi.commands.MarkCommand;
import buddiboi.commands.UnmarkCommand;

/**
 * Parses user input into corresponding Command objects and executes them.
 */
public class ParseCommand {
    private final String command;
    private final String args;

    /**
     * Constructor for ParseCommand
     *
     * @param input Command to be parsed
     */
    public ParseCommand(String input) {
        String[] parsedInput = Parser.parse(input);
        this.command = parsedInput[0].toLowerCase();
        this.args = parsedInput.length > 1 ? parsedInput[1] : "";
    }

    /**
     * Returns the Command object corresponding to the parsed user input.
     *
     * @return The Command object to be executed.
     */
    public Command getCommand() {
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnmarkCommand(args);
        case "todo":
            return new AddTodoCommand(args);
        case "deadline":
            return new AddDeadlineCommand(args);
        case "event":
            return new AddEventCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "find":
            return new FindCommand(args);
        case "echo":
            String[] argArray = args.split(", ");
            return new EchoCommand(argArray);
        default:
            return new InvalidCommand();
        }
    }

    @Override
    public String toString() {
        return "> Command: " + command + " " + args;
    }
}
