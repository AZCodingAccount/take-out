package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * @program: sky-take-out
 * @author: AlbertZhang
 * @create: 2023-12-05 08:55
 * @description: 套餐service接口
 **/
public interface SetmealService {
    /**
     * @author AlbertZhang
     * @description 新增套餐
     * @date 2023-12-06 15:44
     * @param setmealDTO
     * @return void
     **/
    void save(SetmealDTO setmealDTO);


    /**
     * @author AlbertZhang
     * @description 套餐分页查询
     * @date 2023-12-06 15:44
     * @param setmealPageQueryDTO
     * @return com.sky.result.PageResult
     **/
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * @author AlbertZhang
     * @description 根据id或ids删除套餐
     * @date 2023-12-06 16:33
     * @param ids
     * @return void
     **/
    void deleteByIdOrIds(List<Long> ids);

    /**
     * @author AlbertZhang
     * @description 根据id查询套餐
     * @date 2023-12-06 18:48
     * @param id
     * @return com.sky.vo.SetmealVO
     **/
    SetmealVO selectById(Long id);

    /**
     * @author AlbertZhang
     * @description TODO
     * @date 2023-12-06 20:35
     * @param setmealDTO
     * @return void
     **/
    void update(SetmealDTO setmealDTO);


    /**
     * @author AlbertZhang
     * @description 起售停售套餐
     * @date 2023-12-06 21:08
     * @param id
     * @param status
     * @return void
     **/
    void startOrStop(Long id, Integer status);
}
