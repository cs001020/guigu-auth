package com.chen.system.exception;

/**
 * 服务异常
 *
 * @author CHEN
 * @date 2023/02/16
 */
public class ServiceException extends RuntimeException{
    private String msg;

    public ServiceException(String msg) {
        super(msg);
    }
}
