package cc.xfl12345.mybigdata.server.common.web.mapper;

import cc.xfl12345.mybigdata.server.common.data.condition.SingleTableCondition;

import java.math.BigDecimal;
import java.util.List;

public interface AdvanceSearchMapper {
    public List<String> selectStringByPrefix(String prefix);

    public List<BigDecimal> selectNumberByPrefix(String prefix);

    public List<BigDecimal> selectNumberByPrefix(Integer prefix);

    public List<Object> selectByCondition(SingleTableCondition condition);
}
