import java.util.Scanner;

public class BuddiBoi {

    static Ui ui = new Ui();
    static TaskList taskList = new TaskList();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        taskList = (TaskList) Storage.load();

        ui.showWelcome();

        while (true) { 
            String input = sc.nextLine().trim();
            Command command = new Command(input);
            ui.showMessage(command.toString());

            if (command.isGoodBye()) {
                ui.showMessage("Would you like me to save your tasks before exiting? (yes/no)");
                if (!sc.hasNextLine()) {
                    ui.showMessage("No command was given.\n"
                            + "Your tasks were not saved.\n"
                            + "See you next timeee! Ciaoooo ~~~");
                    break;
                }

                input = sc.nextLine();
                System.out.println("> Command: " + input);
                StringBuilder reply = new StringBuilder();

                if (input.toLowerCase().equals("yes")) {
                    reply.append("Your tasks have been saved.");
                    Storage.save(taskList.getTasks());
                } else {
                    reply.append("Your tasks have not been saved.");
                }

                reply.append("\nSee you next timeee! Ciaoooo ~~~");
                ui.showMessage(reply.toString());
                break;

            } else if (command.isList()) {
                ui.showMessage(taskList.toString());

            } else if (command.isMark()) {
                String taskNumberStr = input.substring(5).trim();

                if (taskNumberStr.matches("\\d+")) {
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    taskList.markTask(taskNumber);
                } else {
                    ui.showErrorMark();
                }
                
            } else if (command.isUnmark()) {
                String taskNumberStr = input.substring(7).trim();

                if (taskNumberStr.matches("\\d+")) {
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    taskList.unmarkTask(taskNumber);
                } else {
                    ui.showErrorUnmark();
                }

            } else if (command.isTodo()) {
                String description = input.substring(5);
                
                if (!description.isBlank()) {
                    Task todoTask = new Todos(description);
                    taskList.addTask(todoTask);
                    ui.showMessage("Added: " + todoTask.toString());

                } else {
                    ui.showErrorTodo();
                }

            } else if (command.isDeadline()) {
                String[] parts = input.substring(9).split(" /by ");

                if (parts.length == 2) {
                    String description = parts[0];
                    String by = parts[1];

                    Task deadlineTask = new Deadline(description, by);
                    taskList.addTask(deadlineTask);
                    ui.showMessage("Added: " + deadlineTask.toString());
                } else {
                    ui.showErrorDeadline();
                }

            } else if (command.isEvent()) {
                String[] part_1 = input.substring(6).split(" /from ");

                if (part_1.length == 2) {
                    String description = part_1[0];
                    String[] part_2 = part_1[1].split(" /to ");

                    if (part_2.length == 2) {
                        String from = part_2[0];
                        String to = part_2[1];
                        Task eventTask = new Event(description, from, to);
                        taskList.addTask(eventTask);

                        ui.showMessage("Added: " + eventTask.toString());
                    } else {
                        ui.showErrorEventTo();
                    }
                } else {
                    ui.showErrorEventFrom();
                }
            } else if (command.isDelete()) {
                String taskNumberStr = input.substring(7).trim();

                if (taskNumberStr.matches("\\d+")) {
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    taskList.deleteTask(taskNumber);

                } else {
                    ui.showErrorDelete();
                }

            } else {
                ui.showMessage("Echo: " + input + " ~~~");
            }
        }

        sc.close();
    }
}
