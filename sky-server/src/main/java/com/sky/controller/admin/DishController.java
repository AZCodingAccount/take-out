package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: sky-take-out
 * @author: AlbertZhang
 * @create: 2023-11-29 22:00
 * @description: 菜品控制器
 **/

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * @param dish
     * @return com.sky.result.Result
     * @author AlbertZhang
     * @description 新增菜品
     * @date 2023-12-01 20:12
     **/
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dish) {
        log.info("新增菜品：{}", dish);
        dishService.save(dish);
        return Result.success(null);
    }


    /**
     * @param dishPageQueryDTO
     * @return com.sky.result.Result<com.sky.result.PageResult>
     * @author AlbertZhang
     * @description 分页查询菜品
     * @date 2023-12-01 20:12
     **/
    @GetMapping("page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询菜品数据：{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * @param ids
     * @return com.sky.result.Result
     * @author AlbertZhang
     * @description 根据id删除菜品
     * @date 2023-12-01 20:19
     **/
    @DeleteMapping
    @ApiOperation("根据id删除菜品")
    public Result deleteByIdOrIds(@ApiParam("要删除的菜品id") @RequestParam List<Long> ids) {
        log.info("根据id删除菜品:{}", ids);
        dishService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * @param id
     * @return com.sky.result.Result<com.sky.vo.DishVO>
     * @author AlbertZhang
     * @description 根据id查询菜品
     * @date 2023-12-01 21:01
     **/
    @GetMapping("{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> selectById(@PathVariable Integer id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dish = dishService.selectById(id);
        return Result.success(dish);
    }

    /**
     * @param dishDTO
     * @return com.sky.result.Result
     * @author AlbertZhang
     * @description 修改菜品信息
     * @date 2023-12-01 21:10
     **/
    @PutMapping
    @ApiOperation(("修改菜品信息"))
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品信息：{}", dishDTO);
        dishService.update(dishDTO);
        return Result.success();
    }

    /**
     * @param status
     * @param id
     * @return com.sky.result.Result
     * @author AlbertZhang
     * @description 菜品起售停售
     * @date 2023-12-01 21:27
     **/
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售停售")
    public Result startOrStop(@ApiParam("菜品状态(0停售，1起售)") @PathVariable Integer status,
                              @ApiParam("菜品id") @RequestParam Long id) {

        log.info("修改菜品{}的起售停售：{}", id, status);
        dishService.startOrStop(id, status);
        return Result.success();
    }

}
