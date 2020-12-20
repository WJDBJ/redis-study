package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author XJ
 */
@SpringBootApplication
public class RedisSpringBootApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RedisSpringBootApp.class,args);
    }

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Override
    public void run(String... args) throws Exception {
        UserInfo userInfo = userInfoService.getById(1);
        System.out.println("userInfo = " + userInfo);
        UserInfo userInfo1 = userInfoService.getById(1);
        System.out.println("userInfo1 = " + userInfo1);
    }
}
