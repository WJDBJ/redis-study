package com.cache;

import com.cacheconf.RedisSpringCacheDefaultConfig;
import com.cacheconf.RedisSpringCacheJacksonConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author XJ
 */
public class Main {
    public static void main(String[] args) {
        //CacheHelloWorld();
        //CacheJacksonHelloWorld();
        CacheMultiHelloWorld();
    }

    public static void CacheHelloWorld(){
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(RedisSpringCacheDefaultConfig.class);
       UserInfoServiceImpl userInfoService = applicationContext.getBean(UserInfoServiceImpl.class);
       UserInfo userInfo = userInfoService.getById(1);
       System.out.println("userInfo = " + userInfo);
       UserInfo userInfo1 = userInfoService.getById(1);
        System.out.println("userInfo1 = " + userInfo1);
    }

    public static void CacheJacksonHelloWorld(){
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(RedisSpringCacheJacksonConfig.class);
        UserInfoServiceImpl userInfoService = applicationContext.getBean(UserInfoServiceImpl.class);
        UserInfo userInfo = userInfoService.getById(1);
        System.out.println("userInfo = " + userInfo);
        UserInfo userInfo1 = userInfoService.getById(1);
        System.out.println("userInfo1 = " + userInfo1);
    }

    public static void CacheMultiHelloWorld(){
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(RedisSpringCacheMultiConfig.class);
        UserInfoServiceImpl userInfoService = applicationContext.getBean(UserInfoServiceImpl.class);
        UserInfo userInfo = userInfoService.getById(1);
        System.out.println("userInfo = " + userInfo);
        UserInfo userInfo1 = userInfoService.getById(1);
        System.out.println("userInfo1 = " + userInfo1);
    }
}
