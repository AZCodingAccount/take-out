package com.sky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 套餐菜品关系
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("菜品套餐关系")
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    //套餐id
    @ApiModelProperty("所属套餐id")
    private Long setmealId;

    //菜品id
    @ApiModelProperty("菜品id")
    private Long dishId;

    //菜品名称 （冗余字段）
    @ApiModelProperty("菜品名称")
    private String name;

    //菜品原价
    @ApiModelProperty("菜品原价")
    private BigDecimal price;

    //份数
    @ApiModelProperty("菜品份数")
    private Integer copies;
}
