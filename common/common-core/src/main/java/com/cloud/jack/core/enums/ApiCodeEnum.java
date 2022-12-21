package com.cloud.jack.core.enums;

/**
 * API常量
 *
 * @author 马鹿
 */
public class ApiCodeEnum {

    /**
     * 成功
     **/
    public static final Integer DEMO = 1000;
    public static final String DEMO_MSG = "演示模式，不允许操作！";

    /**
     * 成功
     **/
    public static final Integer OK = 2000;
    public static final String OK_MSG = "成功";

    /**
     * 失败
     **/
    public static final Integer ERROR = 2001;
    public static final String ERROR_MSG = "失败";
    /**
     * 用户名或密码错误
     **/
    public static final Integer LOGIN_ERROR = 2002;
    public static final String LOGIN_ERROR_MSG = "用户名或密码错误";

    /**
     * 权限不足
     **/
    public static final Integer ACCESS_ERROR = 2003;
    public static final String ACCESS_ERROR_MSG = "权限不足";

    /**
     * 远程调用失败
     **/
    public static final Integer REMOTE_ERROR = 2004;
    public static final String REMOTE_ERROR_MSG = "远程调用失败";

    /**
     * 重复操作
     **/
    public static final Integer REPEAT_ERROR = 2005;
    public static final String REPEAT_ERROR_MSG = "重复操作";

    /**
     * 旧密码错误
     **/
    public static final Integer MODIFY_ERROR = 2006;
    public static final String MODIFY_ERROR_MSG = "旧密码错误";

}
