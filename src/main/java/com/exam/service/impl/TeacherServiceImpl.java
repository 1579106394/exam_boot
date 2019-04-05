package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.mapper.TeacherMapper;
import com.exam.pojo.Page;
import com.exam.pojo.TeacherDO;
import com.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 教师表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-31
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, TeacherDO> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;

    @Override
    public Page<TeacherDO> getByPage(Page<TeacherDO> page) {
        // 处理参数
        page.filterParams();
        // 设置每页显示条数
        if (page.getCurrentCount() == null) {
            page.setCurrentCount(CURRENT_COUNT);
        }
        // 计算索引
        Integer index = (page.getCurrentPage() - 1) * page.getCurrentCount();
        page.setIndex(index);
        // 查询每页数据
        List<TeacherDO> list = teacherMapper.getListByPage(page);
        page.setList(list);
        Integer totalCount = teacherMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }
}
