package com.cloud.jack.core.abnormal;


import com.cloud.jack.core.R;
import lombok.Data;

/**
 * 业务异常
 *
 * @author 马鹿
 */
@Data
public class ServiceException extends RuntimeException {

    private Integer code;

    private String msg;

    public ServiceException(String msg) {
        R.fail(msg);
    }

    public ServiceException(Integer code, String msg) {
        R.fail(code, msg);
    }
}
