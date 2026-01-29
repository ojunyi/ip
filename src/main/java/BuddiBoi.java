import java.util.ArrayList;
import java.util.Scanner;

public class BuddiBoi {

    static ArrayList<Task> tasks = new ArrayList<>();
    static int itemCount = 0;

    public static void main(String[] args) {
        String logo = "______           _     _ _______       _ \n"
                    + "| ___ \\         | |   | (_) ___ \\     (_)\n"
                    + "| |_/ /_   _  __| | __| |_| |_/ / ___  _ \n"
                    + "| ___ \\ | | |/ _` |/ _` | | ___ \\/ _ \\| |\n"
                    + "| |_/ / |_| | (_| | (_| | | |_/ / (_) | |\n"
                    + "\\____/ \\__,_|\\__,_|\\__,_|_\\____/ \\___/|_|\n";

        Scanner sc = new Scanner(System.in);
        
        returnText("Hey, whats up! I am\n" + logo + "\nWhat can I do for you today?");
        tasks = (ArrayList<Task>) Storage.load();
        itemCount = tasks.size();

        while (true) { 
            String input = sc.nextLine();
            System.out.println("> Command: " + input);

            if (input.equals("bye")) {
                returnText("Would you like me to save your tasks before exiting? (yes/no)");
                if (!sc.hasNextLine()) {
                    returnText("No command was given.\n"
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
                returnText(reply.toString());
                break;

            } else if (input.equals("list")) {
                listTasks(itemCount);

            } else if (input.startsWith("mark ")) {
                String taskNumberStr = input.substring(5).trim();

                if (taskNumberStr.matches("\\d+") && Integer.parseInt(taskNumberStr) - 1 < itemCount) {
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    markTask(taskNumber);
                } else {
                    // Error handling for invalid task format
                    returnText("Invalid command due to possible reasons:\n"
                                + " - Empty input\n"
                                + " - Not a number\n"
                                + " - Number exceeds total tasks\n"
                                + "Please use - mark <task number>"
                    );
                }
                
            } else if (input.startsWith("unmark ")) {
                String taskNumberStr = input.substring(7).trim();

                if (taskNumberStr.matches("\\d+") && Integer.parseInt(taskNumberStr) - 1 < itemCount) {
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    unMarkTask(taskNumber);
                } else {
                    // Error handling for invalid task format
                    returnText("Invalid command due to possible reasons:\n"
                                + " - Empty input\n"
                                + " - Not a number\n"
                                + " - Number exceeds total tasks\n"
                                + "Please use - unmark <task number>"
                    );
                }

            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                
                if (!description.isBlank()) {
                    Task todoTask = new Todos(description);
                    tasks.add(todoTask);
                    itemCount++;

                    returnText("Added: " + todoTask.toString());

                } else {
                    // Error handling for invalid task format
                    returnText("Invalid command due to possible reasons:\n"
                                + "Please use - todo <description>"
                    );
                }

            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");

                if (parts.length == 2) {
                    String description = parts[0];
                    String by = parts[1];

                    Task deadlineTask = new Deadline(description, by);
                
                    tasks.add(deadlineTask);
                    itemCount++;

                    returnText("Added: " + deadlineTask.toString());
                } else {
                    // Error handling for invalid deadline format
                    returnText("Invalid command due to possible reasons:\n"
                                + " - No /by was found\n"
                                + " - More than one /by was found\n"
                                + "Please use - deadline <description> /by <deadline>"
                    );
                }

            } else if (input.startsWith("event ")) {
                String[] part_1 = input.substring(6).split(" /from ");

                if (part_1.length == 2) {
                    String description = part_1[0];
                    String[] part_2 = part_1[1].split(" /to ");

                    if (part_2.length == 2) {
                        String from = part_2[0];
                        String to = part_2[1];
                        Task eventTask = new Events(description, from, to);

                        tasks.add(eventTask);
                        itemCount++;

                        returnText("Added: " + eventTask.toString());
                    } else {
                        // Error handling for invalid event format
                        returnText("Invalid command due to possible reasons:\n"
                                    + " - No /to was found\n"
                                    + " - More than one /to was found\n"
                                    + "Please use - event <description> /from <start date> /to <end date>"
                        );
                    }
                } else {
                    // Error handling for invalid event format
                    returnText("Invalid command due to possible reasons:\n"
                                + " - No /from was found\n"
                                + " - More than one /from was found\n"
                                + " - /from and /to are in the wrong order\n"
                                + "Please use - event <description> /from <start date> /to <end date>"
                    );
                }
            } else if (input.startsWith("delete ")) {
                String taskNumberStr = input.substring(7).trim();

                if (taskNumberStr.matches("\\d+") && Integer.parseInt(taskNumberStr) - 1 < itemCount) {
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    
                    delete(taskNumber);
                    
                } else {
                    // Error handling for invalid task format
                    returnText("Invalid command due to possible reasons:\n"
                                + " - Empty input\n"
                                + " - Not a number\n"
                                + " - Number exceeds total tasks\n"
                                + "Please use - delete <task number>"
                    );
                }

            } else {
                returnText("Echo: " + input + " ~~~");
            }
        }

        sc.close();
    }

    public static void returnText(String input) {
        String divider = "============================================================\n";
        String indent = "    < ";

        System.out.println(divider);
        for (String line : input.split("\n")) {
            System.out.println(indent + line.replaceAll("\\s+$", ""));
        }
        System.out.println("\n" + divider);
    }

    public static void listTasks(int itemCount) {
        String str = "Here's your tasks lists:\n";
        for (int i = 0; i < itemCount; i++) {
            str = str
                + (i + 1)
                + ". "
                + tasks.get(i).toString() + "\n";
        }

        returnText(str);
    }

    public static void markTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.markAsDone();
        returnText("Alrightyy! Congratz on completing that task!:\n"
                    + "  " + task.toString());
    }

    public static void unMarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.markAsNotDone();
        returnText("Okay. Task shall be undone:\n"
                    + "  " + task.toString());
    }

    public static void delete(int taskNumber) {
        Task task = tasks.get(taskNumber);
        returnText("Noted. I've removed this task. Less work for you:\n"
                    + "  " + task.toString()
                    + "\nNow you have " + (itemCount - 1) + " tasks in the list.");
        tasks.remove(taskNumber);
        itemCount--;
    }
}
