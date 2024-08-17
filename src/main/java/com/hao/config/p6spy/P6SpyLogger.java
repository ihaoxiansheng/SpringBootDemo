package com.hao.config.p6spy;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

/**
 * 自定义格式化SQL打印日志
 *
 * @author xu.liang
 * @since 2024/8/17 15:43
 */
public class P6SpyLogger implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category,
                                String prepared, String sql, String url) {
        String shortUrl = "数据库：" + url.substring(url.indexOf("//") + 2).split("\\?")[0];
        return StringUtils.isNotBlank(sql) ? " 耗时：" + elapsed + " ms " + now + " " + shortUrl + "\n SQL：" + sql.replaceAll("[\\s]+", " ") + "\n" : "";
    }
}
