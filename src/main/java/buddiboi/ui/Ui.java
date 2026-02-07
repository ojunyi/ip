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
    public static void showWelcome() {
        String logo = "______           _     _ _______       _ \n"
                    + "| ___ \\         | |   | (_) ___ \\     (_)\n"
                    + "| |_/ /_   _  __| | __| |_| |_/ / ___  _ \n"
                    + "| ___ \\ | | |/ _` |/ _` | | ___ \\/ _ \\| |\n"
                    + "| |_/ / |_| | (_| | (_| | | |_/ / (_) | |\n"
                    + "\\____/ \\__,_|\\__,_|\\__,_|_\\____/ \\___/|_|\n";

        returnText(logo + "\n"
                + "Hello! I'm BuddiBoi\n"
                + "What can I do for you?");
    }

    /**
     * Prints the goodbyes of the program
     */
    public static void showExitNoCommand() {
        returnText("No save command was given.\n"
                + "Your tasks were not saved.\n"
                + "See you next timeee! Ciaoooo ~~~");
    }

    /**
     * Prints the user input command
     *
     * @param command The users input
     */
    public static void showCommand(String command) {
        String sender = "> ";
        System.out.println(sender + "Command: " + command);
    }

    /**
     * Prints the task that was added
     *
     * @param task The task to be added
     */
    public static void showAddTask(Task task) {
        returnText("Added: " + task.toString());
    }

    /**
     * Prints the task to be deleted
     *
     * @param task Task to be deleted
     * @param itemCount Total task left in the list after deleting the task
     */
    public static void showDeleteTask(Task task, int itemCount) {
        returnText("Noted. I've removed this task. Less work for you:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + (itemCount - 1) + " tasks in the list.");
    }

    /**
     * Prints the task to be marked
     *
     * @param task The task to be marked
     */
    public static void showMarkTask(Task task) {
        returnText("Alrightyy! Congratz on completing that task!:\n"
                + "  " + task.toString());
    }

    /**
     * Prints the task to be unmarked
     *
     * @param task The task to be unmarked
     */
    public static void showUnmarkTask(Task task) {
        returnText("Okay. Task shall be unmarked:\n"
                + "  " + task.toString());
    }

    /**
     * Prints whether the user saved the task list
     *
     * @param isSave Yes/No input on whether the user wants to save the task list
     */
    public static void showExitSaveCommand(Boolean isSave) {
        StringBuilder reply = new StringBuilder();
        if (isSave) {
            System.out.println("> Command: yes");
            reply.append("Your tasks have been saved\n");
        } else {
            System.out.println("> Command: no");
            reply.append("Your tasks have not been saved\n");
        }

        reply.append("See you next timeee! Ciaoooo ~~~");

        returnText(reply.toString());
    }

    /**
     * Prints possible errors for the 'Mark' command
     */
    public static void showErrorMark() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - mark <task number>");
    }

    /**
     * Prints possible errors for 'Unmark' command
     */
    public static void showErrorUnmark() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - unmark <task number>");
    }

    /**
     * Prints possible errors for 'Todo' command
     */
    public static void showErrorAddTodo() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + "Please use - todo <description>");
    }

    /**
     * Prints possible errors for 'Deadline' command
     */
    public static void showErrorAddDeadline() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - No /by was found\n"
                + " - More than one /by was found\n"
                + " - Invalid date/time format\n"
                + "Please use - deadline <description> /by <01-01-1999>");
    }

    /**
     * Prints possible errors for 'Event' command
     */
    public static void showErrorAddEvent() {
        returnText("Invalid command due to possible reasons:\n"
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
    public static void showErrorDelete() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - delete <task number>");
    }

    /**
     * Prints error for I/O on save/load
     */
    public static void showErrorStorageIo(IOException e) {
        returnText("An error occurred while trying to save/load storage file: " + e);
    }

    /**
     * Prints other errors on load
     */
    public static void showErrorStorageLoad(Exception e) {
        returnText("An error occurred while trying to load storage file: " + e);
    }

    /**
     * Prints any message
     */
    public static void showMessage(String message) {
        returnText(message);
    }

    /**
     * Prints message and surrounds them with a divider
     */
    public static void returnText(String input) {
        String divider = "=================================================\n";
        String indent = "    < ";

        System.out.println(divider);
        for (String line : input.split("\n")) {
            System.out.println(indent + line.replaceAll("\\s+$", ""));
        }
        System.out.println("\n" + divider);
    }
}
