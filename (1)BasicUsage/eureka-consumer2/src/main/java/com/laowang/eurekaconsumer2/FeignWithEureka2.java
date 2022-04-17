package com.laowang.eurekaconsumer2;

import com.laowang.apieurekaprovider.Provider1Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
* Feign整合Hystrix【隔离限流 熔断降级】fallback熔断器
* */
@Component
//@FeignClient(name = "provider1",fallback = Provider1ApiFallBack.class)
@FeignClient(name = "provider1",fallbackFactory = Provider1ApiFallBackFactory.class)
public interface FeignWithEureka2 extends Provider1Api {
}
