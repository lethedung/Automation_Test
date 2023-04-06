package calculateDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class CalculateDate {
    public static void main(String[] args) {
        String startDateString = "20170520";
        String closeDateString = "20170731";

        int checkResult = validateInput(startDateString, closeDateString);
        if (checkResult == 2) {
            System.out.println(
                    "1. Number of days from " + startDateString + " to " + closeDateString + " is " + getSumDays(
                            startDateString, closeDateString));

            int monthsToAdd = 1;
            System.out.println(
                    "2. CloseDate after adding " + monthsToAdd + " month with " + startDateString + " is " + addMonths(
                            startDateString, monthsToAdd));
        } else if (checkResult == 1){
            System.out.println("CloseDate must be after the StartDate");
        } else {
            System.out.println("Input date is invalid!");
        }
    }

    // validate input
    public static int validateInput(String startDateString, String closeDateString){
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        try {
            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate closeDate = LocalDate.parse(closeDateString, formatter);
            if (closeDate.isBefore(startDate)){
                return 1;
            }
            return 2;
        } catch (DateTimeParseException e) {
            return -1;
        }
    }

    // calculate number of days
    public static long getSumDays(String startDateString, String closeDateString) {
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate closeDate = LocalDate.parse(closeDateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return startDate.until(closeDate, ChronoUnit.DAYS) + 1;
    }

    // calculate closeDate
    public static String addMonths(String startDateString, int monthsToAdd) {
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate closeDate = startDate.plusMonths(monthsToAdd);
        return closeDate.withDayOfMonth(closeDate.lengthOfMonth()).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    // check startDate is last day of month or not
    public static boolean isLastDayOfMonth(String startDate ){
        LocalDate date = LocalDate.parse(startDate, DateTimeFormatter.BASIC_ISO_DATE);
        return date.getDayOfMonth() == date.lengthOfMonth();
    }
}
