package com.example;


import com.example.service.ContactServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {

        SpringApplication.run(MyApplication.class,args);
    }

//    @Bean
//    public ContactServiceImpl getContactService() {
//        return new ContactServiceImpl();
//    }
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

