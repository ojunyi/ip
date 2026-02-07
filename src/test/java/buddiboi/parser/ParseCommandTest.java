package buddiboi.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import buddiboi.commands.AddTodoCommand;
import buddiboi.commands.ExitCommand;
import buddiboi.commands.InvalidCommand;
import buddiboi.commands.ListCommand;

public class ParseCommandTest {

    @Test
    public void getCommand_byeInput_returnsExitCommand() {
        ParseCommand parseCommand = new ParseCommand("bye");
        assertTrue(parseCommand.getCommand() instanceof ExitCommand);
    }

    @Test
    public void getCommand_listInput_returnsListCommand() {
        ParseCommand parseCommand = new ParseCommand("list");
        assertTrue(parseCommand.getCommand() instanceof ListCommand);
    }

    @Test
    public void getCommand_todoInput_returnsAddTodoCommand() {
        ParseCommand parseCommand = new ParseCommand("todo read book");
        assertTrue(parseCommand.getCommand() instanceof AddTodoCommand);
    }

    @Test
    public void getCommand_invalidInput_returnsInvalidCommand() {
        ParseCommand parseCommand = new ParseCommand("blahblah");
        assertTrue(parseCommand.getCommand() instanceof InvalidCommand);
    }

    @Test
    public void getCommand_caseInsensitive_recognizesCommand() {
        ParseCommand parseCommand = new ParseCommand("BYE");
        assertTrue(parseCommand.getCommand() instanceof ExitCommand);
    }
}
