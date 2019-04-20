package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.constant.OtherConstant;
import com.exam.mapper.BankMapper;
import com.exam.pojo.BankDO;
import com.exam.pojo.Page;
import com.exam.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题库表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, BankDO> implements BankService {

    @Autowired
    private BankMapper bankMapper;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<BankDO> getListByPage(Page<BankDO> page) {
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
        List<BankDO> list = bankMapper.getListByPage(page);
        page.setList(list);
        Integer totalCount = bankMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }
}
