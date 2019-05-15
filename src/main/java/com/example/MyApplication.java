package com.example;


import com.example.service.ContactServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan
@EnableCaching(proxyTargetClass=true)//enables Spring Caching functionality
@SpringBootApplication
public class MyApplication /*extends SpringBootServletInitializer*/ {
    public static void main(String[] args) {

        SpringApplication.run(MyApplication.class,args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

