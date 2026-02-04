package buddiboi.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import buddiboi.tasks.Deadline;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public AddDeadlineCommand(String args) {
        String[] parts = args.split(" /by ");
        if (parts.length != 2) {
            this.description = "";
            return;
        }

        this.description = parts[0].trim();

        try {
            this.by = LocalDateTime.parse(parts[1].trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            this.by = null;
        }
    }

    @Override
    public void execute(CommandContext context) {
        if (description.isEmpty() || by == null) {
            context.ui.showErrorAddDeadline();
            return;
        }
        Deadline deadline = new Deadline(description, by);
        context.taskList.addTask(deadline);
        context.ui.showAddTask(deadline);
    }
    
}
