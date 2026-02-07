package buddiboi.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parse_emptyString_returnsEmptyCommand() {
        String[] result = Parser.parse("");
        assertArrayEquals(new String[] {""}, result);
    }

    @Test
    public void parse_singleWord_returnsSingleElement() {
        String[] result = Parser.parse("list");
        assertArrayEquals(new String[] {"list"}, result);
    }

    @Test
    public void parse_commandWithArgs_returnsTwoElements() {
        String[] result = Parser.parse("todo read book");
        assertEquals(2, result.length);
        assertEquals("todo", result[0]);
        assertEquals("read book", result[1]);
    }

    @Test
    public void parse_extraSpaces_trimsCorrectly() {
        String[] result = Parser.parse("  deadline  return book  ");
        assertArrayEquals(new String[] {"deadline", "return book"}, result);
    }
}
