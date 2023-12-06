package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     *
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * @param name
     * @return com.sky.entity.Setmeal
     * @author AlbertZhang
     * @description 根据套餐名称查询套餐实体（检测套餐名称是否唯一）
     * @date 2023-12-06 14:47
     **/
    @Select("select * from setmeal where name=#{name}")
    Setmeal selectByName(String name);


    /**
     * @author AlbertZhang
     * @description 新增套餐—>操作setmeal和setmeal-dish表
     * @date 2023-12-06 15:57
     * @param setmealEntity
     * @return void
     **/
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmealEntity);

    /**
     * @author AlbertZhang
     * @description 套餐分页查询
     * @date 2023-12-06 15:56
     * @param setmealPageQueryDTO
     * @return java.util.List<com.sky.entity.Setmeal>
     **/
    Page<SetmealVO> page(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * @author AlbertZhang
     * @description 根据id或ids删除套餐
     * @date 2023-12-06 16:35
     * @param ids
     * @return void
     **/
    void deleteBatch(List<Long> ids);

    /**
     * @author AlbertZhang
     * @description 根据id查询菜品——实现修改时数据回显功能
     * @date 2023-12-06 18:52
     * @param id
     * @return com.sky.vo.SetmealVO
     **/
    SetmealVO selectById(Long id);

    /**
     * @author AlbertZhang
     * @description 根据套餐信息更新套餐
     * @date 2023-12-06 20:40
     * @param setmeal
     * @return void
     **/
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);
}