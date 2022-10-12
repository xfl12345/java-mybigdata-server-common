package cc.xfl12345.mybigdata.server.common.database.mapper;

import java.util.List;

public interface TableBasicMapper<Pojo> {
    /**
     * 插入数据。失败则抛出异常。
     * @return 影响行数
     */
    long insert(Pojo pojo);

    /**
     * 插入数据。失败则抛出异常。
     * @return 影响行数
     */
    long insertBatch(List<Pojo> pojos);

    /**
     * 插入数据，返回 全局数据记录表 的 ID
     * @return 全局数据记录表 的 ID
     */
    Object insertAndReturnId(Pojo pojo);

    Pojo selectOne(Pojo pojo, String[] fields);

    Pojo selectById(Object globalId, String[] fields);

    /**
     * 给定数据，返回 全局数据记录表 的 ID
     * @return 全局数据记录表 的 ID
     */
    Object selectId(Pojo pojo);

    /**
     * 按 全局ID 更新数据。失败则抛出异常。
     */
    void updateById(Pojo pojo, Object globalId);

    /**
     * 按 全局ID 删除数据。失败则抛出异常。
     */
    void deleteById(Object globalId);

    Class<Pojo> getPojoType();
}
