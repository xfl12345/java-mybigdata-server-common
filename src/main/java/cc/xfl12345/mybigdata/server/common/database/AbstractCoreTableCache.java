package cc.xfl12345.mybigdata.server.common.database;

import cc.xfl12345.mybigdata.server.common.appconst.DefaultSingleton;
import cc.xfl12345.mybigdata.server.common.database.error.TableDataException;
import cc.xfl12345.mybigdata.server.common.pojo.FieldNotNullChecker;
import cc.xfl12345.mybigdata.server.common.pojo.MbdId;
import cc.xfl12345.mybigdata.server.common.pojo.TwoWayMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public abstract class AbstractCoreTableCache<ID, Value> {
    @Getter
    @Setter
    protected FieldNotNullChecker fieldNotNullChecker = DefaultSingleton.FIELD_NOT_NULL_CHECKER;

    @Getter
    protected TwoWayMap<Value, MbdId<ID>> tableNameCache;

    @Getter
    protected MbdId<ID> idOfTrue;

    @Getter
    protected MbdId<ID> idOfFalse;

    @PostConstruct
    public void init() throws Exception {
        refreshBooleanCache();
        refreshCoreTableNameCache();
    }

    public abstract Class<ID> getIdType();

    public abstract void refreshBooleanCache() throws Exception;

    public abstract void refreshCoreTableNameCache() throws Exception;

    protected abstract String tableNameOfBoolean();

    public boolean getBooleanById(MbdId<ID> globalId) {
        if (idOfTrue.equals(globalId)) {
            return true;
        }
        if (idOfFalse.equals(globalId)) {
            return false;
        }

        throw new TableDataException(
            "The reference of id '" + globalId + "' is not a boolean value.",
            new MbdId<?>[]{ globalId },
            tableNameOfBoolean()
        );
    }

    public abstract MbdId<ID> getTableNameId(Class<?> pojoClass);

    public abstract <ID2 extends MbdId<ID>> Class<?> getPojoClassByTableNameId(ID2 id);

    public abstract <T> T getEmptyPoEntity(Class<T> pojoClass);
}
