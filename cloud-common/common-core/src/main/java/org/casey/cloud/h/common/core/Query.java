package org.casey.cloud.h.common.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.casey.cloud.h.common.core.consts.DelimiterConst;
import org.casey.cloud.h.common.core.util.StringUtil;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName Query
 * @Author Code2021
 * @Description 分页查询对象
 * @Date 2020/11/29 15:25
 */

@Data
@NoArgsConstructor
@ApiModel("分页查询对象")
public class Query implements Serializable {

    @ApiModelProperty(value = "参数Map", example = "{'key1':'','key2':1,'key3':[],'key4':{},'beginCreateTime':'2020-12-01 00:00:00','endCreateTime':'2020-12-02 00:00:00','beginUpdateTime':'2020-12-01 00:00:00','endUpdateTime':'2020-12-02 00:00:00'}",notes = "所有参数选填")
    private Map<String, Object> parameterMap;

    @NotNull
    @ApiModelProperty(value = "分页参数 当前页", example = "1", required = true)
    private Long pageNum;

    @NotNull
    @ApiModelProperty(value = "分页参数 分页大小 pageNum为0时此属性失效", example = "10", required = true)
    private Long pageSize;

    @NotEmpty
    @ApiModelProperty(value = "默认根据 id 字段排序", example = "id", required = true)
    private String orderBy;

    @NotNull
    @ApiModelProperty(value = "默认升序排序", example = "true", required = true)
    private Boolean asc;

    public Query(Map<String, Object> parameterMap, String orderBy, Boolean asc, Long pageNum, Long pageSize) {
        this.parameterMap = parameterMap;
        if (!orderBy.contains(DelimiterConst.UNDERLINE)) {
            // 不包含下划线则 驼峰转下划线
            orderBy = StringUtil.humpToUnderline(orderBy);
        }
        this.orderBy = orderBy;
        this.asc = asc;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public void setOrderBy(String orderBy) {
        if (!orderBy.contains(DelimiterConst.UNDERLINE)) {
            // 不包含下划线则 驼峰转下划线
            orderBy = StringUtil.humpToUnderline(orderBy);
        }
        this.orderBy = orderBy;
    }
}
