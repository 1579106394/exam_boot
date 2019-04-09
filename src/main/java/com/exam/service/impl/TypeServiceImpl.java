package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.mapper.TypeMapper;
import com.exam.pojo.Page;
import com.exam.pojo.TypeDO;
import com.exam.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题型表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, TypeDO> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<TypeDO> getListByPage(Page<TypeDO> page) {
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
        List<TypeDO> list = typeMapper.getListByPage(page);
        page.setList(list);
        Integer totalCount = typeMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }
}
