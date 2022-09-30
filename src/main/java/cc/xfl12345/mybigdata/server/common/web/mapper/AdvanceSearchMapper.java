package cc.xfl12345.mybigdata.server.common.web.mapper;

import cc.xfl12345.mybigdata.server.common.data.condition.SingleTableCondition;
import cc.xfl12345.mybigdata.server.common.pojo.IdAndValue;

import java.math.BigDecimal;
import java.util.List;

public interface AdvanceSearchMapper {
    List<IdAndValue<String>> selectStringByPrefix(String prefix);

    List<IdAndValue<BigDecimal>> selectNumberByPrefix(String prefix);

    List<IdAndValue<BigDecimal>> selectNumberByPrefix(Integer prefix);

    List<Object> selectByCondition(SingleTableCondition condition);
}
