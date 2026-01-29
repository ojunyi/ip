package buddiboi.tasks;
/** 
 **Taken from "https://nus-cs2103-ay2526-s2.github.io/website/schedule/week2/project.html"
 Level 3 Extension: A-Classes
 **/
public class Task { 
    protected String description;
    protected boolean isDone;

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