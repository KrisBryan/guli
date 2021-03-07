package com.atguigu.guli.service.edu.controller.admin;

import com.atguigu.guli.service.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.query.TeacherQuery;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description:
 * Created by Kris on 2021/3/3
 */
@CrossOrigin
@Slf4j
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherConrtoller {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/list")
    @ApiOperation("所有讲师列表")
    public R listAll() {
        log.debug("{}级别日志", "debug");
        log.info("{}级别日志", "info");
        log.warn("{}级别日志", "warn");
        log.error("{}级别日志", "error");
//        return teacherService.list();
        List<Teacher> list = teacherService.list();
        return R.ok().data("items", list).message("获取讲师列表成功");
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除目标对象", notes = "根据id删除目标对象")
    public R deleteById(@ApiParam(value = "讲师ID", required = true, defaultValue = "1") @PathVariable("id") String id) {
        boolean result = teacherService.removeById(id);
        if (result) {
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败，数据不存在");
    }

    @ApiOperation("讲师列表分页")
    @GetMapping("/list/{page}/{limit}")
    public R page(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                  @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                  TeacherQuery teacherQuery) {
        Page<Teacher> pageModel=teacherService.selectByPage(page, limit, teacherQuery);
//        Page<Teacher> pageParam = new Page<>(page, limit);
//        IPage<Teacher> pageModel = teacherService.page(pageParam);
        return R.ok().data("pageModel", pageModel);
    }

    @ApiOperation("新增讲师")
    @PostMapping("/save")
    public R save(@ApiParam(value = "讲师对象", required = true) @RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return R.ok().message("添加成功");
        }
        return R.error().message("添加失败");
    }

    @ApiOperation("更新数据")
    @PutMapping("/update")
    public R updateById(@ApiParam(value = "讲师对象", required = true) @RequestBody Teacher teacher) {
        boolean update = teacherService.updateById(teacher);
        if (update) {
            return R.ok().message("更新成功");
        }
        return R.error().message("更新失败");
    }

    @ApiOperation("根据ID查询")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam(value = "讲师ID", required = true) @PathVariable String id) {
        Teacher byId = teacherService.getById(id);
        if (byId != null) {
            return R.ok().data("item", byId);
        }
        return R.error().message("数据不存在");
    }

}
