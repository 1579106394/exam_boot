package com.exam.service.impl;

import com.exam.pojo.TeacherAuthDO;
import com.exam.mapper.TeacherAuthMapper;
import com.exam.service.TeacherAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教师-权限表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-01
 */
@Service
public class TeacherAuthServiceImpl extends ServiceImpl<TeacherAuthMapper, TeacherAuthDO> implements TeacherAuthService {

}
