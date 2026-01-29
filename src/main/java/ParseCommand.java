public class ParseCommand {
    private String command;
    private String args;
    private String[] parsedInput;

    public ParseCommand(String input) {
        this.parsedInput = Parser.parse(input);

        this.command = parsedInput[0];

        if (parsedInput.length > 1) {
            this.args = parsedInput[1];
        } else {
            this.args = "";
        }
    }

    public boolean isGoodBye() {
        return command.toLowerCase().equals("bye");
    }

    public boolean isList() {
        return command.toLowerCase().equals("list");
    }

    public boolean isMark() {
        return command.toLowerCase().equals("mark");
    }

    public boolean isUnmark() {
        return command.toLowerCase().equals("unmark");
    }

    public boolean isTodo() {
        return command.toLowerCase().equals("todo");
    }

    public boolean isDeadline() {
        return command.toLowerCase().equals("deadline");
    }

    public boolean isEvent() {
        return command.toLowerCase().equals("event");
    }

    public boolean isDelete() {
        return command.toLowerCase().equals("delete");
    }

    @Override
    public String toString() {
        return "> Command: " + command + " " + args;
    }
}