package cc.xfl12345.mybigdata.server.common.database.mapper;

import java.util.List;

public interface TableNoConditionMapper<Value> extends SingleGenericTypeGetter<Value> {
    /**
     * 插入数据。失败则抛出异常。
     * @return 影响行数
     */
    long insert(Value value);

    /**
     * 插入数据。失败则抛出异常。
     * @return 影响行数
     */
    long insertBatch(List<Value> values);

    /**
     * 插入数据，返回 全局数据记录表 的 ID
     * @return 全局数据记录表 的 ID
     */
    Object insertAndReturnId(Value value);

    Value selectOne(Value value, String[] fields);

    Value selectById(Object globalId, String[] fields);

    /**
     * 给定数据，返回 全局数据记录表 的 ID
     * @return 全局数据记录表 的 ID
     */
    Object selectId(Value value);

    /**
     * 按 全局ID 更新数据。失败则抛出异常。
     */
    void updateById(Value value, Object globalId);

    /**
     * 按 全局ID 删除数据。失败则抛出异常。
     */
    void deleteById(Object globalId);
}
