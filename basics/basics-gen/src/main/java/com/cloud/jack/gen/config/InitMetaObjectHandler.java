package com.cloud.jack.gen.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cloud.jack.core.CurrentUser;
import com.cloud.jack.core.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 初始化字段
 *
 * @author ML
 */
@Slf4j
@Component
public class InitMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        CurrentUser currentUser = UserUtils.getCurrentUser();
        if (currentUser != null) {
            metaObject.setValue("createBy", currentUser.getId());
            metaObject.setValue("updateBy", currentUser.getId());
        }
        metaObject.setValue("createTime", new Date());
        metaObject.setValue("updateTime", new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        CurrentUser currentUser = UserUtils.getCurrentUser();
        if (currentUser != null) {
            metaObject.setValue("updateBy", currentUser.getId());
        }
        metaObject.setValue("updateTime", new Date());
    }
}
