import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int itemCount = 0;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < itemCount; i++) {
            result.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return result.toString();
    }
}
