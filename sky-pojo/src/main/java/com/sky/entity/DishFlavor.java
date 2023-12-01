package com.sky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜品口味
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("菜品口味表实体类")
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("口味id")
    private Long id;
    //菜品id
    @ApiModelProperty("菜品id")
    private Long dishId;

    //口味名称
    @ApiModelProperty("口味名称")
    private String name;

    //口味数据list
    @ApiModelProperty("口味详细数据(list)")
    private String value;

}
