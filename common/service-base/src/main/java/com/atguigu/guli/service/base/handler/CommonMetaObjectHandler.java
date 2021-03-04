package com.atguigu.guli.service.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * description:
 * Created by Kris on 2021/3/4
 */
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("gmtCreate",new Date());
        metaObject.setValue("gmtModified", new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("gmtModified", new Date());
    }
}
