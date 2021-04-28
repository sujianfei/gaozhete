package com.feixiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GaozhiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaozhiteApplication.class, args);
    }

}
