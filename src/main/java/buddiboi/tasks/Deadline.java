package buddiboi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a deadline time.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    protected LocalDateTime deadline;

    /**
     * Construct a Deadline task with the given variables
     *
     * @param description Description of Deadline
     * @param deadline Deadline date of Deadline
     */
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
