package com.cloud.jack.app.entity.wx;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("user")
public class User {

    private Integer id;

    private String userid;

    private String name;

    private String alias;

    private String position;

    private String mobile;

    private String email;

    private Integer main_department;

    @TableField(exist = false)
    private List<Integer> is_leader_in_dept;

    @TableField(exist = false)
    private Boolean isleader;

    @TableField(exist = false)
    private List<String> department;

}
