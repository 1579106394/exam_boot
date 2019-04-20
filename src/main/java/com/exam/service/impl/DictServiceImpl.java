package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.constant.OtherConstant;
import com.exam.mapper.DictMapper;
import com.exam.pojo.DictDO;
import com.exam.pojo.Page;
import com.exam.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据字典表
 * college-学院
 * major-专业
 * job-职务
 * title-职称
 * subject-科目 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictDO> implements DictService {

    @Autowired
    private DictMapper dictMapper;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Override
    public Page<DictDO> getListByPage(Page<DictDO> page) {
        // 处理参数
        page.filterParams();
        // 设置每页显示条数
        if (page.getCurrentCount() == null) {
            page.setCurrentCount(OtherConstant.CURRENT_COUNT);
        }
        // 计算索引
        Integer index = (page.getCurrentPage() - 1) * page.getCurrentCount();
        page.setIndex(index);
        // 查询每页数据
        List<DictDO> list = dictMapper.getListByPage(page);
        page.setList(list);
        Integer totalCount = dictMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }

    /**
     * 根据父级id查询
     * @param fatherId
     * @return
     */
    @Override
    public List<DictDO> getByFather(String fatherId) {
        return dictMapper.getByFather(fatherId);
    }
}
