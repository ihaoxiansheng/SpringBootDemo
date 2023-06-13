// package com.hao.springbootdemo.util;
//
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
//
// /**
//  * SpringBoot全局异常处理
//  *
//  * @author xu.liang
//  * @since 2022/10/12 16:24
//  */
// @RestControllerAdvice
// public class GlobalExceptionHandler {
//
//     @ExceptionHandler(Exception.class)
//     public String handleException(Exception e) {
//         if (e instanceof ArithmeticException) {
//             return "数据异常";
//         }
//         if (e instanceof Exception) {
//             return "服务器内部异常";
//         }
//         return null;
//     }
// }
