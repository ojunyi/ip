package buddiboi.commands;

import buddiboi.storage.Storage;
import buddiboi.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(CommandContext context) {
        Ui.showMessage("Would you like me to save your tasks before exiting? (yes/no)");
        if (!context.scanner.hasNextLine()) {
            Ui.showMessage("No command was given.\n"
                    + "Your tasks were not saved.\n"
                    + "See you next timeee! Ciaoooo ~~~");
        }

        String input = context.scanner.nextLine();
        System.out.println("> Command: " + input);
        StringBuilder reply = new StringBuilder();

        if (input.toLowerCase().equals("yes")) {
            reply.append("Your tasks have been saved.");
            Storage.save(context.taskList.getTasks());
        } else {
            reply.append("Your tasks have not been saved.");
        }

        reply.append("\nSee you next timeee! Ciaoooo ~~~");
        Ui.showMessage(reply.toString());
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
