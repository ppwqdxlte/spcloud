package com.laowang.apieurekaprovider;

import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

public interface Provider2Api {

    @RequestMapping("/getHi")
    public String getHi();

    @RequestMapping("/sayHi")
    public String sayHi();

    @GetMapping("/getMap/{id}/{name}")
    public Map<String,String> getMapByIdandName(@PathVariable("id") String id, @PathVariable String name);

    @GetMapping("/getObj")
    public Person getObj(@RequestParam Map<String,String> paramMap);

    @PostMapping("/postObj")
    public Person postMap(@RequestBody Map<String, Object> paramMap);

    @PostMapping("/postParam")
    public URI postParam(@RequestBody Person person);

    @PostMapping("/testTimeout")
    public String testTimeout();
}
