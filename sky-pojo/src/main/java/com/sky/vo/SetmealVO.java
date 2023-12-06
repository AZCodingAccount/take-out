package com.sky.vo;

import com.sky.entity.SetmealDish;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("套餐VO类")
public class SetmealVO implements Serializable {

    @ApiModelProperty("套餐id")
    private Long id;

    // 分类id
    @ApiModelProperty("套餐分类id")
    private Long categoryId;

    // 套餐名称
    @ApiModelProperty("套餐名称")
    private String name;

    // 套餐价格
    @ApiModelProperty("套餐价格")
    private BigDecimal price;

    // 状态 0:停用 1:启用
    @ApiModelProperty("套餐状态")
    private Integer status;

    // 描述信息
    @ApiModelProperty("套餐描述")
    private String description;

    // 图片
    @ApiModelProperty("套餐描述图片")
    private String image;

    // 更新时间
    @ApiModelProperty("套餐更新时间")
    private LocalDateTime updateTime;

    // 分类名称
    @ApiModelProperty("套餐所属分类名称")
    private String categoryName;

    // 套餐和菜品的关联关系
    @ApiModelProperty("套餐和菜品关联关系（可为空）")
    private List<SetmealDish> setmealDishes = new ArrayList<>();
}
