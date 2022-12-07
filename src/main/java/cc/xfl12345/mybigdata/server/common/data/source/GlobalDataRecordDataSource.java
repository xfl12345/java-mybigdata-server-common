package cc.xfl12345.mybigdata.server.common.data.source;

import cc.xfl12345.mybigdata.server.common.database.pojo.CommonGlobalDataRecord;

import java.util.Date;
import java.util.List;

public interface GlobalDataRecordDataSource extends DataSource<CommonGlobalDataRecord> {
    CommonGlobalDataRecord getNewDataInstance(Date createTime, Object tableNameId);

    CommonGlobalDataRecord getNewRegisteredDataInstance(Date createTime, Object tableNameId);

    CommonGlobalDataRecord getNewDataInstance(Date createTime, Class<?> pojoClass);

    CommonGlobalDataRecord getNewRegisteredDataInstance(Date createTime, Class<?> pojoClass);

    List<CommonGlobalDataRecord> getNewDataInstances(Date createTime, Class<?> pojoClass, int batchSize);

    List<CommonGlobalDataRecord> getNewRegisteredDataInstances(Date createTime, Class<?> pojoClass, int batchSize);

    List<CommonGlobalDataRecord> getRecordsByUUID(List<String> uuids);

    void updateOneRow(Object id, Date updateTime);

    void updateOneRow(CommonGlobalDataRecord record, Date updateTime);
}
