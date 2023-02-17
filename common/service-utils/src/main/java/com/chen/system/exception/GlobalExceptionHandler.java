package com.chen.system.exception;

import com.chen.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理程序
 *
 * @author CHEN
 * @date 2023/01/28
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * z自定义参数校验异常
     *
     * @param e e
     * @return {@link Result}<{@link Object}>
     */
    @ExceptionHandler(BindException.class)
    public Result<Object> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Result.fail(message);
    }


    /**
     * 请求方法不支持
     *
     * @param e       e
     * @param request 请求
     * @return {@link Result}
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                  HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestUri, e.getMethod());
        return Result.fail("请求地址'"+requestUri+"',不支持'"+e.getMethod()+"'请求");
    }

    /**
     * 拦截未知的运行时异常
     *
     * @param e       e
     * @param request 请求
     * @return {@link Result}
     */
    @ExceptionHandler(ServiceException.class)
    public Result<Object> handleServiceException(ServiceException e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestUri, e);
        return Result.fail(e.getMessage());
    }


    /**
     * 拦截未知的运行时异常
     *
     * @param e       e
     * @param request 请求
     * @return {@link Result}
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<Object> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestUri, e);
        return Result.fail("请求地址'" + requestUri + "',发生未知异常");
    }


    /**
     * 系统异常
     *
     * @param e       e
     * @param request 请求
     * @return {@link Result}
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestUri, e);
        return Result.fail("请求地址'" + requestUri + "',发生未知异常");
    }
}