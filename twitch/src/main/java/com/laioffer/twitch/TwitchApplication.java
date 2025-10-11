package com.laioffer.twitch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class TwitchApplication {


    public static void main(String[] args) {
        SpringApplication.run(TwitchApplication.class, args);
        // 就是因为这行代码，所以Spring boot可以run
    }


}

