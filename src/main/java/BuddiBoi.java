import java.util.Scanner;

public class BuddiBoi {
    public static void main(String[] args) {
        String logo = "______           _     _ _______       _ \n"
                    + "| ___ \\         | |   | (_) ___ \\     (_)\n"
                    + "| |_/ /_   _  __| | __| |_| |_/ / ___  _ \n"
                    + "| ___ \\ | | |/ _` |/ _` | | ___ \\/ _ \\| |\n"
                    + "| |_/ / |_| | (_| | (_| | | |_/ / (_) | |\n"
                    + "\\____/ \\__,_|\\__,_|\\__,_|_\\____/ \\___/|_|\n";
        String divider = "============================================================\n";
        
        Scanner sc = new Scanner(System.in);

        System.out.println(divider);
        System.out.println("Hiiii! I am\n" + logo);
        System.out.println("What can I do for you today?");
        System.out.println("\n" + divider);

        while (true) { 
            String input = sc.nextLine();
            System.out.println("Command: " + input);

            if (input.equals("bye")) {
                System.out.println(divider);
                System.out.println("See you next timeee!");
                System.out.println("\n" + divider);
                break;
            }

            System.out.println(divider);
            System.out.println(input);
            System.out.println("\n" + divider);
        }

        sc.close();
    }
}
