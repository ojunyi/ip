package buddiboi.ui;

import buddiboi.tasks.Task;
import buddiboi.tasks.TaskList;

/**
 * User interface class for displaying messages to the user.
 */
public class Ui {

    private static final String INDENT = "    < ";
    private static final String LOGO = "______           _     _ _______       _ \n"
            + "| ___ \\         | |   | (_) ___ \\     (_)\n"
            + "| |_/ /_   _  __| | __| |_| |_/ / ___  _ \n"
            + "| ___ \\ | | |/ _` |/ _` | | ___ \\/ _ \\| |\n"
            + "| |_/ / |_| | (_| | (_| | | |_/ / (_) | |\n"
            + "\\____/ \\__,_|\\__,_|\\__,_|_\\____/ \\___/|_|\n";

    /**
     * Returns the welcome message with the BuddiBoi logo.
     *
     * @return A formatted welcome message.
     */
    public static String showWelcome() {
        return formatText(LOGO + "\n"
                + "Hello! I'm BuddiBoi\n"
                + "What can I do for you?");
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @param tasksList The task list to display.
     * @return A formatted string showing all tasks.
     */
    public static String showListCommand(TaskList tasksList) {
        return formatText(tasksList.toString());
    };

    /**
     * Returns the results of a find command.
     *
     * @param matchingTasksList The list of tasks that match the search criteria.
     * @return A formatted string showing the matching tasks.
     */
    public static String showFindCommand(TaskList matchingTasksList) {
        return formatText(matchingTasksList.toString());
    }

    /**
     * Returns the exit message when no save command was given.
     *
     * @return A formatted exit message indicating tasks were not saved.
     */
    public static String showExitNoCommand() {
        return formatText("No save command was given.\n"
                + "Your tasks were not saved.\n"
                + "See you next timeee! Ciaoooo ~~~");
    }

    /**
     * Returns a formatted display of the user's command.
     *
     * @param command The user's input command.
     * @return A formatted string showing the command.
     */
    public static String showCommand(String command) {
        return " > Command: " + command + "\n";
    }

    /**
     * Returns a confirmation message for adding a task.
     *
     * @param task The task that was added.
     * @return A formatted confirmation message.
     */
    public static String showAddTask(Task task) {
        return formatText("Added: " + task.toString());
    }

    /**
     * Returns a confirmation message for deleting a task.
     *
     * @param task The task that was deleted.
     * @param itemCount The number of tasks remaining in the list.
     * @return A formatted confirmation message.
     */
    public static String showDeleteTask(Task task, int itemCount) {
        return formatText("Noted. I've removed this task. Less work for you:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + itemCount + " tasks in the list.");
    }

    /**
     * Returns a confirmation message for marking a task as done.
     *
     * @param task The task that was marked.
     * @return A formatted confirmation message.
     */
    public static String showMarkTask(Task task) {
        return formatText("Well done completing that task:\n"
                + "  " + task.toString());
    }

    /**
     * Returns a confirmation message for unmarking a task.
     *
     * @param task The task that was unmarked.
     * @return A formatted confirmation message.
     */
    public static String showUnmarkTask(Task task) {
        return formatText("Okay. Task shall be unmarked:\n"
                + "  " + task.toString());
    }

    /**
     * Returns the exit message based on whether tasks were saved.
     *
     * @param isSave True if tasks were saved, false otherwise.
     * @return A formatted exit message.
     */
    public static String showExitSaveCommand() {
        return formatText("Preparing to exit...\n"
                + "Would you like me to save your tasks before exiting? (yes/no)");
    }

    /**
     * Returns the save confirmation message based on whether user wants to save.
     *
     * @param isSave True if user wishes to save, false otherwise.
     * @return A formatted save confirmation message.
     */
    public static String showSaveConfirmation(Boolean isSave) {
        StringBuilder sb = new StringBuilder();
        if (isSave) {
            sb.append("Your tasks have been saved\n");
        } else {
            sb.append("Your tasks have not been saved\n");
        }
        sb.append("See you next timeee! Ciaoooo ~~~");

        return formatText(sb.toString());
    }

    /**
     * Returns a formatted message.
     *
     * @param message The message to display.
     * @return A formatted string.
     */
    public static String showMessage(String message) {
        return formatText(message);
    }

    /**
     * Returns a formatted internal error message.
     *
     * @param errorMessage The error message to display.
     * @return A formatted error message.
     */
    public static String showBuddiBoiError(String errorMessage) {
        return formatText("Internal Error: " + errorMessage);
    }

    /**
     * Returns a formatted command error message.
     *
     * @param errorMessage The error message to display.
     * @return A formatted error message.
     */
    public static String showCommandError(String errorMessage) {
        return formatText("Command Error: " + errorMessage);
    }

    /**
     * Returns a formatted command error message.
     *
     * @param errorMessage The error message to display.
     * @return A formatted error message.
     */
    public static String showStorageError(String errorMessage) {
        return formatText("Storage Error: " + errorMessage);
    }

    /**
     * Returns a formatted error message.
     *
     * @param errorMessage The error message to display.
     * @return A formatted error message.
     */
    public static String showError() {
        return formatText("Error: An unexpected error occurred");
    }

    /**
     * Formats text by adding indentation to each line.
     *
     * @param input The text to format.
     * @return A formatted string with indentation applied to each line.
     */
    public static String formatText(String input) {
        StringBuilder sb = new StringBuilder();

        for (String line : input.split("\n")) {
            sb.append(INDENT).append(line.replaceAll("\\s+$", "")).append("\n");
        }

        return sb.toString();
    }
}
