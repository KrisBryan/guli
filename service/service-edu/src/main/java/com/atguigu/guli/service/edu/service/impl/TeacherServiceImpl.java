package com.atguigu.guli.service.edu.service.impl;

import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.query.TeacherQuery;
import com.atguigu.guli.service.edu.mapper.TeacherMapper;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-03-03
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public Page<Teacher> selectByPage(Long page, Long limit, TeacherQuery teacherQuery) {
        Page<Teacher> teacherPage = new Page<>(page, limit);

        if (teacherQuery == null) {
            return baseMapper.selectPage(teacherPage, null);
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String joinDateBegin = teacherQuery.getJoinDateBegin();
        String joinDateEnd = teacherQuery.getJoinDateEnd();
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.likeRight(Teacher::getName, name);
        }
        if (level != null) {
            queryWrapper.eq(Teacher::getLevel, level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            queryWrapper.ge(Teacher::getJoinDate, joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            queryWrapper.le(Teacher::getJoinDate, joinDateEnd);
        }
        return baseMapper.selectPage(teacherPage, queryWrapper);
    }
}
