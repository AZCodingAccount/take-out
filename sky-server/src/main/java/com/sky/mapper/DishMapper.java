package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * @param dish
     * @return void
     * @author AlbertZhang
     * @description 保存菜品
     * @date 2023-11-30 22:08
     **/
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * @param dishPageQueryDTO
     * @return java.util.List<com.sky.vo.DishVO>
     * @author AlbertZhang
     * @description 分页查询
     * @date 2023-12-01 20:38
     **/
    List<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * @param id
     * @return com.sky.vo.DishVO
     * @author AlbertZhang
     * @description 根据id查询菜品
     * @date 2023-12-01 20:38
     **/
    @Select("select * from dish where id=#{id}")
    DishVO queryById(Long id);

    /**
     * @param id
     * @return void
     * @author AlbertZhang
     * @description 根据id删除菜品和口味数据
     * @date 2023-12-01 20:38
     **/
    void deleteDishAndFlavorsById(Long id);

    /**
     * @param dishDTO
     * @return void
     * @author AlbertZhang
     * @description 更新菜品表
     * @date 2023-12-01 21:12
     **/
    @AutoFill(OperationType.UPDATE)
    void update(Dish dishDTO);

    /**
     * @param categoryId
     * @return java.util.List<com.sky.entity.Dish>
     * @author AlbertZhang
     * @description 根据分类id查询所有菜品
     * @date 2023-12-06 15:19
     **/
    @Select("select * from sky_take_out.dish where category_id=#{categoryId}")
    List<Dish> getDishesById(Long categoryId);
}
