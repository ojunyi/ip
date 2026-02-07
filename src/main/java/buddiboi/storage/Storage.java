package buddiboi.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import buddiboi.tasks.Deadline;
import buddiboi.tasks.Event;
import buddiboi.tasks.Task;
import buddiboi.tasks.TaskList;
import buddiboi.tasks.Todo;
import buddiboi.ui.Ui;

/**
 * Handles loading and saving tasks to persistent storage.
 */
public class Storage {
    private static final Path FILE_PATH = Paths.get("data", "save.txt");

    /**
     * Loads the task list from persistent storage.
     *
     * @return The loaded TaskList or an empty TaskList if nothing or failure to load.
     */
    public static TaskList load() {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return new TaskList(tasks);
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);

            for (String line: lines) {
                Task task = parseLine(line);
                if (task == null) {
                    throw new IllegalArgumentException();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            Ui.showErrorStorageIo(e);
            tasks = new ArrayList<>();
            return new TaskList(tasks);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            Ui.showErrorStorageLoad(e);
            tasks = new ArrayList<>();
            return new TaskList(tasks);
        }

        return new TaskList(tasks);
    }

    /**
     * Saves the task list to persistent storage.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void save(List<Task> tasks) {
        try {
            if (!Files.exists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }

            List<String> lines = new ArrayList<>();

            for (Task task: tasks) {
                lines.add(taskToLine(task));
            }

            Files.write(FILE_PATH, lines);
        } catch (IOException e) {
            Ui.showErrorStorageIo(e);
        }
    }

    /**
     * Helper method to parses a line from the storage file into a Task object.
     *
     * @param line The line to be parsed.
     * @return The corresponding Task object.
     * @throws DateTimeParseException If there is an error parsing date and time.
     */
    private static Task parseLine(String line) throws DateTimeParseException {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
        case "T":
            task = new Todo(description);
            break;

        case "D":
            LocalDateTime by = LocalDateTime.parse(parts[3]);
            task = new Deadline(description, by);
            break;

        case "E":
            LocalDateTime start = LocalDateTime.parse(parts[3]);
            LocalDateTime end = LocalDateTime.parse(parts[4]);
            task = new Event(description, start, end);
            break;

        default:
            return null;
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Helper method to converts a Task object into a line for the storage file.
     *
     * @param task The Task object to be converted.
     * @return The corresponding line for the storage file.
     */
    private static String taskToLine(Task task) {
        String status = task.getStatus();

        if (task instanceof Todo) {
            return "T | " + status + " | " + task.getDescription();
        } else if (task instanceof Deadline d) {
            return "D | " + status + " | " + task.getDescription() + " | " + d.getDeadline();
        } else if (task instanceof Event e) {
            return "E | " + status + " | " + task.getDescription() + " | " + e.getStartDate() + " | " + e.getEndDate();
        } else {
            return "";
        }
    }
}
