package com.laowang.eurekaconsumer2;

import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

@Component
public class Provider1ApiFallBackFactory implements FallbackFactory<FeignWithEureka2> {
    @Override
    public FeignWithEureka2 create(Throwable cause) {
        return new FeignWithEureka2() {

            private Throwable localCause = cause;

            @Override
            public String getHi() {
                return getCause();
            }

            @Override
            public String health(Boolean status) {
                return getCause();
            }

            @Override
            public String loadBalenced() {
                return getCause();
            }

            private String getCause(){
                System.out.println(localCause);
                if (cause instanceof ArithmeticException){
                    return "算数异常了";
                }else if (cause instanceof FeignException.ServiceUnavailable){
                    return "Feign$ServiceUnavailable 503" + cause.getLocalizedMessage();
                }else if(cause instanceof FeignException.InternalServerError) {
                    return "Feign远程服务器 500" + cause.getLocalizedMessage();
                }else if (cause instanceof HttpServerErrorException.InternalServerError){
                    return "SpringCloud远程服务器500" + cause.getLocalizedMessage();
                }
                else {
                    return "呵呵";
                }
            }
        };
    }
}
