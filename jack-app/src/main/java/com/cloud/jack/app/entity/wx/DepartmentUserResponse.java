package com.cloud.jack.app.entity.wx;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentUserResponse {


    private Integer errcode;

    private String errmsg;

    private String nextCursor;

    private List<DepartmentUser> dept_user;
}
