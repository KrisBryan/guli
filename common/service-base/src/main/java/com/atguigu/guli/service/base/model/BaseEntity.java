package com.atguigu.guli.service.base.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * description:这是代码生成器的基类，在每张表中都包含这三个属性，当每创建一个javabean时都需要这三个属性
 * Created by Kris on 2021/3/3
 */
@Data
@Accessors(chain = true) //链式调用，get、set方法返回值是void，这个返回值是目标对象自己，可以再次进行调用
public class BaseEntity {
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
