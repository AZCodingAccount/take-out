package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        // 1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        // 2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            // 账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 密码比对
        // 进行Md5加密，还可以加点盐
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            // 密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (Objects.equals(employee.getStatus(), StatusConstant.DISABLE)) {
            // 账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        // 3、返回实体对象
        return employee;
    }

    /**
     * @param employeeDTO
     * @return com.sky.result.Result<java.lang.String>
     * @author AlbertZhang
     * @description 创建员工信息
     * @date 21:17 2023-11-09
     **/
    @Override
    @Transactional
    public Result<String> save(EmployeeDTO employeeDTO) {
        // 这里对前端发送过来的全部为空的参数还是没有校验，后续使用注解的方式校验
        if (employeeDTO == null) {
            return Result.error("员工信息为空，不能添加");
        }
        // 拷贝属性
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        // 添加上一些属性
        // 设置账号的状态，默认正常状态 1表示正常 0表示锁定
        employee.setStatus(StatusConstant.ENABLE);

        // 设置密码，默认密码123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        // 从ThreadLocal里面拿id
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());
        int i = employeeMapper.insert(employee);
        if (i == 0) {
            return Result.error("添加失败");
        }
        return Result.success();
    }

    /**
     * @param employeePageQueryDTO
     * @return com.sky.result.Result<com.sky.result.PageResult>
     * @author AlbertZhang
     * @description 员工分页查询
     * @date 21:09 2023-11-10
     **/
    @Override
    public Result<PageResult> pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        // 使用PageHelper插件
        // 开始分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        // 查询出来数据
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        // 获取总条数
        long total = page.getTotal();
        List<Employee> employees = page.getResult();
        if (total != 0 && !employees.isEmpty()) {
            // 组转成PageResult对象
            PageResult pageResult = new PageResult(total, employees);
            return Result.success(pageResult);
        }
        return Result.error("分页查询数据为空");

    }

    /**
     * @param id
     * @param status
     * @return void
     * @author AlbertZhang
     * @description 启用或禁用员工账号
     * @date 20:36 2023-11-11
     **/
    @Override
    @Transactional
    public void startOrStop(Long id, Integer status) {
        // 直接调用这个方法，这样更新状态和更新员工信息都可以调用这个方法
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employeeMapper.update(employee);
    }

    /**
     * @param id
     * @return com.sky.entity.Employee
     * @author AlbertZhang
     * @description 根据id查询员工信息
     * @date 21:13 2023-11-11
     **/
    @Override
    public Employee selectByID(Long id) {
        Employee employee = employeeMapper.selectByID(id);
        return employee;
    }

    /**
     * @param employeeDTO
     * @return void
     * @author AlbertZhang
     * @description 更新员工信息
     * @date 2023-11-11
     **/
    @Transactional
    @Override
    public void update(EmployeeDTO employeeDTO) {
        // 把employeeDTO里面的属性拷贝到employee里面
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        // 接下来的公共属性就不再考虑了，到时候自己加上注解采用AOP自动填充了，之前的修改状态也是
        employeeMapper.update(employee);
    }
}
