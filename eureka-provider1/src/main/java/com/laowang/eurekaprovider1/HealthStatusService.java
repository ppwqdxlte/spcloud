package com.laowang.eurekaprovider1;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/*
* eureka-client在不配置health-check=true时，哪怕程序报错了，如果依然
* 给server发 心跳status=up时，eureka服务器傻傻地以为该client状态良好，
* 实际上都挂了，所以需要主动上报真实健康状况
* */
@Service
public class HealthStatusService implements HealthIndicator {

    private Boolean status = true;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStatus() {
        return status.toString();
    }

    @Override
    public Health health() {
        if (status){
            return new Health.Builder().up().build();
        }else {
            return new Health.Builder().down().build();
        }
    }
}
