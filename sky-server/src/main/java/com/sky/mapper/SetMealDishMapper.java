package com.sky.mapper;

import com.sky.entity.Dish;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: sky-take-out
 * @author: AlbertZhang
 * @create: 2023-12-01 20:31
 * @description: 套餐菜品查询mapper
 **/
@Mapper
public interface SetMealDishMapper {

    /**
     * @author AlbertZhang
     * @description 根据菜品id查询关联套餐的个数（删除菜品时使用）
     * @date 2023-12-01 20:33
     * @param id
     * @return int
     **/
    @Select("select count(*) from setmeal_dish where dish_id=#{id};")
    int selectByDishId(Long id);

    /**
     * @author AlbertZhang
     * @description 批量插入数据，新增套餐时候使用
     * @date 2023-12-06 14:59
     * @param setmealDishes
     * @return void
     **/
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * @author AlbertZhang
     * @description 根据多个套餐id删除与其关联的菜品—删除套餐功能
     * @date 2023-12-06 16:54
     * @param ids
     * @return void
     **/
    void deleteBatch(List<Long> ids);


    /**
     * @param id
     * @return void
     * @author AlbertZhang
     * @description 根据套餐id查询所有菜品信息—实现套餐起售时判断是否有停售菜品
     * @date 2023-12-06 21:15
     **/
    List<Dish> selectDishesBySetmealId(Long id);
}
