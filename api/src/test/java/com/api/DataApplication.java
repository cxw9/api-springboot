package com.api;

import com.api.entity.Img;
import com.api.service.impl.ImgServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataApplication {
    @Autowired
    ImgServiceImpl imgService;
    @Test
    public void test1(){
        //id总数
        System.out.println(imgService.count());
        //img_type="gril"的总数
        QueryWrapper<Img> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("img_type","gril");
        System.out.println(imgService.count(queryWrapper));
        //img_type="acg"的总数
        QueryWrapper<Img> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("img_type","acg");
        System.out.println(imgService.count(queryWrapper2));
        //用id得到对应链接
        System.out.println(imgService.getById(1).getUrl());
        //用id得到对应除链接之外的 格式为
        /**Img(id=1, name=1.jpeg, imgPath=null, md5=null, md5Context=f69f250c6cbe7a4a9681b5c33afb2a82,
         * statusCode=200, url=https://tva4.sinaimg.cn/large/0072Vf1pgy1foxkjkmkn8j31kw0w0k5q,
         * urlMd5=537beb1bd1cda80f21321748e388f794, getTime=Tue Dec 22 12:18:08 2020,
         * updTime=null, imgType=acg, contentType=image/jpeg, contentLength=399)
         * */
        System.out.println(imgService.getById(1));

    }


    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("test","111111");
        //redisTemplate.delete("turnover:2020-12-29:00");
        System.out.println("----------------");
        redisTemplate.opsForValue().get("test");
        for (int i=0;i<100;i++){
            redisTemplate.opsForList().rightPush("mytest",imgService.getById(i).getUrl());
        }
        System.out.println(redisTemplate.opsForValue().get("test"));
        for (int i=0;i<10;i++){
            System.out.println(redisTemplate.opsForList().index("mytest",i));
        }
    }


    /**
     * 自定义sql查询语句

    @Test
    public void selectByMySelect() {
        List<ImgMapper> imgList = ImgMapper.selectByType("acg");
        imgList.forEach(System.out::println);
    }
     */

}
