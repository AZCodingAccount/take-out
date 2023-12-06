package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.ExtendsConstant;
import com.sky.constant.MessageConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.SetMealException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sky-take-out
 * @author: AlbertZhang
 * @create: 2023-12-05 08:55
 * @description: 套餐service实现类
 **/
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * @param setmealDTO
     * @return void
     * @author AlbertZhang
     * @description 新增套餐
     * @date 2023-12-05 9:00
     **/
    @Override
    public void save(SetmealDTO setmealDTO) {
        /* 需要满足下面几个条件:
            1:套餐必须属于某个分类：看看传过来的包不包含category_id
            2：套餐名称唯一：根据套餐名称查询，看看查询出来的条数
            3：套餐包含菜品：看list集合里面的长度
            4：名称、分类、价格、图片为必填项
        */
        Long categoryId = setmealDTO.getCategoryId();

        // 检查套餐是否属于分类
        if (categoryId == 0) {
            throw new SetMealException(ExtendsConstant.CATEGORY_CONTAINS_SETMEAL);
        }
        // 检查套餐是否包含菜品
        List<SetmealDish> dishes = setmealDTO.getSetmealDishes();
        if (dishes == null || dishes.isEmpty()) {
            throw new SetMealException(ExtendsConstant.SETMEAL_CONTAINS_DISH);
        }
        // 检查套餐名称是否唯一
        Setmeal setmeal = setmealMapper.selectByName(setmealDTO.getName());
        if (setmeal != null) {
            throw new SetMealException(ExtendsConstant.SETMEAL_NAME_UNIQUE);
        }

        // 插入数据（setmeal表数据和setmeal_dish表数据）
        Setmeal setmealEntity = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmealEntity);
        setmealMapper.insert(setmealEntity);
        Long id = setmealEntity.getId();
        // 增强for遍历插入菜品
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(id);
        });
        setMealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * @param setmealPageQueryDTO
     * @return com.sky.result.PageResult
     * @author AlbertZhang
     * @description 套餐分页查询
     * @date 2023-12-06 15:44
     **/
    @Override
    public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        // 根据条件查询数据
        Page<SetmealVO> page = setmealMapper.page(setmealPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * @param ids
     * @return void
     * @author AlbertZhang
     * @description 根据id或ids删除套餐
     * @date 2023-12-06 16:33
     **/
    @Override
    @Transactional
    public void deleteByIdOrIds(List<Long> ids) {
        // 需要判断一下当前套餐的状态，是不是起售
        for (Long id : ids) {
            SetmealVO setmealVO = setmealMapper.selectById(id);
            if (setmealVO.getStatus() == 1) {
                throw new SetMealException(MessageConstant.SETMEAL_ON_SALE);
            }
        }

        // 直接把集合传递进去，批量删除,in
        setmealMapper.deleteBatch(ids);
        // 删除套餐——菜品关系表中的数据
        setMealDishMapper.deleteBatch(ids);
    }

    /**
     * @param id
     * @return com.sky.vo.SetmealVO
     * @author AlbertZhang
     * @description 根据id查询套餐
     * @date 2023-12-06 18:50
     **/
    @Override
    public SetmealVO selectById(Long id) {
        // 多表联查，setmeal表查询套餐基本信息、setmeal_dish查询套餐关联的菜品、category表查询分类名称
        return setmealMapper.selectById(id);
    }

    /**
     * @param setmealDTO
     * @return void
     * @author AlbertZhang
     * @description 修改套餐
     * @date 2023-12-06 20:32
     **/
    @Override
    @Transactional
    public void update(SetmealDTO setmealDTO) {
        // 这里需要先把属性拷贝到SetMeal实体类里面，因为时间和创建人这些DTO里面没有
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        // 把套餐数据更新到套餐表，再更新setmeal_dish这个表
        // 更新setmeal表
        setmealMapper.update(setmeal);

        // 更新菜品表，这里直接先把setmeal_dish表里面的这个套餐的菜品全删了，然后添加进去新的
        List<Long> ids = new ArrayList<>();
        ids.add(setmealDTO.getId());
        setMealDishMapper.deleteBatch(ids); // 调用之前删除套餐关联的菜品的方法
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        // 把setmealId给setmealDishes里面加进去，前端传过来的没有
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealDTO.getId());
        }
        setMealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * @param id
     * @param status
     * @return void
     * @author AlbertZhang
     * @description 起售停售套餐
     * @date 2023-12-06 21:08
     **/
    @Override
    @Transactional
    public void startOrStop(Long id, Integer status) {
        // 如果套餐关联的菜品在停售，套餐不能起售
        if (status == 1) {
            // 拿着id和setmeal_dish,dish这两个表进行联查，返回List<dish>对象
            List<Dish> dishes = setMealDishMapper.selectDishesBySetmealId(id);

            // 循环dishes对象，如果有菜品停售的情况，抛一个异常
            for (Dish dish : dishes) {
                if (dish.getStatus() == 0) {
                    throw new SetMealException(MessageConstant.SETMEAL_ENABLE_FAILED);
                }
            }
        }
        // 接下来直接调用套餐的update方法即可
        Setmeal setmeal = Setmeal.builder().id(id)
                .status(status).
                build();
        setmealMapper.update(setmeal);
    }
}
