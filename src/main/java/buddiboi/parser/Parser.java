package buddiboi.parser;

/**
 * Utility class for parsing user input into command and arguments.
 */
public class Parser {

    /**
     * Parses the input string into a command and its arguments.
     * 
     * @param input The user input string.
     * @return String array where 1st element is command and 2nd element is arguments.
     */
    public static String[] parse(String input) {
        input = input.trim();

        if (input.isEmpty()) {
            return new String[] {""};
        }

        int firstSpace = input.indexOf(' ');
        if (firstSpace == -1) {
            return new String[] {input};
        }

        String command = input.substring(0, firstSpace).trim();
        String args = input.substring(firstSpace + 1).trim();

        return new String[] {command, args};
    }
}
