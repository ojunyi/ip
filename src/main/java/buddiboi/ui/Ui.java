package buddiboi.ui;

import java.io.IOException;

import buddiboi.tasks.Task;

/**
 * User interface class for displaying messages to the user.
 */
public class Ui {

    /**
     * Prints the greetings of the program
     */
    public static String showWelcome() {
        String logo = "______           _     _ _______       _ \n"
                    + "| ___ \\         | |   | (_) ___ \\     (_)\n"
                    + "| |_/ /_   _  __| | __| |_| |_/ / ___  _ \n"
                    + "| ___ \\ | | |/ _` |/ _` | | ___ \\/ _ \\| |\n"
                    + "| |_/ / |_| | (_| | (_| | | |_/ / (_) | |\n"
                    + "\\____/ \\__,_|\\__,_|\\__,_|_\\____/ \\___/|_|\n";

        return formatText(logo + "\n"
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
                + "Now you have " + (itemCount - 1) + " tasks in the list.");
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
            System.out.println("> Command: yes");
            reply.append("Your tasks have been saved\n");
        } else {
            System.out.println("> Command: no");
            reply.append("Your tasks have not been saved\n");
        }

        reply.append("See you next timeee! Ciaoooo ~~~");

        return formatText(reply.toString());
    }

    /**
     * Prints possible errors for the 'Mark' command
     */
    public static String showErrorMark() {
        return formatText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - mark <task number>");
    }

    /**
     * Prints possible errors for 'Unmark' command
     */
    public static String showErrorUnmark() {
        return formatText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - unmark <task number>");
    }

    /**
     * Prints possible errors for 'Todo' command
     */
    public static String showErrorAddTodo() {
        return formatText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + "Please use - todo <description>");
    }

    /**
     * Prints possible errors for 'Deadline' command
     */
    public static String showErrorAddDeadline() {
        return formatText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - No /by was found\n"
                + " - More than one /by was found\n"
                + " - Invalid date/time format\n"
                + "Please use - deadline <description> /by <01-01-1999>");
    }

    /**
     * Prints possible errors for 'Event' command
     */
    public static String showErrorAddEvent() {
        return formatText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - No /from was found\n"
                + " - No /to was found\n"
                + " - More than one /from was found\n"
                + " - More than one /to was found\n"
                + " - /from and /to are in the wrong order\n"
                + " - Invalid datetime format\n"
                + " - End datetime is before start datetime\n"
                + "Please use - event <description> /from <01-01-1999> /to <01-01-1999>");
    }

    /**
     * Prints possible errors for 'Delete' command
     */
    public static String showErrorDelete() {
        return formatText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - delete <task number>");
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
        String divider = "----------------------------------\n";
        String indent = "    < ";

        sb.append(divider).append("\n");
        for (String line : input.split("\n")) {
            sb.append(indent).append(line.replaceAll("\\s+$", "")).append("\n");
        }
        sb.append("\n").append(divider);

        return sb.toString();
    }
}
