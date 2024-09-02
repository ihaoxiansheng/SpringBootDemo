package com.hao.test.year.demo2024.demo4;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hao.config.async.AsyncConfig;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author xu.liang
 * @since 2024/4/1 13:53
 */
public class DemoTest {

    private static AtomicInteger sequence = new AtomicInteger(1);

    @SneakyThrows
    public static void main(String[] args) {

        String orgId = "";
        String[] strArrays = orgId.split(",");
        // 输出[]
        System.out.println("strArrays = " + Arrays.toString(strArrays));

        String gjBeginTime = "2024-04-03 17:10:47";
        DateTime parse = DateUtil.parse(gjBeginTime, "yyyy-MM-dd HH:mm:ss");
        String sss = DateUtil.formatDateTime(parse);
        System.out.println("sss = " + sss);

        Date format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(gjBeginTime);
        String format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(format);
        System.out.println("format1 = " + format1);


        // 获取当前月
        LocalDate current = LocalDate.now();
        int yearDate = current.getYear();
        int monthDate = current.getMonthValue();
        int dayOfMonth = current.getDayOfMonth();
        System.out.println("yearDate = " + yearDate);
        System.out.println("monthDate = " + monthDate);
        System.out.println("dayOfMonth = " + dayOfMonth);

        LocalDate currentDate = LocalDate.now().withYear(yearDate).withMonth(monthDate).withDayOfMonth(1).plusDays(1);
        System.out.println("currentDate = " + currentDate);

        int num = 22;
        String primaryNum = String.format("%05d", num);
        System.out.println("primaryNum = " + primaryNum);

        List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        List<CompletableFuture<Void>> futures = list.stream()
                .map(devicePollAlarmConfig ->
                        // CompletableFuture.runAsync(() -> {}, 自定义线程池名称)
                        CompletableFuture.runAsync(() -> {
                            // 业务代码
                        }, new AsyncConfig().asyncExecutor())
                )
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println();

        // 详细用法：https://blog.csdn.net/HaHa_Sir/article/details/133921844
        Map<String, String> result = new HashMap<>();
        result.put("name", "小明");
        String name = result.getOrDefault("name", "张三");
        System.out.println("name = " + name);
        // 编程中经常遇到这种数据结构，判断一个map中是否存在这个key，如果存在则处理value的数据，
        // 如果不存在，则创建一个满足value要求的数据结构放到value中。
        // 这个业务场景要写很大一堆代码，map本身的这个computeIfAbsent方法直接搞定。
        result.computeIfAbsent("name", key -> "李四");
        System.out.println("result1 = " + result);
        // key转换为value
        result.compute("name", (k, v) -> k);
        System.out.println("result2 = " + result);
        result.computeIfPresent("name", (k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
            return "王五";
        });
        System.out.println("result3 = " + result);

        System.out.println();

        // 处理Emoji表情
        String emojiStr = "12321😊🐶";
        String noEmoji = EmojiParser.removeAllEmojis(emojiStr);
        System.out.println("noEmoji = " + noEmoji);
        String parseToAliases = EmojiParser.parseToAliases(emojiStr);
        System.out.println("parseToAliases = " + parseToAliases);
        String parseToUnicode = EmojiParser.parseToUnicode(parseToAliases);
        System.out.println("parseToUnicode = " + parseToUnicode);


        String s = generateOrderId();
        System.out.println("s = " + s);

        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);
        String replace = uuid.replace("-", "");
        System.out.println("replace = " + replace);

        // 从字符串左侧补齐
        String leftPad = StringUtils.leftPad("1234", 5, "ab");
        System.out.println("leftPad = " + leftPad);

        String strTest = null + "";
        System.out.println("strTest = " + strTest);


        List<String> strList = new ArrayList<>();
        strList.add("aa");
        strList.add("qq");
        strList.add("ww");
        System.out.println("strList = " + strList);
        System.out.println("strList2 = " + JSON.toJSONString(strList));
        System.out.println("String.join(\",\", strList) = " + String.join(",", strList));

        System.out.println("emptyList = " + JSON.toJSONString(new ArrayList<>()));

        Map<String, Object> params = new HashMap<>();
        params.put("name", "小明");
        String id = MapUtils.getString(params, "id", "");
        System.out.println("id = " + id);

        String nameTest = "";
        String[] split = nameTest.split(",");
        System.out.println("split = " + Arrays.toString(split));


        String clientIp = "{\"RemoteHost\":\"192.168.275.109\",\"RemoteAddr\":\"192.168.275.109\"}";
        JSONObject jsonObject = JSON.parseObject(clientIp);
        String remoteHost = jsonObject.getString("RemoteHost");
        System.out.println("remoteHost = " + remoteHost);


        Map<String, Object> rulesMap = new LinkedHashMap<>();

        rulesMap.put("stateFormJson", "stateFormJson");
        rulesMap.put("eventFormJson", "eventFormJson");
        rulesMap.put("participantJson", "participantJson");

        rulesMap.put("participantJson", "participantJson2");


        System.out.println("rulesMap = " + rulesMap);


        List<String> testList = new ArrayList<>();
        for (int i = 0; i < testList.size(); i++) {
            System.out.println("i = " + i);
        }

        Map<Object, Object> hashMap = new HashMap<>();
        System.out.println("JSON.toJSONString(hashMap) = " + JSON.toJSONString(hashMap));
    }

    public static String generateOrderId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String timestamp = now.format(formatter);
        int seq = sequence.getAndIncrement();
        System.out.println("seq = " + seq);
        String seqStr = String.format("%04d", seq); // 保证序列号始终是4位数，不足补0
        String clueCode = timestamp + "-" +  seqStr;
        System.out.println("clueCode = " + clueCode);
        return clueCode;
    }

}
