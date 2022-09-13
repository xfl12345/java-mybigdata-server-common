package cc.xfl12345.mybigdata.server.common.database;

import cc.xfl12345.mybigdata.server.common.appconst.AppConst;
import cc.xfl12345.mybigdata.server.common.pojo.TwoWayMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public abstract class AbstractCoreTableCache <IdType, ValueType> implements InitializingBean {
    @Getter
    @Setter
    protected String fieldCanNotBeNullMessageTemplate = AppConst.FIELD_CAN_NOT_BE_NULL_MESSAGE_TEMPLATE;

    @Getter
    protected TwoWayMap<ValueType, IdType> tableNameCache;

    @Getter
    protected IdType idOfTrue;

    @Getter
    protected IdType idOfFalse;

    @Override
    public void afterPropertiesSet() throws Exception {
        refreshBooleanCache();
        refreshCoreTableNameCache();
    }

    public abstract void refreshBooleanCache() throws Exception;

    public abstract void refreshCoreTableNameCache() throws Exception;
}
