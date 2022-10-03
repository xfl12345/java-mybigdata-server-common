package cc.xfl12345.mybigdata.server.common.database.mapper;

import java.util.List;

public interface TableNoConditionMapper<Value> {
    /**
     * 插入数据。失败则抛出异常。
     * @return 影响行数
     */
    long insert(Value value) throws Exception;

    /**
     * 插入数据。失败则抛出异常。
     * @return 影响行数
     */
    long insertBatch(List<Value> values) throws Exception;

    /**
     * 插入数据，返回 全局数据记录表 的 ID
     * @return 全局数据记录表 的 ID
     */
    Object insertAndReturnId(Value value) throws Exception;

    Value selectOne(Value value, String[] fields) throws Exception;

    Value selectById(Object globalId, String[] fields) throws Exception;

    /**
     * 给定数据，返回 全局数据记录表 的 ID
     * @return 全局数据记录表 的 ID
     */
    Object selectId(Value value) throws Exception;

    /**
     * 按 全局ID 更新数据。失败则抛出异常。
     */
    void updateById(Value value, Object globalId) throws Exception;

    /**
     * 按 全局ID 删除数据。失败则抛出异常。
     */
    void deleteById(Object globalId) throws Exception;
}
