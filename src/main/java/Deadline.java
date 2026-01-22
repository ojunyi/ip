/** 
 **Taken from "https://nus-cs2103-ay2526-s2.github.io/website/schedule/week2/project.html"
 Level 4 Extension: A-Inheritance
 **/
public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
