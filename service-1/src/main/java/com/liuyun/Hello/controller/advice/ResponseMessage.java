package com.liuyun.Hello.controller.advice;

public interface ResponseMessage {

    int getRespCode();

    Object getMessage();

    int getErrorCode();
}
