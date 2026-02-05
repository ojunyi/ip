package buddiboi.ui;

import buddiboi.tasks.Task;

public class Ui {

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

    public static void showCommand(String command) {
        String sender = "> ";
        System.out.println(sender + "Command: " + command);
    }

    public static void showAddTask(Task task) {
        returnText("Added: "  + task.toString());
    }

    public static void showDeleteTask(Task task, int itemCount) {
        returnText("Noted. I've removed this task. Less work for you:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + (itemCount - 1) + " tasks in the list.");
    }

    public static void showMarkTask(Task task) {
        returnText("Alrightyy! Congratz on completing that task!:\n"
                + "  " + task.toString());
    }

    public static void showUnmarkTask(Task task) {
        returnText("Okay. Task shall be unmarked:\n"
                + "  " + task.toString());
    }

    public static void showErrorMark() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - mark <task number>");
    }

    public static void showErrorUnmark() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - unmark <task number>");
    }

    public static void showErrorAddTodo() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + "Please use - todo <description>");
    }

    public static void showErrorAddDeadline() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - No /by was found\n"
                + " - More than one /by was found\n"
                + " - Invalid date/time format\n"
                + "Please use - deadline <description> /by <01-01-1999>");
    }

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

    public static void showErrorDelete() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - delete <task number>");
    }

    public static void showErrorStorageIo() {
        returnText("An I/O Error occurred while accessing the storage file: ");
    }

    public static void showMessage(String message) {
        returnText(message);
    }

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
