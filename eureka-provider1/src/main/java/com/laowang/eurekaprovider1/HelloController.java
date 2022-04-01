package com.laowang.eurekaprovider1;

import com.laowang.apieurekaprovider.Provider1Api;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/p1/hello")
public class HelloController implements Provider1Api {

    @Override
    public String getHi(){
//        int i = 1/0; //测试 provider方有问题，consumer那边是否走 fallback
        return "provider1 says : Hi!";
    }

    @Autowired
    HealthStatusService healthStatusService;

    /*
    * http://eureka-provider1-1.com:7911/health?status=true
    * http://eureka-provider1-1.com:7911/health?status=false
    * false时候，eureka-server knows it!and show in the index page!
    * when true,you maybe weit for lessthan 30s to flash server
    * index page seeing it up.
    * */
    @Override
    public String health(@RequestParam("status") Boolean status){
        /*
        // hystrix异常测试
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
    }

    @Autowired
    EurekaClient client;

    @Override
    public String loadBalenced() {
        /*
        // hystrix异常测试
        String a = null;
        System.out.println(a.toString());*/
        return String.valueOf(client.getApplicationInfoManager().getInfo().getPort());
    }
}
