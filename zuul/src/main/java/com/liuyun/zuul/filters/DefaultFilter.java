package com.liuyun.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
public class DefaultFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(DefaultFilter.class);

    private final Object EMPTY = new Object();

    /**
     * 该方法返回一个字符串，代表过滤器的类型。
     * 在zuul中，定义了四种不同生命周期的过滤器类型，具体如下：
     *  pre  ------> 路由之前
     *  routing ----> 路由时
     *  post -------> 路由之后
     *  error ------> 发生错误的时候调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /***
     * 该方法返回一个整数，代表当前过滤器在过滤器链路中被调用的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /***
     * 该方法决定是否启用当前过滤器对请求进行过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑，可以很复杂。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestId = request.getHeader("requestId");
        if (requestId == null || requestId.isEmpty()){

            //ctx.setSendZuulResponse(false)表明不再对该请求进行路由，
            // 如果在当前类中直接或间接调用了ctx.getResponse().getWriter()
            //则必须设置为false, 否则后续会抛出异常 getWriter() has already been called for this response
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().println("Invalid request Id");
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.warn("[{}: {}] is invalid!", request.getMethod(), request.getRequestURL());
        }
        return null;
    }
}
