package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.result.Result;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * @param []
     * @return void
     * @author AlbertZhang
     * @description 创建员工信息
     * @date 19:56 2023-11-09
     **/
    Result<String> save(EmployeeDTO employeeDTO);

    /**
     * @author AlbertZhang
     * @description 员工分页查询
     * @date 21:08 2023-11-10
     * @param employeePageQueryDTO
     * @return com.sky.result.Result<com.sky.result.PageResult>
     **/
    Result<PageResult> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
