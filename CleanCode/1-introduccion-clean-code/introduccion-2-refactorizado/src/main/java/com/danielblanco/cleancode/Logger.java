package com.danielblanco.cleancode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Logger {

    private static final String EMPLOYEE_LOG_PATH = "log.txt";
    private static final String LAST_MONTH_LOG_PATH = "lastMonthMax.txt";

    public void logNumberOfEmployees(Integer numberOfEmployees) throws IOException {
        checkMonthlyLog();
        updateDailyLog(numberOfEmployees);
    }

    private void checkMonthlyLog() throws IOException {
        if (isFirstDayOfMonth()) {
            logLastMonthMaximumNumberOfEmployees();
        }
    }

    private boolean isFirstDayOfMonth() {
        return LocalDate.now().getDayOfMonth() == 1;
    }

    private void logLastMonthMaximumNumberOfEmployees() throws IOException {
        if (isAnyLastMonthDataAvailable()) {
            Integer maxNumberOfEmployees = getLastMonthMaximumNumberOfEmployees();
            createFileIfNotExists(Paths.get(LAST_MONTH_LOG_PATH));
            Files.write(Paths.get(LAST_MONTH_LOG_PATH), maxNumberOfEmployees.toString().getBytes(),
                    StandardOpenOption.WRITE);

            Files.deleteIfExists(Paths.get(EMPLOYEE_LOG_PATH));
        }
    }

    private boolean isAnyLastMonthDataAvailable() {
        File logFile = new File(EMPLOYEE_LOG_PATH);
        return logFile.exists() && logFile.length() > 0;
    }

    private Integer getLastMonthMaximumNumberOfEmployees() throws IOException {
        List<String> data =
                Files.readAllLines(Paths.get(EMPLOYEE_LOG_PATH), StandardCharsets.UTF_8);

        Integer max = 0;
        for (String s : data) {
            Integer v = Integer.valueOf(s);
            if (v > max) {
                max = v;
            }
        }

        return max;
    }

    private void createFileIfNotExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private void updateDailyLog(Integer numberOfEmployees) throws IOException {
        if (isWorkingDay()) {
            createFileIfNotExists(Paths.get(EMPLOYEE_LOG_PATH));

            String newLog = numberOfEmployees.toString() + "\n";
            Files.write(Paths.get(EMPLOYEE_LOG_PATH), newLog.getBytes(), StandardOpenOption.APPEND);
        }
    }

    private boolean isWorkingDay() {
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

}
