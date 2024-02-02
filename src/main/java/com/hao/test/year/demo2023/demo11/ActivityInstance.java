package com.hao.test.year.demo2023.demo11;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ActivityInstance {
    private long activityInstId;
    private String activityInstName;
    private String description;
    private String activityType;
    private String activityDefId;
    private String repoId;
    private long processInstId;
    private int currentState;
    private double limitTime;
    private Double advancedTime;
    private String calendarName;
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private Date finalTime;
    private String actClass;
    private long shardingId;
    private List<Long> workItemsId;
    private String tenantId;
    private String overTime;
    private String remindTime;
    private int remindState;
    private int processState;
    private String apiCode;
    private String apiVersion;
    private String apiResult;
    private String shortNote;
    private String checkStatus;
    private String formId;

    public static void main(String[] args) throws Exception {
        ActivityInstance activityInstance = new ActivityInstance();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(activityInstance);
        System.out.println(json);
    }
}
