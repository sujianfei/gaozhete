package com.feixiang.gaozhite;

import com.feixiang.annotation.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    @GetMapping(value = "hello")
    @RateLimit(2)
    public String hello(){
        return new Date().toString();
    }

}
