package com.exam.pojo;

import com.exam.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页类
 *
 * @author 杨德石
 */
@Data
public class Page<T> implements Serializable {

    /**
     * 起始索引
     */
    private Integer index = 0;

    /**
     * 当前页
     */
    private Integer currentPage = 1;

    /**
     * 每页显示条数
     */
    private Integer currentCount;

    /**
     * 总条数
     */
    private Integer totalCount;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 拍序列
     */
    private String sortName;

    /**
     * 排序方式
     */
    private String sortOrder = "asc";

    /**
     * 其他参数
     */
    private Map<String, Object> params = Maps.newHashMap();

    /**
     * 每页显示数据
     */
    private List<T> list = Lists.newArrayList();

    /**
     * 查询出来的参数
     */
    private List<String> columns = Lists.newArrayList();

    public void filterParams() {
        // 处理排序参数
        if(StringUtils.isNotBlank(sortName)) {
            this.sortName = StringUtils.camelToUnderline(this.sortName);
            this.sortOrder = this.sortOrder.replaceAll("ending", "");
        }
    }

}
