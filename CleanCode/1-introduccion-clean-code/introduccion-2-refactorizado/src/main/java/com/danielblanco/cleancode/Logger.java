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

    public void logNumberOfEmployees(Integer value) throws IOException {
        checkMonthlyLog();
        updateDailyLog(value);
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
            Files.write(Paths.get("lastMonthMax.txt"), maxNumberOfEmployees.toString().getBytes(),
                    StandardOpenOption.WRITE);

            Files.deleteIfExists(Paths.get("log.txt"));
        }
    }

    private Integer getLastMonthMaximumNumberOfEmployees() throws IOException {
        List<String> data = Files.readAllLines(Paths.get("log.txt"), StandardCharsets.UTF_8);

        Integer max = 0;
        for (String s : data) {
            Integer v = Integer.valueOf(s);
            if (v > max) {
                max = v;
            }
        }

        return max;
    }

    private boolean isAnyLastMonthDataAvailable() {
        File logFile = new File(EMPLOYEE_LOG_PATH);
        return logFile.exists() && logFile.length() > 0;
    }

    private void updateDailyLog(Integer value) throws IOException {
        if (isWorkingDay()) {
            createFileIfNotExists(Paths.get(EMPLOYEE_LOG_PATH));

            String newLog = value.toString() + "\n";
            Files.write(Paths.get("log.txt"), newLog.getBytes(), StandardOpenOption.APPEND);
        }
    }

    private boolean isWorkingDay() {
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    private void createFileIfNotExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
}
