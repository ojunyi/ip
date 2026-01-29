public class Ui {
    public void showWelcome() {
        String logo = " ____        _     _     _ \n"
                    + "|  _ \\ _   _| |__ | |__ (_) ___ \n"
                    + "| |_) | | | | '_ \\| '_ \\| |/ _ \\\n"
                    + "|  __/| |_| | |_) | |_) | |  __/\n"
                    + "|_|    \\__,_|_.__/|_.__/|_|\\___|\n";
        String welcomeMessage = "Hello! I'm BuddiBoi\n"
                              + "What can I do for you?";
        returnText(logo + "\n" + welcomeMessage);
    }

    public void showDeleteTask(Task task, int itemCount) {
        returnText("Noted. I've removed this task. Less work for you:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + (itemCount - 1) + " tasks in the list.");
    }

    public void showMarkTask(Task task) {
        returnText("Alrightyy! Congratz on completing that task!:\n"
                + "  " + task.toString());
    }

    public void showUnmarkTask(Task task) {
        returnText("Okay. Task shall be undone:\n"
                + "  " + task.toString());
    }

    public void showErrorMark() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - mark <task number>");
    }

    public void showErrorUnmark() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - unmark <task number>");
    }

    public void showErrorTodo() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + "Please use - todo <description>");
    }

    public void showErrorDeadline() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - No /by was found\n"
                + " - More than one /by was found\n"
                + "Please use - deadline <description> /by <deadline>");
    }

    public void showErrorEventTo() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - No /to was found\n"
                + " - More than one /to was found\n"
                + "Please use - event <description> /from <start date> /to <end date>");
    }

    public void showErrorEventFrom() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - No /from was found\n"
                + " - More than one /from was found\n"
                + " - /from and /to are in the wrong order\n"
                + "Please use - event <description> /from <start date> /to <end date>");
    }

    public void showErrorDelete() {
        returnText("Invalid command due to possible reasons:\n"
                + " - Empty input\n"
                + " - Index out of bounds\n"
                + " - Not a number\n"
                + "Please use - delete <task number>");
    }

    public void showMessage(String message) {
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
