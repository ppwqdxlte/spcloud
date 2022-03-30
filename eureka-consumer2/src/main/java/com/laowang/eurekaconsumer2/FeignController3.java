package com.laowang.eurekaconsumer2;

import com.laowang.apieurekaprovider.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * feign调用api方式2.5：对方法2的进一步完善，单独做个接口项目，全是provider的api，maven install一下，
 * 安装在本地maven仓库里，并没有部署在远程仓库，部署远程的命令是deploy
 * 在client pom.xml中引入该api的依赖
 */
@RestController
public class FeignController3 {

    @Autowired
    FeignWithEureka3 feignWithEureka3;

    @GetMapping("/6")
    public String getHi(){
        return feignWithEureka3.getHi();
    }

    @GetMapping("/7")
    public String sayHi(){
        return feignWithEureka3.sayHi();
    }

    @GetMapping("/8/{id}/{name}")
    public Map<String, String> getHi(@PathVariable String id,@PathVariable String name){
       return feignWithEureka3.getMapByIdandName(id, name);
    }

    @GetMapping("/9")
    public Person getObj(@RequestParam Map<String,String> paramMap){
       return feignWithEureka3.getObj(paramMap);
    }
    @GetMapping("/10")
    public Person postMap(@RequestParam Map<String,Object> paramMap){
        return feignWithEureka3.postMap(paramMap);
    }
    @GetMapping("/11")
    public void postParam(@RequestParam Integer id,@RequestParam String name, HttpServletResponse response) throws IOException {
        Person person = new Person(id,name);
        System.out.println(person);
        URI location = feignWithEureka3.postParam(person);
        response.sendRedirect(location.toString());
    }
}
