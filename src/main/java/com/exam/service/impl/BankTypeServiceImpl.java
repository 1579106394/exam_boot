package com.exam.service.impl;

import com.exam.pojo.BankTypeDO;
import com.exam.mapper.BankTypeMapper;
import com.exam.service.BankTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
