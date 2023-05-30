package com.cloud.jack.app.test.distributed.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    // 课程id
    private Integer id;
    // 课程名称
    private String name;

    private double price;
}
