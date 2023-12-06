package com.sky.dto;

import com.sky.entity.SetmealDish;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("新增套餐DTO")
public class SetmealDTO implements Serializable {

    @ApiModelProperty("套餐id")
    private Long id;

    //分类id
    @ApiModelProperty("套餐所属分类id")
    private Long categoryId;

    //套餐名称
    @ApiModelProperty("套餐名称")
    private String name;

    //套餐价格
    @ApiModelProperty("套餐价格")
    private BigDecimal price;

    //状态 0:停用 1:启用
    @ApiModelProperty("套餐当前状态")
    private Integer status;

    //描述信息
    @ApiModelProperty("套餐描述信息")
    private String description;

    //图片
    @ApiModelProperty("套餐图片")
    private String image;

    //套餐菜品关系
    @ApiModelProperty("套餐所包含菜品")
    private List<SetmealDish> setmealDishes = new ArrayList<>();

}
