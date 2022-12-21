package com.cloud.jack.core;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class CurrentUser {

    private String id;

    private String account;

    private String username;

    private String password;

    private List<String> roles;

    private Boolean enabled;
}
