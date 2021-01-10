package com.api.controller;


import com.api.service.impl.ImgServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author o
 * @since 2020-12-23
 */
@RestController
@CrossOrigin //跨域
@RequestMapping("/img")
public class ImgController {
    Jedis jedis=new Jedis("localhost", 6379);
    @Autowired
    ImgServiceImpl imgService;
    @RequestMapping("/test")
    @ResponseBody
    public void test(HttpServletResponse response) throws IOException {
        //mysql中id 的数量
        int count=imgService.count()-1;
        //System.out.println(imgService.count());
        int random = (int)(Math.random()*count);
        //id不可能为空所以不用判断
        try{
            response.sendRedirect(imgService.getById(random).getUrl());
        }catch (Exception e){
            //防止图片连接出错
            //不要用下边的这种方法，最好是写另一个接口
            response.sendRedirect("https://tva1.sinaimg.cn/large/0072Vf1pgy1foxkf5ytavj31kw0w0ts3");
        }
        //System.out.println(imgService.getById(1).getUrl());
    }
    /**
     @RequestMapping("/acg")
     @ResponseBody
     public void acg(HttpServletResponse response) throws IOException {
     //mysql中id 的数量
     int count=imgService.count()-1;
     //System.out.println(imgService.count());
     int random = (int)(Math.random()*count);
     //id不可能为空所以不用判断
     try{
     response.sendRedirect(imgService.getById(random).getUrl());
     }catch (Exception e){
     //防止图片连接出错
     response.sendRedirect("https://tva1.sinaimg.cn/large/0072Vf1pgy1foxkf5ytavj31kw0w0ts3");
     }
     //System.out.println(imgService.getById(1).getUrl());
     }
     //从redis中读取
     @RequestMapping("/redis")
     @ResponseBody
     public void redis(HttpServletResponse response) throws IOException {
     //redisTemplate.opsForList().rightPush("test1","aa");
     //jedis.spop("img",1);
     //list->string
     String join = String.join(",", jedis.spop("img",1));
     response.sendRedirect(join);
     //System.out.println(join);
     }*/

    @RequestMapping("/gril")
    @ResponseBody
    public void gril(HttpServletResponse response) throws IOException {
        String join = String.join(",", jedis.spop("gril",1));
        response.sendRedirect(join);
    }
    @RequestMapping("/acg")
    @ResponseBody
    public void acg(HttpServletResponse response) throws IOException {
        String join = String.join(",", jedis.spop("acg",1));
        response.sendRedirect(join);
    }
    @RequestMapping("/fg")
    @ResponseBody
    public void fg(HttpServletResponse response) throws IOException {

        String join = String.join(",", jedis.spop("fg",1));
        response.sendRedirect(join);
    }


}

