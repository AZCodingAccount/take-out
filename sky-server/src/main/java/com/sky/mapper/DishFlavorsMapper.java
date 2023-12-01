package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: sky-take-out
 * @author: AlbertZhang
 * @create: 2023-12-01 18:24
 * @description: 菜品口味表mapper
 **/
@Mapper
public interface DishFlavorsMapper {

    void insertBatch(List<DishFlavor> flavors);

}
