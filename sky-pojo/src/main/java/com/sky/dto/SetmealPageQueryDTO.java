package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel("套餐分页查询DTO")
public class SetmealPageQueryDTO implements Serializable {

    @ApiModelProperty("套餐查询页码")
    private int page;

    @ApiModelProperty("套餐查询每页记录数")
    private int pageSize;

    @ApiModelProperty("套餐名称")
    private String name;

    //分类id
    @ApiModelProperty("套餐所属分类id")
    private Integer categoryId;

    //状态 0表示禁用 1表示启用
    @ApiModelProperty("套餐状态")
    private Integer status;

}
