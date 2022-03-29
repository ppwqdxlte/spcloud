package com.laowang.eurekaprovider2;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

@RestController("/p2/hello")
public class HelloController {

    @RequestMapping("/getHi")
    public String getHi(){
        return "provider2 says : Hi!";
    }

    @RequestMapping("/sayHi")
    public String sayHi(){
        return "provider2 says: Hi ~~~";
    }

    @GetMapping("/getMap/{id}/{name}")
    public Map<String,String> getMapByIdandName(@PathVariable("id") String id, @PathVariable String name){
        return Collections.singletonMap(id,name);
    }

    @GetMapping("/getObj")
    public Person getObj(@RequestParam Map<String,String> paramMap){
        System.out.println(paramMap);
        return new Person(Integer.valueOf(paramMap.get("id")), paramMap.get("name"));
    }

    @PostMapping("/postObj")
    public Person postMap(@RequestBody Map<String, Object> paramMap) {
        System.out.println(paramMap);
        return new Person(Integer.valueOf(paramMap.get("id").toString()), paramMap.get("name").toString());
    }

    @PostMapping("/postParam")
    public URI postParam(@RequestBody Person person, HttpServletResponse response) throws URISyntaxException {
        URI uri = new URI("https://www.baidu.com/s?wd="+person.getName());
        //需要设置头信息，否则返回的是null
        response.addHeader("Location",uri.toString());
        return uri;
    }
}
