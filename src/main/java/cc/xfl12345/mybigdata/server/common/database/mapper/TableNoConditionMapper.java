package cc.xfl12345.mybigdata.server.common.database.mapper;

import java.util.List;

public interface TableNoConditionMapper<ValueType> {
    /**
     * 插入数据。失败则抛出异常。
     * @return 影响行数
     */
    long insert(ValueType value) throws Exception;

    /**
     * 插入数据。失败则抛出异常。
     * @return 影响行数
     */
    long insertBatch(List<ValueType> values) throws Exception;

    /**
     * 插入数据，返回 全局数据记录表 的 ID
     * @return 全局数据记录表 的 ID
     */
    Object insertAndReturnId(ValueType value) throws Exception;

    ValueType selectOne(ValueType value, String[] fields) throws Exception;

    ValueType selectById(Object globalId, String[] fields) throws Exception;

    /**
     * 给定数据，返回 全局数据记录表 的 ID
     * @return 全局数据记录表 的 ID
     */
    Object selectId(ValueType value) throws Exception;

    /**
     * 按 全局ID 更新数据。失败则抛出异常。
     */
    void updateById(ValueType value, Object globalId) throws Exception;

    /**
     * 按 全局ID 删除数据。失败则抛出异常。
     */
    void deleteById(Object globalId) throws Exception;
}
