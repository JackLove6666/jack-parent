package com.cloud.jack.core.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Semaphore;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiLimitVo {
    private String confKey;
    private String value;
    private Integer maxThreadCount;
    private Integer maxWaitTime;
    private Semaphore semaphore;
}
