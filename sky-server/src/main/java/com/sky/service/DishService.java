package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * @program: sky-take-out
 * @author: AlbertZhang
 * @create: 2023-11-30 22:09
 * @description: 菜品service
 **/
public interface DishService {
    /**
     * @author AlbertZhang
     * @description 保存菜品
     * @date 2023-11-30 22:10
     * @param dish
     * @return void
     **/

    void save(DishDTO dish);

    /**
     * @author AlbertZhang
     * @description 根据条件分页查询
     * @date 2023-12-01 19:55
     * @param dishPageQueryDTO
     * @return com.sky.result.PageResult
     **/
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * @author AlbertZhang
     * @description 根据id删除菜品
     * @date 2023-12-01 20:19
     * @param ids
     * @return void
     **/
    void deleteByIds(List<Long> ids);

    /**
     * @author AlbertZhang
     * @description 根据id查询菜品
     * @date 2023-12-01 21:01
     * @param id
     * @return com.sky.vo.DishVO
     **/
    DishVO selectById(Integer id);

    /**
     * @author AlbertZhang
     * @description 修改菜品信息
     * @date 2023-12-01 21:10
     * @param dishDTO
     * @return void
     **/
    void update(DishDTO dishDTO);

    /**
     * @author AlbertZhang
     * @description 修改菜品起售停售状态
     * @date 2023-12-01 21:29
     * @param id
     * @param status
     * @return void
     **/
    void startOrStop(Long id, Integer status);

    /**
     * @author AlbertZhang
     * @description 根据分类id查询所属菜品
     * @date 2023-12-06 15:17
     * @param categoryId
     * @return java.util.List<com.sky.entity.Dish>
     **/
    List<Dish> list(Long categoryId);
}
