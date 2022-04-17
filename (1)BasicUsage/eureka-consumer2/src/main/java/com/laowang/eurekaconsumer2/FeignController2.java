package com.laowang.eurekaconsumer2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * feign调用api方式2：调用继承了provider api接口的方法，比方法1好处在于不用本地写死Mappings，只需实现即可
 * 反正现在只是java client，没有其它语言客户端，所以添加api依赖是最方便的，等有其它语言的client再写原生api写死mappings
 */
@RestController
public class FeignController2 {

    @Autowired
    FeignWithEureka2 feignWithEureka2;

    @GetMapping("/3")
    public String getHi(){
        return feignWithEureka2.getHi();
    }

    @GetMapping("/4")
    public String health(Boolean status){
        return feignWithEureka2.health(status);
    }

    @GetMapping("/5")
    public String loadBalenced(){
        return feignWithEureka2.loadBalenced();
    }

}
