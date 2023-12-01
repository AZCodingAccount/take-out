package com.sky.service;

import com.sky.dto.DishDTO;

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

}
