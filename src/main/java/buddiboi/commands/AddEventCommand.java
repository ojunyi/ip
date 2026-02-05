package buddiboi.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import buddiboi.tasks.Event;
import buddiboi.ui.Ui;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public AddEventCommand(String args) {
        String[] parts = args.split(" /from ");
        if (parts.length != 2) {
            this.description = "";
            return;
        }

        this.description = parts[0].trim();

        String[] subParts = parts[1].split(" /to ");
        if (subParts.length != 2) {
            from = null;
            to = null;
        }
            
        try {
            this.from = LocalDateTime.parse(subParts[0].trim(), INPUT_FORMAT);
            this.to = LocalDateTime.parse(subParts[1].trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            this.from = null;
            this.to = null;
        }

        if (from != null && to != null && to.isBefore(from)) {
            from = null;
            to = null;
        }
    }

    @Override
    public void execute(CommandContext context) {
        if (description.isEmpty() || from == null || to == null) {
            Ui.showErrorAddEvent();
            return;
        }
        Event event = new Event(description, from, to);
        context.taskList.addTask(event);
        Ui.showAddTask(event);
    }
}