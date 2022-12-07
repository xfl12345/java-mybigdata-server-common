package cc.xfl12345.mybigdata.server.common.data.source;

import java.util.List;

public interface DataSource<Value> {
    /**
     * 获取 数据对象 的 全局数据记录表 的 ID。
     * 如果数据不存在，则插入并生成 全局ID ，如果数据存在，则返回其 全局ID 。
     *
     * @return 全局数据记录表 的 ID
     */
    Object insert4IdOrGetId(Value value);


    /**
     * 插入数据，返回 全局数据记录表 的 ID 。该 API 只对有 自增主键 的表有用。
     *
     * @return 全局数据记录表 的 ID
     */
    Object insertAndReturnId(Value value);

    /**
     * 插入数据。失败则抛出异常。
     *
     * @return 影响行数
     */
    long insert(Value value);

    /**
     * 插入数据。失败则抛出异常。
     *
     * @return 影响行数
     */
    long insertBatch(List<Value> values);


    /**
     * 给定数据，返回 全局数据记录表 的 ID
     *
     * @return 全局数据记录表 的 ID
     */
    Object selectId(Value value);

    Value selectById(Object globalId);

    List<Value> selectBatchById(List<Object> globalIdList);


    default void update(Value theOld, Value theNew) {
        updateById(theNew, selectById(theOld));
    }

    /**
     * 按 全局ID 更新数据。失败则抛出异常。
     */
    void updateById(Value value, Object globalId);


    default void delete(Value value) {
        deleteById(selectId(value));
    }

    /**
     * 按 全局ID 删除数据。失败则抛出异常。
     */
    void deleteById(Object globalId);

    void deleteBatchById(List<Object> globalIdList);


    Class<Value> getValueType();
}
