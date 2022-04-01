package com.laowang.eurekaconsumer2;

import com.laowang.apieurekaprovider.Provider1Api;
import org.springframework.cloud.openfeign.FeignClient;

/**预知而写死的接口，跟restTemplate毫无区别
* 通过Eureka解析地址
* */
@FeignClient(name = "provider1")
public interface FeignWithEureka1 extends Provider1Api {
}
