package com.atguigu.guli.service.edu.controller.admin;

import com.atguigu.guli.service.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description:
 * Created by Kris on 2021/3/3
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherConrtoller {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/list")
    @ApiOperation("所有讲师列表")
    public R listAll() {
//        return teacherService.list();
        List<Teacher> list = teacherService.list();
        return R.ok().data("items", list).message("获取讲师列表成功");
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除目标对象",notes = "根据id删除目标对象")
    public R deleteById(@ApiParam(value = "讲师ID",required = true,defaultValue = "1") @PathVariable("id") String id){
        boolean result = teacherService.removeById(id);
        if (result){
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败，数据不存在");
    }

    @ApiOperation("讲师列表分页")
    @GetMapping("/list/{page}/{limit}")
    public R page(@ApiParam(value = "当前页码",required = true)@PathVariable Long page,@ApiParam(value = "每页记录数",required = true)@PathVariable Long limit){
        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.page(pageParam);
        return R.ok().data("pageModel", pageModel);
    }
}
