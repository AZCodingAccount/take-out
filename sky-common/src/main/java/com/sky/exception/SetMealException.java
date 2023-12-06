package com.sky.exception;

/**
 * @program: sky-take-out
 * @author: AlbertZhang
 * @create: 2023-12-05 09:32
 * @description: 套餐异常
 **/
public class SetMealException extends BaseException{
    public SetMealException() {
        super();
    }

    public SetMealException(String msg) {
        super(msg);
    }
}
