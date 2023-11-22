package com.king.urban.workflow;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class WorkSchedule {


    public static void main(String[] args) {
        // 定义开始时间
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 14, 0); // 2023年1月1日下午2点

        int workMinutesRemaining = 300 * 60; // 剩余工作分钟数

        while (workMinutesRemaining > 0) {
            // 判断当前时间是否为工作日
            if (isWeekday(startTime)) {
                if (isWorkTime(startTime)) {
                    workMinutesRemaining--;
                }
            }

            // 时间向后推移一分钟
            startTime = startTime.plusMinutes(1);
        }

        System.out.println("预计完成时间：" + startTime);
    }

    // 判断是否为工作日
    private static boolean isWeekday(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    // 判断是否为工作时间
    private static boolean isWorkTime(LocalDateTime dateTime) {
        return (dateTime.getHour() >= 8 && dateTime.getHour() < 12) || (dateTime.getHour() >= 13 && dateTime.getHour() < 17);
    }

}
