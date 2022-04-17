package com.example.eurekaconsumer1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestOriginalController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getEntity")
    public Object sayHi(){
        String url = "http://provider2/sayHi";
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        System.out.println(entity);
        return entity.toString();
    }

    @RequestMapping("/getMap")
    public Map<String,Integer> getMap(){
        return Collections.singletonMap("id",100);
    }

    @GetMapping("/getMap/{id}/{name}")
    public Map<String,String> getMapByIdandName(@PathVariable Integer id,@PathVariable String name){
//        String url = String.format("http://provider2/getMap/%d/%s",id,name);
//        Map forObject = restTemplate.getForObject(url, Map.class);
        //第二种方式，自动处理 uri：
        String url = "http://provider2/getMap/{1}/{2}";
        Map forObject = restTemplate.getForObject(url, Map.class, id, name);
        return forObject;
    }
    /*
    * http://192.168.15.92:7921/getMap?id=1&name=laowang url参数列表自动转为map键值对
    * */
    @GetMapping("/getMap")
    public Map<Integer, String> getMapByMap(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

    @GetMapping("/getObj/{id}/{name}")
    public Person getObj(@PathVariable Integer id,@PathVariable String name){
        String url = "http://provider2/getObj?id={id}&name={name}";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id",id.toString());paramMap.put("name",name);
        return restTemplate.getForObject(url, Person.class, paramMap);
    }

    //@GetMapping("/postObj/{id}/{name}") 在浏览器地址栏的请求都是GET请求，能看到响应内容
    @PostMapping("/postForObj/{id}/{name}")    //而POST方法不要在浏览器地址栏请求，要用postman或者apipost等工具能看到响应
    public Person postObj(@PathVariable Integer id,@PathVariable String name){
        String url = "http://provider2/postObj";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",id);paramMap.put("name",name);
        Person person = restTemplate.postForObject(url, paramMap, Person.class);
        System.out.println("远程调用provider2的postObj方法："+person);
        return person;
    }
    /*
    * 重定向到远程provider2给的URI上
    * */
    @GetMapping("/postForLocation")
    public void postLocation(HttpServletResponse response) throws IOException {
        String url = "http://provider2/postParam";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",123);paramMap.put("name","test");
        URI location = restTemplate.postForLocation(url, paramMap, Person.class);//难道map能自动组装person???
        System.out.println(location);
        response.sendRedirect(location.toString());
    }

}
