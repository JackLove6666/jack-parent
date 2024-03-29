package com.cloud.jack.app.rabbit.topic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * topic  通配符/主题模式-交换机和队列进行绑定，并且通配符方式routing key，当发送消息到交换机后，交换机会根据routing key将消息发送到对应的队列
 * 符合路由（routing key，通配符）的消费者收到
 * </p>
 *
 * @author MrWen
 **/
@Component
public class FullLogConsumer {

    /**
     * 消息监听方法
     * bindings: 完成队列与交换机的绑定
     * Queue: 队列属性
     * exchange：交换机属性
     * key：路由key,通配符
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "${topic.full.queue}", durable = "true"),// 队列
            exchange = @Exchange(name = "${topic.exchange}", type = ExchangeTypes.TOPIC),// 交换机
            key = {"#"}// 路由key,通配符
    ))
    public void handlerMessage(String msg, Channel channel, Message message) {
        System.out.println("log.full--->接受到的消息是：" + msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
