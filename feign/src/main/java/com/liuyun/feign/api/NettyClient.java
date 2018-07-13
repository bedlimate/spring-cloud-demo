package com.liuyun.feign.api;

import feign.Client;
import feign.Request;
import feign.Response;

import java.io.IOException;

/***
 * todo 此处后续考虑使用netty实现httpClient替代默认实现
 */
public class NettyClient implements Client {

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        return null;
    }

//    private FullHttpRequest convertAndSend(Request request, Request.Options options){
//        return null;
//    }
}
