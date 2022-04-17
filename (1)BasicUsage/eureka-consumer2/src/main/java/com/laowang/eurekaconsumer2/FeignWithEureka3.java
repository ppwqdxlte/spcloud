package com.laowang.eurekaconsumer2;

import com.laowang.apieurekaprovider.Provider2Api;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "provider2")
public interface FeignWithEureka3 extends Provider2Api {
}
