package com.cloud.jack.app.distributed.redis;


import com.alibaba.fastjson.JSON;
import com.cloud.jack.app.test.distributed.redis.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetTop10CoursesBySalesTest {


        @Autowired
        private RedisTemplate redisTemplate;
//    private RedisTemplate<String, Object> getRedisTemplate() {
//        // 这里可以根据具体情况，选择合适的 RedisTemplate 实现类
//        return new RedisTemplate();
//    }


    public List<Course> getTop10CoursesBySales(){
        // 获取 RedisTemplate 实例
//        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();

        // 定义 Redis 中存储销量数据的 key 的前缀
        String salesKeyPrefix = "course_sales";

        // 获取所有课程的 ID
        //RedisZSetCommands.Range.unbounded() 表示不设定范围，即获取所有元素。
        Set<Object> courseIds = redisTemplate.opsForZSet().rangeByLex(salesKeyPrefix, RedisZSetCommands.Range.unbounded());

        // 定义销量数据的 Map
        // 键为课程的 ID，值为课程的销量
        Map<Object, Double> salesMap = new HashMap<>();

        // 遍历课程 ID，获取对应的销量数据
        for (Object courseId : courseIds) {
            Double sales = redisTemplate.opsForZSet().score(salesKeyPrefix, courseId);
            if (sales != null) {
                salesMap.put(courseId, sales);
            }
        }

        // 对销量数据进行降序排序
        List<Map.Entry<Object, Double>> salesList = new ArrayList<>(salesMap.entrySet());
        salesList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // 取销量最高的前 10 个课程
        List<Course> top10Courses = new ArrayList<>(10);
        for (int i = 0; i < Math.min(salesList.size(), 10); i++) {
            Object courseId = salesList.get(i).getKey();
            Object o = redisTemplate.opsForValue().get("course:" + courseId);
            Course course = JSON.parseObject((String) o, Course.class);
            if (course != null) {
                top10Courses.add(course);
            }
        }

        return top10Courses;
    }


    public List<Course> getTop10CoursesBySalesStr() {
        // 获取 RedisTemplate 实例
//        RedisTemplate<String, Object> redisTemplate = getRedisTemplate();

        // 定义课程销量的 key 前缀
        String salesKeyPrefix = "course_sales";

        // 构造所有课程销量的 key 列表
        List<String> salesKeys = new ArrayList<>();
        Set<String> courseIds = redisTemplate.keys(salesKeyPrefix + "*");
        for (Object courseId : courseIds) {
            salesKeys.add((String) courseId);
        }

        // 根据销量排序，获取销量排行前 10 的课程
        Collections.sort(salesKeys, (a, b) -> {
            Double salesA = Double.valueOf((String) redisTemplate.opsForValue().get(a));
            Double salesB = Double.valueOf((String) redisTemplate.opsForValue().get(b));
            return Double.compare(salesB, salesA);
        });

        List<Course> top10Courses = new ArrayList<>(10);
        for (int i = 0; i < salesKeys.size() && i < 10; i++) {
            String courseId = salesKeys.get(i).replace(salesKeyPrefix, "");
            String courseKey = "course:" + courseId;
            Course course = (Course) redisTemplate.opsForValue().get(courseKey);
            if (course != null) {
                top10Courses.add(course);
            }
        }

        return top10Courses;
    }


    @Test
    public void test(){
        List<Course> top10CoursesBySales = getTop10CoursesBySales();
        List<Course> top10CoursesBySalesStr = getTop10CoursesBySalesStr();
        System.out.println(top10CoursesBySales);
        System.out.println(top10CoursesBySalesStr);
//        Object o = redisTemplate.opsForValue().get("course:1");
//        System.out.println(o);
//        redisTemplate.keys("course_sales*").forEach(System.out::println);
//        redisTemplate.opsForValue().set("jkgtest1", "100");
    }
}
