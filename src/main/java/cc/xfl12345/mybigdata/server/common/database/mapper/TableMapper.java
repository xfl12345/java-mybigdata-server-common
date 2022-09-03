package cc.xfl12345.mybigdata.server.common.database.mapper;

import java.util.List;

public interface TableMapper<ValueType, ConditionType> extends TableNoConditionMapper<ValueType> {
    /**
     * 按 条件 查询数据。失败则抛出异常。
     */
    List<ValueType> select(ConditionType condition) throws Exception;

    /**
     * 按 条件 更新数据。失败则抛出异常。
     * @return 影响行数
     */
    long update(ValueType value, ConditionType condition) throws Exception;

    /**
     * 按 全局ID 删除数据。失败则抛出异常。
     * @return 影响行数
     */
    long delete(ConditionType condition) throws Exception;
}
