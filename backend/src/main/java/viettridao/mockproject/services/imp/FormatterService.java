package viettridao.mockproject.services.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

/**
 * DateTimeFormatterService
 * Version: 1.0
 * Date: 5/30/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/30/2024 kiet-kun-afk Create
 */
@Service
public class FormatterService {

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    public LocalDate stringToDate(String dateStr) {
        return LocalDate.parse(dateStr, dateFormatter);
    }

    public String dateToString(LocalDate date) {
        return date.format(dateFormatter);
    }

    public LocalTime stringToTime(String timeStr) {
        return LocalTime.parse(timeStr, timeFormatter);
    }

    public String timeToString(LocalTime time) {
        return time.format(timeFormatter);
    }

    public LocalDateTime stringToDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
    }

    public String dateTimeToString(LocalDateTime dateTimeStr) {
        return dateTimeStr.format(dateTimeFormatter);
    }

    public boolean isFuture(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isAfter(today);
    }
}
