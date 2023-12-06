package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
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
 * @create: 2023-12-05 08:43
 * @description: 套餐控制器
 **/
@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐相关接口")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;


    /**
     * @param setmealDTO
     * @return com.sky.result.Result
     * @author AlbertZhang
     * @description 新增套餐
     * @date 2023-12-06 15:04
     **/
    @PostMapping
    @ApiOperation("新增套餐接口")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐，套餐信息为：{}", setmealDTO);
        setmealService.save(setmealDTO);
        return Result.success();
    }

    /**
     * @param
     * @return com.sky.result.Result<com.sky.result.PageResult>
     * @author AlbertZhang
     * @description 对套餐进行分页查询
     * @date 2023-12-06 15:42
     **/
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("套餐分页查询：{}", setmealPageQueryDTO);
        PageResult pageResult = setmealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * @param ids
     * @return com.sky.result.Result
     * @author AlbertZhang
     * @description 根据id或ids删除套餐
     * @date 2023-12-06 16:33
     **/
    @DeleteMapping
    @ApiOperation("根据id或ids删除套餐")
    public Result deleteByIdOrIds(@ApiParam("要删除的套餐id") @RequestParam List<Long> ids) {
        log.info("删除套餐，套餐id为：{}", ids);
        setmealService.deleteByIdOrIds(ids);
        return Result.success();
    }

    /**
     * @param id
     * @return com.sky.result.Result<com.sky.vo.SetmealVO>
     * @author AlbertZhang
     * @description 根据id查询套餐
     * @date 2023-12-06 18:49
     **/
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> selectById(@ApiParam("套餐id") @PathVariable Long id) {
        log.info("根据id查询套餐，套餐id为：{}", id);
        SetmealVO setmealVO = setmealService.selectById(id);
        return Result.success(setmealVO);
    }

    /**
     * @param setmealDTO
     * @return com.sky.result.Result
     * @author AlbertZhang
     * @description 修改套餐
     * @date 2023-12-06 20:32
     **/
    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("更新套餐信息：{}", setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * @param status
     * @param id
     * @return com.sky.result.Result
     * @author AlbertZhang
     * @description 起售停售套餐
     * @date 2023-12-06 21:07
     **/
    @PostMapping("/status/{status}")
    @ApiOperation("停售起售套餐")
    public Result startOrStop(@ApiParam("套餐状态，1起售0停售") @PathVariable Integer status,
                              @ApiParam("当前修改套餐id") @RequestParam Long id) {
        log.info("起售停售套餐：{}:{}", id, status);
        setmealService.startOrStop(id, status);
        return Result.success();
    }
}
