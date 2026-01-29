public class Parser {
    public static String[] parse(String input) {
        input = input.trim();

        if (input.isEmpty()) {
            return new String[] {""};
        }

        int firstSpace = input.indexOf(' ');
        if (firstSpace == -1) {
            return new String[] {input};
        }

        String command = input.substring(0, firstSpace);
        String args = input.substring(firstSpace + 1);

        return new String[] {command, args};
    }
}
