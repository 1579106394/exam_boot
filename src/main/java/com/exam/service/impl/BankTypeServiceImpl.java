package com.exam.service.impl;

import com.exam.pojo.BankTypeDO;
import com.exam.mapper.BankTypeMapper;
import com.exam.service.BankTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题库-题型对应表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-30
 */
@Service
public class BankTypeServiceImpl extends ServiceImpl<BankTypeMapper, BankTypeDO> implements BankTypeService {

    @Autowired
    private BankTypeMapper bankTypeMapper;

    /**
     * 根据知识点id查询题型
     * @param bankId
     * @return
     */
    @Override
    public List<BankTypeDO> getListByKnow(String bankId) {
        return bankTypeMapper.getListByKnow(bankId);
    }
}
