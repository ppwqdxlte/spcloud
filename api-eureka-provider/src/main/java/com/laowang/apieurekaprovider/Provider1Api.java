package com.laowang.apieurekaprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 模拟从eureka下载provider的接口文件到本地
 */
public interface Provider1Api {
    /**
     * @return hi~~~
     */
    @GetMapping("/getHi")
    public String getHi();
    /**
     * @return health info
     */
    @GetMapping("/health")
    public String health(@RequestParam Boolean status);
    /**
     * @return port info
     */
    @GetMapping("/loadBalenced")
    public String loadBalenced();
}
