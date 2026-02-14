package buddiboi.ui;

import java.io.IOException;

import buddiboi.tasks.Task;

/**
 * User interface class for displaying messages to the user.
 */
public class Ui {

    private static final String DIVIDER = "----------------------------------\n";
    private static final String INDENT = "    < ";
    private static final String ERROR_PREFIX = "Invalid command due to possible reasons:\n";
    private static final String USAGE_PREFIX = "Please use - ";
    private static final String LOGO = "______           _     _ _______       _ \n"
            + "| ___ \\         | |   | (_) ___ \\     (_)\n"
            + "| |_/ /_   _  __| | __| |_| |_/ / ___  _ \n"
            + "| ___ \\ | | |/ _` |/ _` | | ___ \\/ _ \\| |\n"
            + "| |_/ / |_| | (_| | (_| | | |_/ / (_) | |\n"
            + "\\____/ \\__,_|\\__,_|\\__,_|_\\____/ \\___/|_|\n";

    /**
     * Prints the greetings of the program
     */
    public static String showWelcome() {
        return formatText(LOGO + "\n"
                + "Hello! I'm BuddiBoi\n"
                + "What can I do for you?");
    }

    /**
     * Prints the goodbyes of the program
     */
    public static String showExitNoCommand() {
        return formatText("No save command was given.\n"
                + "Your tasks were not saved.\n"
                + "See you next timeee! Ciaoooo ~~~");
    }

    /**
     * Prints the user input command
     *
     * @param command The users input
     */
    public static String showCommand(String command) {
        return " > Command: " + command + "\n";
    }

    /**
     * Prints the task that was added
     *
     * @param task The task to be added
     */
    public static String showAddTask(Task task) {
        return formatText("Added: " + task.toString());
    }

    /**
     * Prints the task to be deleted
     *
     * @param task Task to be deleted
     * @param itemCount Total task left in the list after deleting the task
     */
    public static String showDeleteTask(Task task, int itemCount) {
        return formatText("Noted. I've removed this task. Less work for you:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + itemCount + " tasks in the list.");
    }

    /**
     * Prints the task to be marked
     *
     * @param task The task to be marked
     */
    public static String showMarkTask(Task task) {
        return formatText("Alrightyy! Congratz on completing that task!:\n"
                + "  " + task.toString());
    }

    /**
     * Prints the task to be unmarked
     *
     * @param task The task to be unmarked
     */
    public static String showUnmarkTask(Task task) {
        return formatText("Okay. Task shall be unmarked:\n"
                + "  " + task.toString());
    }

    /**
     * Prints whether the user saved the task list
     *
     * @param isSave Yes/No input on whether the user wants to save the task list
     */
    public static String showExitSaveCommand(Boolean isSave) {
        StringBuilder reply = new StringBuilder();
        if (isSave) {
            reply.append("Your tasks have been saved\n");
        } else {
            reply.append("Your tasks have not been saved\n");
        }
        reply.append("See you next timeee! Ciaoooo ~~~");
        return formatText(reply.toString());
    }

    /**
     * Prints possible errors for the 'Mark' command
     */
    public static String showErrorMark() {
        return showErrorWithReasons(
                new String[]{"Empty input",
                        "Index out of bounds",
                        "Not a number"},
                "mark <task number>");
    }

    /**
     * Prints possible errors for 'Unmark' command
     */
    public static String showErrorUnmark() {
        return showErrorWithReasons(
                new String[]{"Empty input",
                        "Index out of bounds",
                        "Not a number"},
                "unmark <task number>");
    }

    /**
     * Prints possible errors for 'Todo' command
     */
    public static String showErrorAddTodo() {
        return showErrorWithReasons(
                new String[]{"Empty input"},
                "todo <description>");
    }

    /**
     * Prints possible errors for 'Deadline' command
     */
    public static String showErrorAddDeadline() {
        return showErrorWithReasons(
                new String[]{"Empty input",
                        "No /by was found",
                        "More than one /by was found",
                        "Invalid date/time format"},
                "deadline <description> /by <01-01-1999>");
    }

    /**
     * Prints possible errors for 'Event' command
     */
    public static String showErrorAddEvent() {
        return showErrorWithReasons(
                new String[]{"Empty input",
                        "No /from was found",
                        "No /to was found",
                        "More than one /from was found",
                        "More than one /to was found",
                        "/from and /to are in the wrong order",
                        "Invalid datetime format",
                        "End datetime is before start datetime"},
                "event <description> /from <01-01-1999> /to <01-01-1999>");
    }

    /**
     * Prints possible errors for 'Delete' command
     */
    public static String showErrorDelete() {
        return showErrorWithReasons(
                new String[]{"Empty input",
                        "Index out of bounds",
                        "Not a number"},
                "delete <task number>");
    }

    /**
     * Prints error for I/O on save/load
     */
    public static String showErrorStorageIo(IOException e) {
        return formatText("An error occurred while trying to save/load storage file: " + e);
    }

    /**
     * Prints other errors on load
     */
    public static String showErrorStorageLoad(Exception e) {
        return formatText("An error occurred while trying to load storage file: " + e);
    }

    /**
     * Prints any message
     */
    public static String showMessage(String message) {
        return formatText(message);
    }

    /**
     * Prints message and surrounds them with a divider
     */
    public static String formatText(String input) {
        StringBuilder sb = new StringBuilder();

        sb.append(DIVIDER).append("\n");
        for (String line : input.split("\n")) {
            sb.append(INDENT).append(line.replaceAll("\\s+$", "")).append("\n");
        }
        sb.append("\n").append(DIVIDER);

        return sb.toString();
    }

    /**
     * Format error message to show as list
     *
     * @param reasons List of reasons associated with error
     * @param usage Proper usage of command
     * @return Returns a list of errors
     */
    private static String showErrorWithReasons(String[] reasons, String usage) {
        StringBuilder sb = new StringBuilder(ERROR_PREFIX);
        for (String reason : reasons) {
            sb.append(" - ").append(reason).append("\n");
        }
        sb.append(USAGE_PREFIX).append(usage);
        return formatText(sb.toString());
    }
}
