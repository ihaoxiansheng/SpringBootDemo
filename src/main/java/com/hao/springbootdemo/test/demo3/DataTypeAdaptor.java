package com.hao.springbootdemo.test.demo3;

import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * gson序列化过程中数字类型转换问题
 * <p>
 * 在使用gson序列化与反序列化数字类型为Object时，gson会把数字类型转换为浮点型。如2->2.0。解决方案：
 * 创建一个类型转换的实体类，使数字类型转换为Object时使用该TypeAdaptor
 *
 * @author xu.liang
 * @since 2023/3/7 10:19
 */
public class DataTypeAdaptor extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) {

    }

    @Override
    public Object read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        switch (token) {
            case BEGIN_ARRAY:
                List<Object> list = new ArrayList<>();
                in.beginArray();
                while (in.hasNext()) {
                    list.add(read(in));
                }
                in.endArray();
                return list;
            case BEGIN_OBJECT:
                Map<String, Object> map = new LinkedTreeMap<>();
                in.beginObject();
                while (in.hasNext()) {
                    map.put(in.nextName(), read(in));
                }
                in.endObject();
                return map;
            case STRING:
                return in.nextString();
            case NUMBER:
                // 将其作为一个字符串读取出来
                String numberStr = in.nextString();
                // 返回的numberStr不会为null
                if (numberStr.contains(".") || numberStr.contains("e")
                        || numberStr.contains("E")) {
                    return Double.parseDouble(numberStr);
                }
                if (Long.parseLong(numberStr) <= Integer.MAX_VALUE) {
                    return Integer.parseInt(numberStr);
                }
                return Long.parseLong(numberStr);
            case BOOLEAN:
                return in.nextBoolean();
            case NULL:
                in.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }
}
