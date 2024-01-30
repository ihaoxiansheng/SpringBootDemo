package com.hao.test.demo11;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Date;

@Data
public class ProcessInstance {
    private long processInstId;
    private long processDefId;
    private String processInstName;
    private String creator;
    private int currentState;
    private String subFlow;
    private Double limitNum;
    private Double advancedTime;
    private String calendarName;
    private long mainProcInstId;
    private long parentProcInstId;
    private long activityInstId;
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private Date finalTime;
    private long shardingId;
    private String tenantId;
    private String overTime;
    private String remindTime;
    private int remindState;

    public static void main(String[] args) throws Exception {
        ProcessInstance processInstance = new ProcessInstance();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(processInstance);
        System.out.println(json);
    }
}
