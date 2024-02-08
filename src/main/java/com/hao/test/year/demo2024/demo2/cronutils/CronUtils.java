package com.hao.test.year.demo2024.demo2.cronutils;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * CronUtils获取下几次执行时间
 *
 * @author xu.liang
 * @since 2024/2/4 16:03
 */
public class CronUtils {

    public static LocalDateTime getNextExecutions(String cronExpression) {
        return getNextExecutions(cronExpression, 1).stream().findFirst().orElse(null);
    }

    public static List<LocalDateTime> getNextExecutions(String cronExpression, int count) {
        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
        Cron quartzCron = parser.parse(cronExpression);
        ExecutionTime executionTime = ExecutionTime.forCron(quartzCron);

        List<LocalDateTime> nextExecutions = new ArrayList<>();
        ZonedDateTime nextExecution = ZonedDateTime.now();

        for (int i = 0; i < count; i++) {
            nextExecution = executionTime.nextExecution(nextExecution).orElse(null);
            if (nextExecution != null) {
                nextExecutions.add(nextExecution.toLocalDateTime());
            } else {
                break;
            }
        }

        return nextExecutions;
    }
}
