package cc.xfl12345.mybigdata.server.common.data.source;

import cc.xfl12345.mybigdata.server.common.pojo.MbdId;

import java.util.List;

public interface DataSource<Value> {
    /**
     * 获取 数据对象 的 全局数据记录表 的 ID。
     * 如果数据不存在，则插入并生成 全局ID ，如果数据存在，则返回其 全局ID 。
     *
     * @return 全局数据记录表 的 ID
     */
    MbdId<?> insert4IdOrGetId(Value value);


    /**
     * 插入数据，返回 全局数据记录表 的 ID 。该 API 只对有 自增主键 的表有用。
     *
     * @return 全局数据记录表 的 ID
     */
    MbdId<?> insertAndReturnId(Value value);

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
    MbdId<?> selectId(Value value);

    Value selectById(MbdId<?> globalId);

    List<Value> selectBatchById(List<MbdId<?>> globalIdList);


    default void update(Value theOld, Value theNew) {
        updateById(theNew, selectId(theOld));
    }

    /**
     * 按 全局ID 更新数据。失败则抛出异常。
     */
    void updateById(Value value, MbdId<?> globalId);


    default void delete(Value value) {
        deleteById(selectId(value));
    }

    /**
     * 按 全局ID 删除数据。失败则抛出异常。
     */
    void deleteById(MbdId<?> globalId);

    void deleteBatchById(List<MbdId<?>> globalIdList);


    Class<Value> getValueType();
}
