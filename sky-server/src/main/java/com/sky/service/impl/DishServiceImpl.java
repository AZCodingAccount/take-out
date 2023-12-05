package com.sky.service.impl;

import com.sky.constant.MessageConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorsMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * @param dishDTO
     * @return void
     * @author AlbertZhang
     * @description 保存菜品信息
     * @date 2023-11-30 22:11
     **/
    @Transactional
    @Override
    public void save(DishDTO dishDTO) {
        // 把dishDTO数据拷贝到dish对象里面去，插入数据
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
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

    /**
     * @param dishPageQueryDTO
     * @return com.sky.result.PageResult
     * @author AlbertZhang
     * @description 根据条件分页查询菜品
     * @date 2023-12-01 19:55
     **/
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        // 根据条件去dish表和category表查询数据，进行多表联查
        List<DishVO> dishes = dishMapper.pageQuery(dishPageQueryDTO);
        PageResult pageResult = new PageResult();
        if (dishes != null && !dishes.isEmpty()) {
            // 拼装数据
            pageResult.setRecords(dishes);
            pageResult.setTotal(dishes.size());
        }
        return pageResult;
    }

    /**
     * @param ids
     * @return void
     * @author AlbertZhang
     * @description 根据id删除菜品
     * @date 2023-12-01 20:19
     **/
    @Override
    public void deleteByIds(List<Long> ids) {
        /*
           规则1：起售中的菜品不能删除
           规则2：被套餐关联的菜品不能删除
           规则3：删除菜品后，dishFlavors表里面的口味也应该删除掉
        */
        // 根据ids查询出菜品的状态
        for (Long id : ids) {
            DishVO dish = dishMapper.queryById(id);
            if (dish.getStatus() == 1) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
            if (setMealDishMapper.selectByDishId(id) != 0) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
            }

            // 开始删除菜品（当然也可以分开删除）
            dishMapper.deleteDishAndFlavorsById(id);
        }
    }

    /**
     * @param id
     * @return com.sky.vo.DishVO
     * @author AlbertZhang
     * @description 根据id查询菜品
     * @date 2023-12-01 21:02
     **/
    @Override
    public DishVO selectById(Integer id) {
        return dishMapper.queryById(Long.valueOf(id));
    }

    /**
     * @param dishDTO
     * @return void
     * @author AlbertZhang
     * @description 修改菜品
     * @date 2023-12-01 21:11
     **/
    @Transactional
    @Override
    public void update(DishDTO dishDTO) {
        Dish dish=new Dish();
        // 把属性拷贝一下，等下要公共字段填充
        BeanUtils.copyProperties(dishDTO,dish);

        dishMapper.update(dish);
    }

    /**
     * @author AlbertZhang
     * @description 修改菜品起售停售状态
     * @date 2023-12-01 21:29
     * @param id
     * @param status
     * @return void
     **/
    @Override
    public void startOrStop(Long id, Integer status) {
        // 这里直接调用以前的update方法即可
        Dish dish=Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.update(dish);
    }
}
