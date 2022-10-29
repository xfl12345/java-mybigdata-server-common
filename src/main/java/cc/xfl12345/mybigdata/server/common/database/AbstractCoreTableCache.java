package cc.xfl12345.mybigdata.server.common.database;

import cc.xfl12345.mybigdata.server.common.appconst.AppConst;
import cc.xfl12345.mybigdata.server.common.database.error.TableDataException;
import cc.xfl12345.mybigdata.server.common.pojo.TwoWayMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public abstract class AbstractCoreTableCache <ID, Value> {
    @Getter
    @Setter
    protected String fieldCanNotBeNullMessageTemplate = AppConst.FIELD_CAN_NOT_BE_NULL_MESSAGE_TEMPLATE;

    @Getter
    protected TwoWayMap<Value, ID> tableNameCache;

    @Getter
    protected ID idOfTrue;

    @Getter
    protected ID idOfFalse;

    @PostConstruct
    public void init() throws Exception {
        refreshBooleanCache();
        refreshCoreTableNameCache();
    }

    public abstract void refreshBooleanCache() throws Exception;

    public abstract void refreshCoreTableNameCache() throws Exception;

    protected abstract String tableNameOfBoolean();

    public boolean getBooleanById(ID globalId) {
        if (idOfTrue.equals(globalId)) {
            return true;
        }
        if (idOfFalse.equals(globalId)) {
            return false;
        }
        throw new TableDataException(
            "The reference of id '" + globalId + "' is not a boolean value.",
            new Object[]{ globalId },
            tableNameOfBoolean()
        );
    }
}
