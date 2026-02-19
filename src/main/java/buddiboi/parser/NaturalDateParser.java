package buddiboi.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

public class NaturalDateParser {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Parses a date string that can be either natural language or standard format.
     *
     * @param dateStr The date string to parse
     * @return LocalDateTime representation of the date
     * @throws DateTimeParseException if the format is invalid
     */
    public static LocalDateTime parse(String dateStr) {

        LocalDateTime naturalDate = tryParseNatural(dateStr);
        if (naturalDate != null) {
            return naturalDate;
        }

        return LocalDateTime.parse(dateStr, INPUT_FORMAT);
    }

    private static LocalDateTime tryParseNatural(String dateStr) {
        String lower = dateStr.toLowerCase().trim();
        LocalDate today = LocalDate.now();

        String[] parts = lower.split("\\s+", 2);
        String keyword = parts[0];
        int[] time = parts.length == 2 ? parseMilitaryTime(parts[1]) : null;
        int hour = time != null ? time[0] : 0;
        int minute = time != null ? time[1] : 0;

        DayOfWeek targetDay = parseDayOfWeek(lower);
        if (targetDay != null) {
            LocalDate nextOccurrence = today.with(TemporalAdjusters.next(targetDay));
            return nextOccurrence.atTime(0, 0);
        }

        switch (keyword) {
        case "today":
            return today.atTime(hour, minute);
        case "tomorrow":
            return today.plusDays(1).atTime(hour, minute);
        case "next":
            if (parts.length == 2 && parts[1].equals("week")) {
                return today.plusWeeks(1).atTime(0, 0);
            }
            break;
        default:
            break;
        }

        return null;
    }

    /**
     * Parses a 4-digit military time string into [hour, minute].
     * Returns null if the format is invalid.
     */
    private static int[] parseMilitaryTime(String timeStr) {
        if (timeStr == null || !timeStr.matches("\\d{4}")) {
            return null;
        }
        int hour = Integer.parseInt(timeStr.substring(0, 2));
        int minute = Integer.parseInt(timeStr.substring(2, 4));
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            return null;
        }
        return new int[]{hour, minute};
    }

    private static DayOfWeek parseDayOfWeek(String str) {
        return switch (str) {
            case "mon", "monday" -> DayOfWeek.MONDAY;
            case "tue", "tuesday" -> DayOfWeek.TUESDAY;
            case "wed", "wednesday" -> DayOfWeek.WEDNESDAY;
            case "thu", "thursday" -> DayOfWeek.THURSDAY;
            case "fri", "friday" -> DayOfWeek.FRIDAY;
            case "sat", "saturday" -> DayOfWeek.SATURDAY;
            case "sun", "sunday" -> DayOfWeek.SUNDAY;
            default -> null;
        };
    }
}
