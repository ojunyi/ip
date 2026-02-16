package buddiboi.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import buddiboi.exceptions.StorageException;
import buddiboi.tasks.Deadline;
import buddiboi.tasks.Event;
import buddiboi.tasks.Task;
import buddiboi.tasks.TaskList;
import buddiboi.tasks.Todo;

/**
 * Handles loading and saving tasks to persistent storage.
 */
public class Storage {

    private static final Path FILE_PATH = Paths.get("data", "save.txt");
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String DELIMITER = " | ";
    private static final String DONE = "[X]";

    /**
     * Loads the task list from persistent storage.
     *
     * @return The loaded TaskList.
     * @throws StorageException If there are errors reading or parsing the file.
     */
    public static TaskList load() throws StorageException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return new TaskList(tasks);
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);

            for (String line : lines) {
                Task task = parseLine(line);
                if (task == null) {
                    throw new IllegalArgumentException("Invalid task format");
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            throw new StorageException(e.getMessage());
        }

        return new TaskList(tasks);
    }

    /**
     * Saves the task list to persistent storage.
     *
     * @param tasks The list of tasks to save.
     * @throws StorageException If there are errors writing to the file.
     */
    public static void save(TaskList taskList) throws StorageException {
        try {
            if (!Files.exists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }

            List<String> lines = new ArrayList<>();

            for (Task task : taskList.getTasks()) {
                lines.add(taskToLine(task));
            }

            Files.write(FILE_PATH, lines);
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line The line to parse.
     * @return The corresponding Task object, or null if the task type is invalid.
     * @throws DateTimeParseException If there is an error parsing date and time.
     * @throws ArrayIndexOutOfBoundsException If the line format is invalid.
     */
    private static Task parseLine(String line) throws DateTimeParseException, ArrayIndexOutOfBoundsException {
        String[] parts = line.split(" \\| ");

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("[X]");
        String description = parts[2].trim();

        Task task;

        switch (type) {
        case TODO_TYPE:
            task = new Todo(description);
            break;

        case DEADLINE_TYPE:
            LocalDateTime by = LocalDateTime.parse(parts[3]);
            task = new Deadline(description, by);
            break;

        case EVENT_TYPE:
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
     * Converts a Task object into a line for the storage file.
     *
     * @param task The Task object to convert.
     * @return The corresponding line for the storage file.
     */
    private static String taskToLine(Task task) {
        String status = task.getStatus();

        if (task instanceof Todo t) {
            return TODO_TYPE + DELIMITER + status + DELIMITER + t.getDescription();
        } else if (task instanceof Deadline d) {
            return DEADLINE_TYPE + DELIMITER + status + DELIMITER
                    + task.getDescription() + DELIMITER + d.getDeadline();
        } else if (task instanceof Event e) {
            return EVENT_TYPE + DELIMITER + status + DELIMITER
                    + task.getDescription() + DELIMITER
                    + e.getStartDate() + DELIMITER + e.getEndDate();
        } else {
            return "";
        }
    }
}
