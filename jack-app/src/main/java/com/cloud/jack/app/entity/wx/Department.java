package com.cloud.jack.app.entity.wx;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("department")
public class Department {


    private Integer id;

    private String name;

    private String name_en;

    private Integer parentid;

}
