package com;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XJ
 */
@Service
public class UserInfoServiceImpl {
    private static Map<Integer,UserInfo> map;

    static {
        map = new HashMap<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setName("xj");
        map.put(1,userInfo);

        UserInfo userInfo1 = new UserInfo();
        userInfo.setId(2);
        userInfo.setName("lzb");
        map.put(2,userInfo1);
    }

    @Cacheable(value = "cache1", key = "#id")
    public UserInfo getById(Integer id){
        System.out.println("----- cache1 getById ------");
        UserInfo userInfo = map.get(id);
        return userInfo;
    }

    @CacheEvict(value = "cache1", key = "#id")
    public void deleteById(Integer id){
        System.out.println("------ cache1 deleteById ------");
        map.remove(id);
    }
}
