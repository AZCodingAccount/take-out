package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("菜品分页查询DTO")
public class DishPageQueryDTO implements Serializable {

    @ApiModelProperty("页码")
    private int page;

    @ApiModelProperty("每页记录数")
    private int pageSize;

    @ApiModelProperty("菜品名称")
    private String name;

    //分类id
    @ApiModelProperty("分类id")
    private Integer categoryId;

    //状态 0表示禁用 1表示启用
    @ApiModelProperty("菜品状态")
    private Integer status;

}
