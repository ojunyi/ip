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

    /**
     * Constructs a TaskList with the given list of tasks
     *
     * @param tasks ArrayList of tasks to be stored in TaskList
     */
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

    /**
     * Adds a task to the task list
     *
     * @param task Task to be added to task list
     */
    public void addTask(Task task) {
        tasks.add(task);
        itemCount++;
    }

    /**
     * Deletes task from task list based on the task number
     *
     * @param taskNumber Task number to be deleted
     */
    public void deleteTask(int taskNumber) {
        if (taskNumber < 0 || taskNumber > itemCount) {
            throw new IndexOutOfBoundsException();
        }
        tasks.remove(taskNumber);
        itemCount--;
    }

    /**
     * Marks a task in the task list as done
     *
     * @param taskNumber Task number to be marked
     */
    public void markTask(int taskNumber) {
        if (taskNumber < 0 || taskNumber >= itemCount) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(taskNumber);
        task.markAsDone();
    }

    /**
     * Unmarks a task in the task list as undone
     *
     * @param taskNumber Task number to be unmarked
     */
    public void unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.markAsUndone();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < itemCount; i++) {
            result.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }

        if (result.isEmpty()) {
            result.append("List is empty");
        }

        return result.toString();
    }
}
