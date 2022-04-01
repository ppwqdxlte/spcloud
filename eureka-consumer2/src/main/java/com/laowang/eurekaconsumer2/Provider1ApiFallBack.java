package com.laowang.eurekaconsumer2;

import org.springframework.stereotype.Component;

/**
 * 熔断降级的友好plan B
 * */
@Component
public class Provider1ApiFallBack implements FeignWithEureka2 {

    @Override
    public String getHi(){
        return "熔断了";
    }

    @Override
    public String health(Boolean status) {
        return "降级了";
    }

    @Override
    public String loadBalenced(){
        return "熔断降级了";
    }
}
