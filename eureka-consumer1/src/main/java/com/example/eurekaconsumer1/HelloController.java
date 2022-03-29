package com.example.eurekaconsumer1;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController("/c1/hello")
public class HelloController {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    EurekaClient eurekaClient;
    @Autowired//调用远程接口
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    /*
    * DiscoveryClient对象，并不能查询其它服务提供者的接口信息。
    * */
    @RequestMapping("/discovery")
    public String discovery(){
        System.out.println(discoveryClient.getServices().toString());
        System.out.println(discoveryClient.description());
        System.out.println(discoveryClient.getInstances("provider1"));
        System.out.println(discoveryClient.getOrder());

        List<ServiceInstance> provider1 = discoveryClient.getInstances("provider1");
        for (ServiceInstance item : provider1) {
            EurekaServiceInstance serviceInstance = (EurekaServiceInstance)item;
            System.out.println("----------");
            System.out.println(serviceInstance.getServiceId());
            System.out.println(serviceInstance.getInstanceId());
            System.out.println(serviceInstance.getHost());
            System.out.println(serviceInstance.getMetadata());
            System.out.println(serviceInstance.getScheme());
            System.out.println(serviceInstance.getUri());
            System.out.println(serviceInstance.isSecure());
            System.out.println(serviceInstance.getInstanceInfo());
        }

        return "test Spring cloud's DiscoveryClient";
    }
    /*
    * EurekaClient也无法获取其它provider的接口信息
    * */
    @RequestMapping("/testEurekaClient")
    public String testEurekaClient(){
        List<InstanceInfo> provider2 = eurekaClient.getInstancesById("DESKTOP-4MRD752:provider2:7912");
        for (InstanceInfo instanceInfo : provider2) {
            System.out.println(instanceInfo);
        }
        return "test EurekaClient";
    }
    /*
    * 手动填入uri,用RestTemplate对象，调用远程api
    * 这里重新new了一个没有实现负载均衡的RestTemplate对象，才能使用 host:port/api 的方式而不报错
    */
    @RequestMapping("/testRestTemplate")
    public String testRestTemplate(){
        List<InstanceInfo> provider2 = eurekaClient.getInstancesByVipAddress("provider2", false);
        RestTemplate restTemplate = new RestTemplate();
        for (InstanceInfo instanceInfo : provider2) {
            String url = "http://"+instanceInfo.getHostName()+":"+instanceInfo.getPort()
                    +"/sayHi";
            System.out.println(restTemplate.getForObject(url, String.class));
        }
        return "test RestTemplate";
    }
    /*
    * 基于客户端的负载均衡，说白了从eureka server拿取服务信息，从client里的负载均衡算法来挑选出合适的provider
    * */
    @RequestMapping("/testLoadBalencerClient")
    public String testLoadBalencerClient(){
        //负载均衡,挑出一个合适的副本
        ServiceInstance provider1 = loadBalancerClient.choose("provider1");
        System.out.println(provider1.getPort());
        return "test LoadBalencerClient";
    }
    /*
    * 注释了@LoadBalenced的RestTemplate@Bean对象，实现负载均衡，此时url带服务名（spring.application.name）而不带port
    * */
    @RequestMapping("/testLoadBalencedRestTemplate")
    public String testLoadBalencedRestTemplate(){
        String url = "http://provider1/loadBalenced";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(forEntity);
        String mapUrl = "http://consumer1/getMap";
        Map<String,Integer> forObject1 = restTemplate.getForObject(mapUrl, Map.class);
        System.out.println(forObject1);
        return "test Load-Balenced RestTemplate";
    }
    /*
    * JRebel哇塞，好方便，修改Java代码不用重启程序，但是修改配置文件的话还是要重启
    * */
    @RequestMapping("/jRebel")
    public String testJRebel(){
        return "consumer1 启用JRebel后，写的这个方法，我看是否可以热部署，真好用啊！" +
                "听以前的老师说JRebel不能用于一套源码启动多套副本，不知道现在可不可以了！";
    }
}
