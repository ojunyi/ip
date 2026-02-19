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
                + "Welcome back! I'm BuddiBoi, your personal task companion.\n"
                + "I'm here to help you stay on top of things. What shall we tackle today?");
    }

    /**
     * Returns a hellow message.
     *
     * @return A formatted hello message.
     */
    public static String showHello() {
        return formatText("Hello friend!");
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @param tasksList The task list to display.
     * @return A formatted string showing all tasks.
     */
    public static String showListCommand(TaskList tasksList) {
        return formatText("Here is everything on your list right now:\n\n"
                + tasksList.toString());
    };

    /**
     * Returns the results of a find command.
     *
     * @param matchingTasksList The list of tasks that match the search criteria.
     * @return A formatted string showing the matching tasks.
     */
    public static String showFindCommand(TaskList matchingTasksList) {
        return formatText("Here is what I found for you:\n\n"
                + matchingTasksList.toString());
    }

    /**
     * Returns the exit message when no save command was given.
     *
     * @return A formatted exit message indicating tasks were not saved.
     */
    public static String showExitNoCommand() {
        return formatText("Just a heads up - your tasks were not saved this time.\n"
                + "I hope to see you again soon. Take care!");
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
        return formatText("Got it! I have added that to your list:\n"
                + "  " + task.toString());
    }

    /**
     * Returns a confirmation message for deleting a task.
     *
     * @param task The task that was deleted.
     * @param itemCount The number of tasks remaining in the list.
     * @return A formatted confirmation message.
     */
    public static String showDeleteTask(Task task, int itemCount) {
        return formatText("Understood! I have removed that task for you:\n"
                + "  " + task.toString() + "\n"
                + "You now have " + itemCount + " task(s) remaining. Keep it up!");
    }

    /**
     * Returns a confirmation message for marking a task as done.
     *
     * @param task The task that was marked.
     * @return A formatted confirmation message.
     */
    public static String showMarkTask(Task task) {
        return formatText("Wonderful work! I have marked that as complete:\n"
                + "  " + task.toString());
    }

    /**
     * Returns a confirmation message for unmarking a task.
     *
     * @param task The task that was unmarked.
     * @return A formatted confirmation message.
     */
    public static String showUnmarkTask(Task task) {
        return formatText("No problem at all! I have unmarked that task for you:\n"
                + "  " + task.toString());
    }

    /**
     * Returns the exit message based on whether tasks were saved.
     *
     * @param isSave True if tasks were saved, false otherwise.
     * @return A formatted exit message.
     */
    public static String showExitSaveCommand() {
        return formatText("It looks like you are ready to wrap up for now.\n"
                + "Would you like me to save your tasks before we go? (yes / no)");
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
            sb.append("Your tasks have been saved safely. They will be here waiting for you!\n");
        } else {
            sb.append("Alright, your tasks have not been saved this time.\n");
        }
        sb.append("Thank you for using BuddiBoi. See you next time!");

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
        return formatText("Oh no, something went wrong on my end. Here are the details:\n"
                + errorMessage + "\n"
                + "Please try again, and let me know if this keeps happening!");
    }

    /**
     * Returns a formatted command error message.
     *
     * @param errorMessage The error message to display.
     * @return A formatted error message.
     */
    public static String showCommandError(String errorMessage) {
        return formatText("Hmm, I did not quite understand that command. Here is what went wrong:\n"
                + errorMessage + "\n"
                + "Please give it another try - I am happy to help!");
    }

    /**
     * Returns a formatted storage error message.
     *
     * @param errorMessage The error message to display.
     * @return A formatted error message.
     */
    public static String showStorageError(String errorMessage) {
        return formatText("I ran into a problem accessing your saved data. Here are the details:\n"
                + errorMessage + "\n"
                + "You may want to check your save file and try again.");
    }

    /**
     * Returns a formatted error message.
     *
     * @param errorMessage The error message to display.
     * @return A formatted error message.
     */
    public static String showError() {
        return formatText("Something unexpected happened and I am not quite sure what went wrong.\n"
                + "Please try again, and I will do my best to help you out!");
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
