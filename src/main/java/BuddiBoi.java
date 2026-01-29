import java.util.ArrayList;
import java.util.Scanner;

public class BuddiBoi {

    static Ui ui = new Ui();
    static ArrayList<Task> tasks = new ArrayList<>();
    static int itemCount = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        ui.showWelcome();
        tasks = (ArrayList<Task>) Storage.load();
        itemCount = tasks.size();

        while (true) { 
            String input = sc.nextLine();
            System.out.println("> Command: " + input);

            if (input.equals("bye")) {
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
                    Storage.save(tasks);
                } else {
                    reply.append("Your tasks have not been saved.");
                }

                reply.append("\nSee you next timeee! Ciaoooo ~~~");
                ui.showMessage(reply.toString());
                break;

            } else if (input.equals("list")) {
                listTasks(itemCount);

            } else if (input.startsWith("mark ")) {
                String taskNumberStr = input.substring(5).trim();

                if (taskNumberStr.matches("\\d+") && Integer.parseInt(taskNumberStr) - 1 < itemCount) {
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    markTask(taskNumber);
                } else {
                    ui.showErrorMark();
                }
                
            } else if (input.startsWith("unmark ")) {
                String taskNumberStr = input.substring(7).trim();

                if (taskNumberStr.matches("\\d+") && Integer.parseInt(taskNumberStr) - 1 < itemCount) {
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    unmarkTask(taskNumber);
                } else {
                    ui.showErrorUnmark();
                }

            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                
                if (!description.isBlank()) {
                    Task todoTask = new Todos(description);
                    tasks.add(todoTask);
                    itemCount++;

                    ui.showMessage("Added: " + todoTask.toString());

                } else {
                    ui.showErrorTodo();
                }

            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");

                if (parts.length == 2) {
                    String description = parts[0];
                    String by = parts[1];

                    Task deadlineTask = new Deadline(description, by);
                
                    tasks.add(deadlineTask);
                    itemCount++;

                    ui.showMessage("Added: " + deadlineTask.toString());
                } else {
                    ui.showErrorDeadline();
                }

            } else if (input.startsWith("event ")) {
                String[] part_1 = input.substring(6).split(" /from ");

                if (part_1.length == 2) {
                    String description = part_1[0];
                    String[] part_2 = part_1[1].split(" /to ");

                    if (part_2.length == 2) {
                        String from = part_2[0];
                        String to = part_2[1];
                        Task eventTask = new Event(description, from, to);

                        tasks.add(eventTask);
                        itemCount++;

                        ui.showMessage("Added: " + eventTask.toString());
                    } else {
                        ui.showErrorEventTo();
                    }
                } else {
                    ui.showErrorEventFrom();
                }
            } else if (input.startsWith("delete ")) {
                String taskNumberStr = input.substring(7).trim();

                if (taskNumberStr.matches("\\d+") && Integer.parseInt(taskNumberStr) - 1 < itemCount) {
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    
                    delete(taskNumber);
                    
                } else {
                    ui.showErrorDelete();
                }

            } else {
                ui.showMessage("Echo: " + input + " ~~~");
            }
        }

        sc.close();
    }

    public static void listTasks(int itemCount) {
        String str = "Here's your tasks lists:\n";
        for (int i = 0; i < itemCount; i++) {
            str = str
                + (i + 1)
                + ". "
                + tasks.get(i).toString() + "\n";
        }

        ui.showMessage(str);
    }

    public static void markTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.markAsDone();
        ui.showMessage("Alrightyy! Congratz on completing that task!:\n"
                    + "  " + task.toString());
    }

    public static void unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.markAsUndone();
        ui.showMessage("Okay. Task shall be undone:\n"
                    + "  " + task.toString());
    }

    public static void delete(int taskNumber) {
        Task task = tasks.get(taskNumber);
        ui.showMessage("Noted. I've removed this task. Less work for you:\n"
                    + "  " + task.toString()
                    + "\nNow you have " + (itemCount - 1) + " tasks in the list.");
        tasks.remove(taskNumber);
        itemCount--;
    }
}
