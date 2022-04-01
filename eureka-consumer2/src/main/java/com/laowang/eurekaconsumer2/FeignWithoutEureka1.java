package com.laowang.eurekaconsumer2;

import com.laowang.apieurekaprovider.Provider1Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**预知而写死的接口，跟restTemplate毫无区别
* 不通过Eureka解析地址
* */
@FeignClient(name="xxx",url = "http://eureka-provider1.com:7911/")
public interface FeignWithoutEureka1 extends Provider1Api {
}
