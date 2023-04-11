package com.cloud.jack.app.entity.wx;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentResponse {

    private Integer errcode;

    private String errmsg;

    private List<Department> department;
}
