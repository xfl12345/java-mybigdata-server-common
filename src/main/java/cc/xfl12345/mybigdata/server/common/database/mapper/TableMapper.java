package cc.xfl12345.mybigdata.server.common.database.mapper;

import java.util.List;

public interface TableMapper<Value, Condition> extends TableNoConditionMapper<Value> {
    /**
     * 按 条件 查询数据。失败则抛出异常。
     */
    List<Value> select(Condition condition) throws Exception;

    /**
     * 按 条件 更新数据。失败则抛出异常。
     * @return 影响行数
     */
    long update(Value value, Condition condition) throws Exception;

    /**
     * 按 全局ID 删除数据。失败则抛出异常。
     * @return 影响行数
     */
    long delete(Condition condition) throws Exception;
}
