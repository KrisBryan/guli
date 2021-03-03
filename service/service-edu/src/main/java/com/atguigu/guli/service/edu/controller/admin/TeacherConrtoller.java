package com.atguigu.guli.service.edu.controller.admin;

import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description:
 * Created by Kris on 2021/3/3
 */
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherConrtoller {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/list")
    public List<Teacher> listAll() {
        return teacherService.list();
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String id){
        return teacherService.removeById(id);
    }

}
