import java.util.Scanner;

public class BuddiBoi {
    public static void main(String[] args) {
        String divider = "============================================================\n";
        String indent = "    < ";
        String logo = indent + "______           _     _ _______       _ \n"
                    + indent + "| ___ \\         | |   | (_) ___ \\     (_)\n"
                    + indent + "| |_/ /_   _  __| | __| |_| |_/ / ___  _ \n"
                    + indent + "| ___ \\ | | |/ _` |/ _` | | ___ \\/ _ \\| |\n"
                    + indent + "| |_/ / |_| | (_| | (_| | | |_/ / (_) | |\n"
                    + indent + "\\____/ \\__,_|\\__,_|\\__,_|_\\____/ \\___/|_|\n";
        
        
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100]; // No more than 100 Tasks
        int itemCount = 0;

        System.out.println(divider);
        System.out.println(indent + "Hey, whats up! I am\n" + logo);
        System.out.println(indent + "What can I do for you today?");
        System.out.println("\n" + divider);

        while (true) { 
            String input = sc.nextLine();
            System.out.println("> Command: " + input);

            if (input.equals("bye")) {
                System.out.println(divider);
                System.out.println(indent + "See you next timeee! Ciaoooo!");
                System.out.println("\n" + divider);
                break;
            } else if (input.equals("list")) {
                System.out.println(divider);
                System.out.println(indent + "Here's your tasks lists:");
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(indent + (i + 1) + ". " + tasks[i].getStatus() + " " + tasks[i].description()    );
                }
                System.out.println("\n" + divider);
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println(divider);
                System.out.println(indent + "Alright! Done that task shall be:");
                System.out.println(indent + "  " + tasks[taskNumber].getStatus() + " " + tasks[taskNumber].description());
                System.out.println("\n" + divider);
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println(divider);
                System.out.println(indent + "OK. Task shall be undone:");
                System.out.println(indent + "  " + tasks[taskNumber].getStatus() + " " + tasks[taskNumber].description());
                System.out.println("\n" + divider);
            } else {
                System.out.println(divider);
                System.out.println(indent + "Added: " + input);
                System.out.println("\n" + divider);

                tasks[itemCount] = new Task(input);
                itemCount++;
            }

        }

        sc.close();
    }
}
