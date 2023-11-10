package com.sky.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel("员工类")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("员工账号")
    private String username;

    @ApiModelProperty("员工密码")
    private String password;

    @ApiModelProperty("员工姓名")
    private String name;

    @ApiModelProperty("员工手机号")
    private String phone;

    @ApiModelProperty("员工性别")
    private String sex;

    @ApiModelProperty("员工身份证号")
    private String idNumber;

    @ApiModelProperty("员工账户状态")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("账户创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("账户更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("账户创建人")
    private Long createUser;

    @ApiModelProperty("账户修改人")
    private Long updateUser;

}
