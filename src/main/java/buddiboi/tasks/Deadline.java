package buddiboi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(OUTPUT_FORMAT) + ")";
    }
}
