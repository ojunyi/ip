package buddiboi.parser;

import buddiboi.commands.AddDeadlineCommand;
import buddiboi.commands.AddEventCommand;
import buddiboi.commands.AddTodoCommand;
import buddiboi.commands.Command;
import buddiboi.commands.DeleteCommand;
import buddiboi.commands.ExitCommand;
import buddiboi.commands.InvalidCommand;
import buddiboi.commands.ListCommand;
import buddiboi.commands.MarkCommand;
import buddiboi.commands.UnmarkCommand;

public class ParseCommand {
    private final String command;
    private final String args;

    public ParseCommand(String input) {
        String[] parsedInput = Parser.parse(input);
        this.command = parsedInput[0].toLowerCase();
        this.args = parsedInput.length > 1 ? parsedInput[1] : "";
    }

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
        default:
            return new InvalidCommand();
        }
    }

    @Override
    public String toString() {
        return "> Command: " + command + " " + args;
    }
}