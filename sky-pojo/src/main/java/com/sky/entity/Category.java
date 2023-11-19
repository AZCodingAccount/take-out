package com.sky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分类实体类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类id")
    private Long id;

    //类型: 1菜品分类 2套餐分类
    @ApiModelProperty("分类类型")
    private Integer type;

    //分类名称
    @ApiModelProperty("分类名称")
    private String name;

    //顺序
    @ApiModelProperty("分类顺序")
    private Integer sort;

    //分类状态 0标识禁用 1表示启用
    @ApiModelProperty("分类状态")
    private Integer status;

    //创建时间
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    //更新时间
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    //创建人
    @ApiModelProperty("创建人")
    private Long createUser;

    //修改人
    @ApiModelProperty("修改人")
    private Long updateUser;
}
