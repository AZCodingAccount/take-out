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

    /**
     * @author AlbertZhang
     * @description 启用或禁用员工账号
     * @date 20:36 2023-11-11
     * @param id
     * @param status
     * @return void
     **/
    void startOrStop(Long id, Integer status);

    /**
     * @author AlbertZhang
     * @description 根据id查询员工信息
     * @date 21:13 2023-11-11
     * @param id
     * @return com.sky.entity.Employee
     **/
    Employee selectByID(Long id);

    /**
     * @author AlbertZhang
     * @description 更新员工信息
     * @date 2023-11-11
     * @param employeeDTO
     * @return void
     **/
    void update(EmployeeDTO employeeDTO);
}
