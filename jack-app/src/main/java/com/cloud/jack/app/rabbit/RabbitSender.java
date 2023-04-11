package com.cloud.jack.app.rabbit;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * rabbit mq用于发送消息
 * 
 * @author camel
 *
 */
@Component
public class RabbitSender {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public void convertAndSend(String exchangeName, String routingKey, String json, String batchNo) {
        String str[] = batchNo.split("-");
        RabbitMetaMessage rabbitMetaMessage = new RabbitMetaMessage();
        rabbitMetaMessage.setReturnCallback(false);
        rabbitMetaMessage.setPayload(json);
        redisTemplate.opsForHash().put(str[0] + ":" + str[1], batchNo, rabbitMetaMessage);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, json, (message) -> {
            message.getMessageProperties().setMessageId(batchNo);
            return message;
        }, new CorrelationData(batchNo));


    }
}
