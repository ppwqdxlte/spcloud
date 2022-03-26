package com.example.eurekaconsumer1;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController("/c1/hello")
public class HelloController {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    EurekaClient eurekaClient;
    //调用远程接口
    RestTemplate restTemplate = new RestTemplate();
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
    */
    @RequestMapping("/testRestTemplate")
    public String testRestTemplate(){
        List<InstanceInfo> provider2 = eurekaClient.getInstancesByVipAddress("provider2", false);
        for (InstanceInfo instanceInfo : provider2) {
            String url = "http://"+instanceInfo.getHostName()+":"+instanceInfo.getPort()
                    +"/sayHi";
            System.out.println(restTemplate.getForObject(url, String.class));
        }
        return "test RestTemplate";
    }

    @RequestMapping("/testLoadBalencerClient")
    public String testLoadBalencerClient(){
        //负载均衡,挑出一个合适的副本
        ServiceInstance provider1 = loadBalancerClient.choose("provider1");
        System.out.println(provider1.getHost());
        return "test LoadBalencerClient";
    }
}
