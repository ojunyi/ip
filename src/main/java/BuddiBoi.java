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
        String[] list = new String[100]; // No more than 100 items
        int itemCount = 0;

        System.out.println(divider);
        System.out.println(indent + "Hiiii! I am\n" + logo);
        System.out.println(indent + "What can I do for you today?");
        System.out.println("\n" + divider);

        while (true) { 
            String input = sc.nextLine();
            System.out.println("> Command: " + input);

            if (input.equals("bye")) {
                System.out.println(divider);
                System.out.println(indent + "See you next timeee!");
                System.out.println("\n" + divider);
                break;
            } else if (input.equals("list")) {
                System.out.println(divider);
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(indent + (i + 1) + ". " + list[i]);
                }
                System.out.println("\n" + divider);
            } else {
                System.out.println(divider);
                System.out.println(indent + "Added: " + input);
                System.out.println("\n" + divider);

                list[itemCount] = input;
                itemCount++;
            }

        }

        sc.close();
    }
}
