package buddiboi.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        DayOfWeek targetDay = parseDayOfWeek(lower);
        if (targetDay != null) {
            LocalDate nextOccurrence = today.with(TemporalAdjusters.next(targetDay));
            return nextOccurrence.atTime(0, 0);
        }

        // Handle "today"
        if (lower.equals("today")) {
            return today.atTime(0, 0);
        }

        // Handle "tomorrow"
        if (lower.equals("tomorrow")) {
            return today.plusDays(1).atTime(0, 0);
        }

        // Handle "next week"
        if (lower.equals("next week")) {
            return today.plusWeeks(1).atTime(0, 0);
        }

        return null;
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
