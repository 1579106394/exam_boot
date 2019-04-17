package com.exam.service.impl;

import com.exam.pojo.CodeDO;
import com.exam.mapper.CodeMapper;
import com.exam.service.CodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 编程题 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-04-17
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, CodeDO> implements CodeService {

}
