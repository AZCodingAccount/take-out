package com.sky.vo;

import com.sky.entity.DishFlavor;
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
@ApiModel("DishVO对象")
public class DishVO implements Serializable {


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
    @ApiModelProperty("菜品图片路径")
    private String image;
    //描述信息
    @ApiModelProperty("菜品描述")
    private String description;
    //0 停售 1 起售
    @ApiModelProperty("菜品状态")
    private Integer status;
    //更新时间
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
    //分类名称
    @ApiModelProperty("菜品所属分类名称")
    private String categoryName;
    //菜品关联的口味
    @ApiModelProperty("菜品关联的口味")
    private List<DishFlavor> flavors = new ArrayList<>();

    //private Integer copies;
}
