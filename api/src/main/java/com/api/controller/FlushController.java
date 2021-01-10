package com.api.controller;

import com.api.service.impl.ImgServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
//将数据存到redis
/**
 * @author o
 */
@RestController
@CrossOrigin //跨域
@RequestMapping("/flush")
public class FlushController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ImgServiceImpl imgService;
    //mysql->redis
    @RequestMapping("/redis")
    @ResponseBody
    public String flushRedisController(){
        Jedis jedis=new Jedis("localhost", 6379);
        //redis中从 1开始 故int i=0 而不能i=0
        for (int i=1;i<imgService.count();i++){
            if(imgService.getById(i).getType().equals("acg")){
                jedis.sadd("acg",imgService.getById(i).getUrl());
            }
            if(imgService.getById(i).getType().equals("gril")){
                jedis.sadd("gril",imgService.getById(i).getUrl());
            }
            if(imgService.getById(i).getType().equals("fg")){
                jedis.sadd("fg",imgService.getById(i).getUrl());
            }
        }
        return "add db to redis success";
    }
    //清除redis缓存 不要在本地使用
    @RequestMapping("/flushdb")
    @ResponseBody
    public String flushDbRedisController(){
        Jedis jedis=new Jedis("localhost", 6379);
        //redis中从 1开始 故int i=0 而不能i=0
        jedis.flushDB();
        return "flushRedisDb success";
    }
}
