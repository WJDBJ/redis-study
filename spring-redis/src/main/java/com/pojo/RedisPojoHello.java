package com.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author XJ
 */
@Component
public class RedisPojoHello {
    @Autowired
    private RedisTemplate redisTemplate;

    public void hello() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Login login = new Login();
        login.setUserName("xj");
        login.setPassWord("1234");
        valueOperations.set("test2",login);
        Login login1 = (Login) valueOperations.get("test2");
        System.out.println("login1 = " + login1);
    }
}
