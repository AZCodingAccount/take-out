package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorsMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sky-take-out
 * @author: AlbertZhang
 * @create: 2023-11-30 22:10
 * @description:
 **/
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorsMapper dishFlavorsMapper;

    /**
     * @param dishDTO
     * @return void
     * @author AlbertZhang
     * @description 保存菜品信息
     * @date 2023-11-30 22:11
     **/
    @Override
    public void save(DishDTO dishDTO) {
        // 把dishDTO数据拷贝到dish对象里面去，插入数据
        Dish dish=new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        // 往dish表中先把数据插入进去
        dishMapper.insert(dish);

        Long id = dish.getId(); // 获取插入进去的时候的主键值，需要配置useGenerateKey

        // 往口味表中插入n条数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (!flavors.isEmpty()) {
            // 给这些口味设置关联的菜品id
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(id);
            }
            // 插入数据
            dishFlavorsMapper.insertBatch(flavors);
        }
    }
}
