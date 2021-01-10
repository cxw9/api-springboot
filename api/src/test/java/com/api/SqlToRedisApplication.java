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
public class SqlToRedisApplication {
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
        //String join = String.join(",", jedis.spop("img",1));
        //System.out.println(join);
        //int num=imgService.count();
        //redis中从 1开始 故int i=0 而不能i=0
        for (int i=1;i<imgService.count();i++){
            //String ss=imgService.getById(i).getType();
            //System.out.println(ss);
            //String ss=imgService.getById(i).getType();
            //String s2= imgService.getById(i).getUrl();
            //System.out.println(imgService.getById(i).getType());
            //System.out.println(ss.equals("acg"));
            if(imgService.getById(i).getType().equals("acg")){
                jedis.sadd("acg",imgService.getById(i).getUrl());
                //System.out.println("成功");
            }
            if(imgService.getById(i).getType().equals("gril")){
                jedis.sadd("gril",imgService.getById(i).getUrl());
            }
            if(imgService.getById(i).getType().equals("fg")){
                jedis.sadd("fg",imgService.getById(i).getUrl());
            }
        }
    }

}
