import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Path FILE_PATH = Paths.get("data", "save.txt");

    public static TaskList load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!Files.exists(FILE_PATH)) {
                return new TaskList(tasks);
            }

            List<String> lines = Files.readAllLines(FILE_PATH);

            for (String line: lines) {
                try {
                    Task task = parseLine(line);
                    tasks.add(task);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }

        return new TaskList(tasks);
    }

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
        }
    }

    private static Task parseLine(String line) {
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
            String by = parts[3];
            task = new Deadline(description, by);
            break;

        case "E":
            String start = parts[3];
            String end = parts[4];
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

    private static String taskToLine(Task task) {
        String status = task.getStatus();

        if (task instanceof Todo) {
            return "T | " + status + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + status + " | " + task.getDescription() + " | " + d.deadline;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + status + " | " + task.getDescription() + " | " + e.startDate + " | " + e.endDate;
        } else {
            return "";
        }
    }
}
