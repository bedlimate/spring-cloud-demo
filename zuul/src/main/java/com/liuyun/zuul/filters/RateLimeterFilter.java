package com.liuyun.zuul.filters;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 服务内限流（或者说单应用限流），基于Guava提供的令牌桶算法实现
 */
public class RateLimeterFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return  true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();

        return null;
    }

    interface AbstractHandler{
        void handle(RequestContext ctx) throws IOException;
    }

    /***
     * 平滑突发限流
     */
    private static class SmoothBurstyHandler implements AbstractHandler{
        //表示痛的容量为200，且每秒增加200个令牌，即每5ms新增一个令牌
        final RateLimiter limiter = RateLimiter.create(200);

        @Override
        public void handle(RequestContext ctx) throws IOException {
            //获取令牌成功，这里暂时只允许一个一个获取令牌，未考虑批量获取令牌，
            //实际对于limiter而言，支持批量获取令牌，并且支持获取未来的令牌，但是如果获取了未来的令牌，那么在未来的一段时间内，将无法获取令牌
            boolean success = limiter.tryAcquire();
            if(!success){
                ctx.setSendZuulResponse(false);
                ctx.getResponse().getWriter().write("Server is busy, please wait for a moment and try it again!");
            }
        }
    }

    private static class SmoothWarmingUpHandler implements AbstractHandler{

        final RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);

        @Override
        public void handle(RequestContext ctx) throws IOException {

        }
    }
}
