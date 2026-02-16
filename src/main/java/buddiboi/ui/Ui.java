package buddiboi.ui;

import java.io.IOException;

import buddiboi.tasks.Task;
import buddiboi.tasks.TaskList;

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
     * Returns the string description of list
     *
     * @param tasksList List of tasks
     * @returnA formatted string showing the matching tasks
     */
    public static String showListCommand(TaskList tasksList) {
        return tasksList.toString();
    };

    /**
     * Shows the results of a find command
     *
     * @param matchingTasksList List of tasks that match the search criteria
     * @return A formatted string showing the matching tasks
     */
    public static String showFindCommand(TaskList matchingTasksList) {
        return matchingTasksList.toString();
        /*
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");

        if (matchingTasksList.isEmpty()) {
            result.append("No matching tasks found");
        } else {
            for (int i = 0; i < matchingTasksList.size(); i++) {
                result.append(i + 1)
                    .append(". ")
                    .append(matchingTasksList.get(i).toString())
                    .append("\n");
            }
        }

        return result.toString();
        */
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
        return formatText("Well done completing that task:\n"
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

    public static String showCommandError(String errorMessage) {
        return formatText("Command Error: " + errorMessage);
    }

    public static String showBuddiBoiError(String errorMessage) {
        return formatText("Internal Error: " + errorMessage);
    }

    public static String showError(String errorMessage) {
        return formatText("Error: " + errorMessage);
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
