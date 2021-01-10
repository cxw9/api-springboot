package com.api;

import com.api.service.impl.ImgServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplication {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ImgServiceImpl imgService;
    @Test
    public void redis(){
        //redisTemplate.opsForList().rightPush("test1","aa");
        Jedis jedis=new Jedis("localhost", 6379);
        //jedis.spop("img",1);
        //list->string
        String join = String.join(",", jedis.spop("img",1));
        System.out.println(join);
    }

}
