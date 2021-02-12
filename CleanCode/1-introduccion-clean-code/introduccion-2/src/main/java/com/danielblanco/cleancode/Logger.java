package com.danielblanco.cleancode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Logger {

    public void logNumberOfEmployees(Integer value) throws IOException {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Integer dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        Integer dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        if (dayOfMonth == 1) {
            if (Files.exists(Paths.get("log.txt"))) {
                List<String> data =
                        Files.readAllLines(Paths.get("log.txt"), StandardCharsets.UTF_8);

                Integer max = 0;
                for (String s : data) {
                    Integer v = Integer.valueOf(s);
                    if (v > max) {
                        max = v;
                    }
                }

                if (!Files.exists(Paths.get("lastMonthMax.txt"))) {
                    Files.createFile(Paths.get("lastMonthMax.txt"));
                }

                Files.write(Paths.get("lastMonthMax.txt"), max.toString().getBytes(),
                        StandardOpenOption.WRITE);

                Files.deleteIfExists(Paths.get("log.txt"));
            }
        }

        if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
            if (!Files.exists(Paths.get("log.txt"))) {
                Files.createFile(Paths.get("log.txt"));
            }

            String newLog = value.toString() + "\n";
            Files.write(Paths.get("log.txt"), newLog.getBytes(), StandardOpenOption.APPEND);
        }

    }
}
