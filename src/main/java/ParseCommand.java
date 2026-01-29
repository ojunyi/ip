public class ParseCommand {
    private String command;
    private String args;

    public ParseCommand(String input) {
        String[] parsedInput = Parser.parse(input);
        this.command = parsedInput[0];
        this.args = parsedInput.length > 1 ? parsedInput[1] : "";
    }

    public Command getCommand() {
        switch (command.toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            try {
                int index = Integer.parseInt(args.trim()) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                return new InvalidCommand();
            }
        case "unmark":
            try {
                int index = Integer.parseInt(args.trim()) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                return new InvalidCommand();
            }
        case "todo":
            return new AddTodoCommand(args);
        case "deadline":
            return new AddDeadlineCommand(args);
        case "event":
            return new AddEventCommand(args);
        case "delete":
            try {
                int index = Integer.parseInt(args.trim()) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                return new InvalidCommand();
            }
        default:
            return new InvalidCommand();
        }
    }

    @Override
    public String toString() {
        return "> Command: " + command + " " + args;
    }
}