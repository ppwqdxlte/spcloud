package com.laowang.eurekaprovider1;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/p1/hello")
public class HelloController {

    @RequestMapping("/getHi")
    public String getHi(){
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
    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status){
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
    }

    @Autowired
    EurekaClient client;

    @GetMapping("/loadBalenced")
    public String testLoadBalenced(){
        return String.valueOf(client.getApplicationInfoManager().getInfo().getPort());
    }
}
