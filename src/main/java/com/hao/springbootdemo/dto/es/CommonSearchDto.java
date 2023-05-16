package com.hao.springbootdemo.dto.es;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 只含id和索引名称的dto
 */
@Data
public class CommonSearchDto {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("索引名称")
    private String indexName;

}
