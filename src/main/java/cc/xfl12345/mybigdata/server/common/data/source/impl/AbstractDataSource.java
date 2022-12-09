package cc.xfl12345.mybigdata.server.common.data.source.impl;

import cc.xfl12345.mybigdata.server.common.appconst.DefaultSingleton;
import cc.xfl12345.mybigdata.server.common.appconst.TableCurdResult;
import cc.xfl12345.mybigdata.server.common.data.interceptor.DataSourceInterceptorHelper;
import cc.xfl12345.mybigdata.server.common.data.source.DataSource;
import cc.xfl12345.mybigdata.server.common.data.source.DataSourceWarpper;
import cc.xfl12345.mybigdata.server.common.database.error.SqlErrorAnalyst;
import cc.xfl12345.mybigdata.server.common.pojo.DoubleItem;
import cc.xfl12345.mybigdata.server.common.pojo.FieldNotNullChecker;
import cc.xfl12345.mybigdata.server.common.pojo.IdAndValue;
import cc.xfl12345.mybigdata.server.common.pojo.MbdId;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDataSource<Value>
    implements DataSource<Value>, DataSourceWarpper<Value> {
    @Getter
    @Setter
    protected FieldNotNullChecker fieldNotNullChecker = DefaultSingleton.FIELD_NOT_NULL_CHECKER;

    @Getter
    @Setter
    protected SqlErrorAnalyst sqlErrorAnalyst;

    @Getter
    protected DataSourceInterceptorHelper<Value> dataSourceInterceptorHelper;

    protected DataSource<Value> rawImpl;

    @PostConstruct
    public void init() throws Exception {
        fieldNotNullChecker.check(sqlErrorAnalyst, "sqlErrorAnalyst");

        rawImpl = generateRawImpl();
        dataSourceInterceptorHelper = new DataSourceInterceptorHelper<>(rawImpl);
    }

    protected abstract DataSource<Value> generateRawImpl();

    @Override
    public DataSource<Value> getRawImpl() {
        return rawImpl;
    }

    @Override
    public MbdId<?> insert4IdOrGetId(Value value) {
        MbdId<?> id;
        try {
            id = insertAndReturnId(value);
        } catch (RuntimeException e) {
            TableCurdResult result = sqlErrorAnalyst.getTableCurdResult(e);
            if (result.equals(TableCurdResult.DUPLICATE)) {
                id = selectId(value);
            } else {
                throw e;
            }
        }

        return id;
    }

    @Override
    public MbdId<?> insertAndReturnId(Value value) {
        return dataSourceInterceptorHelper.getInsertAndReturnId().execute(value);
    }

    @Override
    public long insert(Value value) {
        return dataSourceInterceptorHelper.getInsert().execute(value);
    }

    @Override
    public long insertBatch(List<Value> values) {
        return dataSourceInterceptorHelper.getInsertBatch().execute(values);
    }

    @Override
    public MbdId<?> selectId(Value value) {
        return dataSourceInterceptorHelper.getSelectId().execute(value);
    }

    @Override
    public Value selectById(MbdId<?> globalId) {
        return dataSourceInterceptorHelper.getSelectById().execute(globalId);
    }

    @Override
    public List<Value> selectBatchById(List<MbdId<?>> globalIdList) {
        return dataSourceInterceptorHelper.getSelectBatchById().execute(globalIdList);
    }

    @Override
    public void update(Value theOld, Value theNew) {
        dataSourceInterceptorHelper.getUpdate().execute(new DoubleItem<>(theOld, theNew));
    }

    @Override
    public void updateById(Value value, MbdId<?> globalId) {
        IdAndValue<Value> idAndValue = new IdAndValue<>();
        idAndValue.id = globalId;
        idAndValue.value = value;
        dataSourceInterceptorHelper.getUpdateById().execute(idAndValue);
    }

    @Override
    public void delete(Value value) {
        dataSourceInterceptorHelper.getDelete().execute(value);
    }

    @Override
    public void deleteById(MbdId<?> globalId) {
        dataSourceInterceptorHelper.getDeleteById().execute(globalId);
    }

    @Override
    public void deleteBatchById(List<MbdId<?>> globalIdList) {
        dataSourceInterceptorHelper.getDeleteBatchById().execute(globalIdList);
    }

    @SuppressWarnings("unchecked")
    protected <T> Class<T> getTypeFromRuntime(int typeArgumentIndex) {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[typeArgumentIndex];
    }
}
