package com.cloud.jack.app.distributed.redis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Tuple;

import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VoteDemo {


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 将投票结果存入 Redis
     * @param candidate
     */
    public void saveVoteResult(String candidate){
        redisTemplate.opsForZSet().incrementScore("vote",candidate,1);
    }

    /**
     * 查询得票排名
     * @param topN
     */
    public void queryVoteResult(int topN){
        Set vote = redisTemplate.opsForZSet().range("vote", 0, topN - 1);
        System.out.println("Top " + topN + " Candidates:");
        int rank = 1;
        for (Object o : vote) {
            System.out.println("No." + rank++ + " " + ", Votes: " + o);
        }
    }

    /**
     * 清空投票结果
     */
    public void clearVoteResult(){
        redisTemplate.opsForZSet().remove("vote");
    }
    @Test
    public void  voteDemoTest(){
        // 初始化候选人信息
        redisTemplate.opsForZSet().add("vote", "A", 0);
        redisTemplate.opsForZSet().add("vote", "B", 0);
        redisTemplate.opsForZSet().add("vote", "C", 0);

        // 进行投票
        saveVoteResult( "A");
        saveVoteResult( "A");
        saveVoteResult( "B");
        saveVoteResult( "C");
        saveVoteResult( "B");
        saveVoteResult("C");
        saveVoteResult("C");

        // 查询排名
        queryVoteResult( 2);

        // 清空投票结果
        clearVoteResult();


    }
}
