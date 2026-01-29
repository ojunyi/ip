import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int itemCount = 0;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
        itemCount++;
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
        itemCount--;
    }

    public void markTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.markAsDone();
    }

    public void unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.markAsUndone();
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
