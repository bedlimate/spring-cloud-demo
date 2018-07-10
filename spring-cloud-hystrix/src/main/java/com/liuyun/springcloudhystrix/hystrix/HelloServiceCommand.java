package com.liuyun.springcloudhystrix.hystrix;

import com.liuyun.springcloudhystrix.service.IHelloService;
import com.netflix.hystrix.*;

import java.util.Random;

/***
 * 非线程安全对象，无法重用，
 */
public class HelloServiceCommand extends HystrixCommand<String> {

    private final IHelloService helloService;
    private final String param;
    private final Random random = new Random();

    public HelloServiceCommand(IHelloService helloService, String param){
        super(setter());
        this.helloService = helloService;
        this.param = param;
    }

    //当服务熔断的时候，会触发这个方法的调用
    @Override
    protected String getFallback() {
        return "This is an error!";
    }

    @Override
    protected String run() throws Exception {
        //模拟1/3的几率服务不可用
        int i = random.nextInt(3);
        if( i < 1){
            throw new RuntimeException("request failed");
        }
        return helloService.sayHello(param);
    }

    private static Setter setter(){
        /**
         * 设置这类key主要是为例控制隔离粒度。如：
         * 1. 服务分组加线程池： 粗粒度隔离，一个服务分组/系统配置一个隔离线程池即可。
         * 2. 服务分组+服务+线程池： 洗粒度隔离，一个服务分组配置一个隔离线程池，为不同的命令实现配置不同的线程池名称即可
         * 3. 混合实现： 一个服务分组配置一个隔离线程池，然后对重要的服务单独设置隔离线程池
         *
         */

        //配置全局唯一服务分组名称。在监控的时候，相同的分组会聚合在一起（必填选项）
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("hello");
        //配置全局唯一服务分组名称（可配置，也可不配置，如果不做配置，则默认是简单类名称）
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("sayHello");
        //配置全局唯一线程池名称（非必填选项， 如果不做配置则默认为分组名称）
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("hello-pool");
        //设置线程池的参数
        HystrixThreadPoolProperties.Setter threadPoolProperties =
                HystrixThreadPoolProperties.Setter()
                        //核心线程数
                .withCoreSize(10)
                        //最大存活时间
                .withKeepAliveTimeMinutes(10)
                        //线程池队列大小
                .withMaxQueueSize(10000)
                        //限定当前队列大小
                .withQueueSizeRejectionThreshold(1000);
        //配置该命令的一些参数
        HystrixCommandProperties.Setter commandProperties =
                HystrixCommandProperties.Setter()
                        //此处配置合理策略参数，此处使用的是线程池隔离，默认情况下是使用线程池隔离
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);
        return HystrixCommand.Setter
                .withGroupKey(groupKey)
                .andCommandKey(commandKey)
                .andThreadPoolKey(threadPoolKey)
                .andThreadPoolPropertiesDefaults(threadPoolProperties)
                .andCommandPropertiesDefaults(commandProperties);
    }
}
