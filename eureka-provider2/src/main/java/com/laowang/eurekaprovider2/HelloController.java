package com.laowang.eurekaprovider2;

import com.laowang.apieurekaprovider.Person;
import com.laowang.apieurekaprovider.Provider2Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
* provider面向接口的实现，这属于传说中的【面向接口编程】
* */
@RestController("/p2/hello")
public class HelloController implements Provider2Api {

    @Autowired
    private HttpServletResponse response;

    @Value("${server.port}")
    String port;

    private AtomicInteger count = new AtomicInteger(1);

    @Override
    public String getHi(){
        return "provider2 says : Hi!";
    }

    @Override
    public String sayHi(){
        return "provider2 says: Hi ~~~";
    }

    @Override
    public Map<String,String> getMapByIdandName(@PathVariable("id") String id, @PathVariable String name){
        return Collections.singletonMap(id,name);
    }

    @Override
    public Person getObj(@RequestParam Map<String,String> paramMap){
        System.out.println(paramMap);
        return new Person(Integer.valueOf(paramMap.get("id")), paramMap.get("name"));
    }

    @Override
    public Person postMap(@RequestBody Map<String, Object> paramMap) {
        System.out.println(paramMap);
        return new Person(Integer.valueOf(paramMap.get("id").toString()), paramMap.get("name").toString());
    }

    @Override
    public URI postParam(@RequestBody Person person) {
        URI uri = null;
        try {
            uri = new URI("https://www.baidu.com/s?wd="+person.getName());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //需要设置头信息，否则返回的是null
        response.addHeader("Location",uri.toString());
        return uri;
    }

    @Override
    public String testTimeout() {
        System.out.println("准备睡");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port + "调用了" + count.getAndIncrement() + "次";
    }
}
