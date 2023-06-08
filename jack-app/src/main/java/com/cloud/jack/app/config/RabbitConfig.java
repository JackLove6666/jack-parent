package com.cloud.jack.app.config;


import com.cloud.jack.app.rabbit.RabbitMetaMessage;
import com.cloud.jack.app.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitConfig  {
    /**
     * 主机host
     */
    @Value("${spring.rabbitmq.host}")
    private String host;

    /**
     * 端口
     */
    @Value("${spring.rabbitmq.port}")
    private String port;

    /**
     * 用户名
     */
    @Value("${spring.rabbitmq.username}")
    private String username;

    /**
     * 密码
     */
    @Value("${spring.rabbitmq.password}")
    private String password;

    @Autowired
    private RedisTemplate redisTemplate;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, Integer.parseInt(port));
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        // 设置 生产者 confirms
        connectionFactory.setPublisherConfirms(true);
        // 设置 生产者 Returns
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        return rabbitTemplate;
    }
}
