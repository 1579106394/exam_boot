package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.mapper.BankKnowledgeMapper;
import com.exam.pojo.BankKnowledgeDO;
import com.exam.pojo.Page;
import com.exam.service.BankKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 知识点表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-11
 */
@Service
public class BankKnowledgeServiceImpl extends ServiceImpl<BankKnowledgeMapper, BankKnowledgeDO> implements BankKnowledgeService {

    @Autowired
    private BankKnowledgeMapper bankKnowledgeMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<BankKnowledgeDO> getByPage(Page<BankKnowledgeDO> page) {
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
        List<BankKnowledgeDO> list = bankKnowledgeMapper.getListByPage(page);
        page.setList(list);
        Integer totalCount = bankKnowledgeMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }
}
