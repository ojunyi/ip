package buddiboi.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int itemCount = 0;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.itemCount = tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public int getItemCount() {
        return itemCount;
    }

    public void addTask(Task task) {
        tasks.add(task);
        itemCount++;
    }

    public void deleteTask(int taskNumber) {
        if (taskNumber < 0 || taskNumber >= itemCount) {
            throw new IndexOutOfBoundsException();
        }
        tasks.remove(taskNumber);
        itemCount--;
    }

    public void markTask(int taskNumber) {
        if (taskNumber < 0 || taskNumber >= itemCount) {
            throw new IndexOutOfBoundsException();
        }
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
