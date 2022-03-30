package com.laowang.eurekaconsumer2;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "provider1")
public interface FeignWithEureka2 extends Provider1Api{
}
