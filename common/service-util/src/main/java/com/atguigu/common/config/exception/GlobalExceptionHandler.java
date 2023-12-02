package com.atguigu.common.config.exception;

import com.atguigu.common.execption.GuiguException;
import com.atguigu.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice  // 将异常处理加入到当前
public class GlobalExceptionHandler {
    // 全局异常处理，执行的方法
    @ExceptionHandler(Exception.class)  // 指定异常执行，当出现异常会执行
    @ResponseBody // 返回json
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行全局异常处理...");
    }

    // 特定异常的处理
    @ExceptionHandler(ArithmeticException.class)  // 指定异常执行，当出现异常会执行
    @ResponseBody // 返回json
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("执行特定异常处理...");
    }

    // 自定义异常处理
    @ExceptionHandler(GuiguException.class)
    @ResponseBody
    public Result error(GuiguException e){
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMessage());
    }
//    @ExceptionHandler(GuiguException.class)
//    @ResponseBody
//    public Result error(GuiguException e){
//        e.printStackTrace();
//        return Result.fail().message(e.getMsg()).code(e.getCode());
//    }
}
