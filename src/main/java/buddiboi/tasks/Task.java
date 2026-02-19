package buddiboi.tasks;

/**
 * Base class to be used for all tasks.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the following params
     *
     * @param description Description of the task.
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
