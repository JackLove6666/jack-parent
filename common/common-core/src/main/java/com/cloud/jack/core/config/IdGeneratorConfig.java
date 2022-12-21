package com.cloud.jack.core.config;


import com.cloud.jack.core.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ID配置(雪花算法)
 *
 * @author Leo
 */
@Configuration
public class IdGeneratorConfig {

    @Value("${id.worker-id:1}")
    private long workerId;

    @Value("${id.data-center-id:1}")
    private long dataCenterId;

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator(workerId, dataCenterId);
    }

}
