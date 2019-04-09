package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.mapper.ChoiceMapper;
import com.exam.pojo.ChoiceAnswerDO;
import com.exam.pojo.ChoiceDO;
import com.exam.pojo.Page;
import com.exam.service.ChoiceService;
import com.exam.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 选择题表 服务实现类
 * </p>
 *
 * @author 杨德石
 * @since 2019-03-28
 */
@Service
public class ChoiceServiceImpl extends ServiceImpl<ChoiceMapper, ChoiceDO> implements ChoiceService {

    @Autowired
    private ChoiceMapper choiceMapper;
    @Value("${CURRENT_COUNT}")
    private Integer CURRENT_COUNT;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Override
    public Page<ChoiceDO> getByPage(Page<ChoiceDO> page) {
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
        List<ChoiceDO> list = choiceMapper.getListByPage(page);
        // 过滤正确答案选项
        list.forEach(e -> {
            List<String> numberList = e.getChoiceAnswer().stream().filter(a -> a.getAnswerTrue() == true)
                    .map(ChoiceAnswerDO::getAnswerNumber).collect(Collectors.toList());
            e.setChoiceTrue(StringUtils.join(numberList, ", "));
        });
        page.setList(list);
        Integer totalCount = choiceMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        // 计算总页数
        page.setTotalPage((int) Math.ceil((page.getTotalCount() * 1.0) / page.getCurrentCount()));
        return page;
    }
}
