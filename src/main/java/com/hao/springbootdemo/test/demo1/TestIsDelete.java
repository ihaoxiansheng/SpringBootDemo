package com.hao.springbootdemo.test.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试实体字段isDelete命名
 *
 * @author xu.liang
 * @since 2022/10/18 13:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestIsDelete {
    /**
     * 非布尔类型
     * 不带is开头：get与set方法正常
     * 带is开头 ：get与set方法正常
     */
    private String start;
    private String isStop;
    /**
     * boolean基本类型，没有get方法，而是以isXXX开头的方法，与setXXX的方法。
     * 不带is开头：正常isXXX，setXXX
     * 带is开头 ：is与set方法都会自动去掉属性名称中的is
     */
    private boolean lower;
    private boolean isUpper;
    /**
     * Boolean包装类型，正常getXXX，setXXX
     * 不带is开头：正常getXXX，setXXX
     * 带is开头 ：get与set方法都会自动去掉属性名称中的is
     */
    private Boolean del;
    private Boolean isDeleted;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getIsStop() {
        return isStop;
    }

    public void setIsStop(String isStop) {
        this.isStop = isStop;
    }

    public boolean isLower() {
        return lower;
    }

    public void setLower(boolean lower) {
        this.lower = lower;
    }

    public boolean isUpper() {
        return isUpper;
    }

    public void setUpper(boolean upper) {
        isUpper = upper;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}

