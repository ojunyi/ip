package buddiboi.tasks;

/**
 * Base class to be used for all tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Construct a task with the given description
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.description;
    }
}
