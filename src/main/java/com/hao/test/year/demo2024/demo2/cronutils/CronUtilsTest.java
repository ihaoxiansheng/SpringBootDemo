package com.hao.test.year.demo2024.demo2.cronutils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CronUtilsTest
 *
 * @author xu.liang
 * @since 2024/2/4 16:03
 */
class CronUtilsTest {

    @Test
    void getNextExecutionTest() {
        String cronExpression = "0 0 6 * * ?";
        LocalDateTime localDateTime = CronUtils.getNextExecutions(cronExpression);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextSixAM = now.truncatedTo(ChronoUnit.DAYS).withHour(6);
        if (now.isAfter(nextSixAM)) {
            nextSixAM = nextSixAM.plusDays(1);
        }
        Assertions.assertEquals(nextSixAM, localDateTime);
    }

    @Test
    void getNextExecutionsTest() {
        String cronExpression = "0 0 6 * * ?";
        List<LocalDateTime> nextExecutions = CronUtils.getNextExecutions(cronExpression, 10);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextSixAM = now.truncatedTo(ChronoUnit.DAYS).withHour(6);
        if (now.isAfter(nextSixAM)) {
            nextSixAM = nextSixAM.plusDays(1);
        }
        Assertions.assertEquals(Stream.of(
                nextSixAM,
                nextSixAM.plusDays(1),
                nextSixAM.plusDays(2),
                nextSixAM.plusDays(3),
                nextSixAM.plusDays(4),
                nextSixAM.plusDays(5),
                nextSixAM.plusDays(6),
                nextSixAM.plusDays(7),
                nextSixAM.plusDays(8),
                nextSixAM.plusDays(9)
        ).collect(Collectors.toList()), nextExecutions);
    }

}
