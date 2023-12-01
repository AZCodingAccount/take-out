package com.sky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("菜品表实体类")
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜品id")
    private Long id;

    //菜品名称
    @ApiModelProperty("菜品名称")
    private String name;

    //菜品分类id
    @ApiModelProperty("菜品分类id")
    private Long categoryId;

    //菜品价格
    @ApiModelProperty("菜品价格")
    private BigDecimal price;

    //图片
    @ApiModelProperty("菜品图片")
    private String image;

    //描述信息
    @ApiModelProperty("菜品描述")
    private String description;

    //0 停售 1 起售
    @ApiModelProperty("菜品当前状态")
    private Integer status;

    @ApiModelProperty("菜品创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("菜品更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("菜品创建人")
    private Long createUser;

    @ApiModelProperty("菜品更新人")
    private Long updateUser;

}
