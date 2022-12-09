package cc.xfl12345.mybigdata.server.common.data.interceptor;


import cc.xfl12345.mybigdata.server.common.appconst.data.EnumDataSourceApiName;
import cc.xfl12345.mybigdata.server.common.data.interceptor.type.*;
import cc.xfl12345.mybigdata.server.common.data.source.DataSource;
import cc.xfl12345.mybigdata.server.common.pojo.DoubleItem;
import cc.xfl12345.mybigdata.server.common.pojo.IdAndValue;
import cc.xfl12345.mybigdata.server.common.pojo.MbdId;
import lombok.Getter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class DataSourceInterceptorHelper<Value> {
    @Getter
    protected Class<Value> valueType;

    public DataSourceInterceptorHelper(Class<Value> valueType) {
        this.valueType = valueType;
    }

    public DataSourceInterceptorHelper(DataSource<Value> dataSource) {
        init(dataSource);
    }

    public void init(DataSource<Value> dataSource) {
        valueType = dataSource.getValueType();

        insert4IdOrGetId =
            new DataInterceptorChain<>(
                new ActionInterceptorChain<>(null),
                EnumDataSourceApiName.insert4IdOrGetId,
                valueType,
                MbdId.class
            );

        insertAndReturnId =
            new DataInterceptorChain<>(
                insertActionInterceptorChain,
                EnumDataSourceApiName.insertAndReturnId,
                valueType,
                MbdId.class
            );

        insert =
            new DataInterceptorChain<>(
                insertActionInterceptorChain,
                EnumDataSourceApiName.insert,
                valueType,
                Long.class
            );

        insertBatch =
            new DataInterceptorChain<>(
                insertActionInterceptorChain,
                EnumDataSourceApiName.insertBatch,
                getListType(),
                Long.class
            );

        selectId =
            new DataInterceptorChain<>(
                selectActionInterceptorChain,
                EnumDataSourceApiName.selectId,
                valueType,
                MbdId.class
            );

        selectById =
            new DataInterceptorChain<>(
                selectActionInterceptorChain,
                EnumDataSourceApiName.selectById,
                MbdId.class,
                valueType
            );

        selectBatchById =
            new DataInterceptorChain<>(
                selectActionInterceptorChain,
                EnumDataSourceApiName.selectBatchById,
                getListType(MbdId.class),
                getListType()
            );

        update =
            new DataInterceptorChain<>(
                updateActionInterceptorChain,
                EnumDataSourceApiName.update,
                new ParameterizedType() {
                    @Override
                    public Type[] getActualTypeArguments() {
                        return new Type[] {valueType, valueType};
                    }

                    @Override
                    public Type getRawType() {
                        return DoubleItem.class;
                    }

                    @Override
                    public Type getOwnerType() {
                        return null;
                    }
                },
                Void.class
            );

        updateById =
            new DataInterceptorChain<>(
                updateActionInterceptorChain,
                EnumDataSourceApiName.updateById,
                getIdAndValueType(),
                Void.class
            );

        delete =
            new DataInterceptorChain<>(
                deleteActionInterceptorChain,
                EnumDataSourceApiName.delete,
                valueType,
                Void.class
            );

        deleteById =
            new DataInterceptorChain<>(
                deleteActionInterceptorChain,
                EnumDataSourceApiName.deleteById,
                MbdId.class,
                Void.class
            );

        deleteBatchById =
            new DataInterceptorChain<>(
                deleteActionInterceptorChain,
                EnumDataSourceApiName.deleteBatchById,
                getListType(MbdId.class),
                Void.class
            );

        insert4IdOrGetId.setDefaultAction(dataSource::insert4IdOrGetId);
        insertAndReturnId.setDefaultAction(dataSource::insertAndReturnId);
        insert.setDefaultAction(dataSource::insert);
        insertBatch.setDefaultAction(dataSource::insertBatch);
        selectId.setDefaultAction(dataSource::selectId);
        selectById.setDefaultAction(dataSource::selectById);
        selectBatchById.setDefaultAction(dataSource::selectBatchById);
        update.setDefaultAction(values -> {
            dataSource.update(values.getFirstItem(), values.getSecondItem());
            return null;
        });
        updateById.setDefaultAction(idAndValue -> {
            dataSource.updateById(idAndValue.value, idAndValue.id);
            return null;
        });
        delete.setDefaultAction(value -> {
            dataSource.delete(value);
            return null;
        });
        deleteById.setDefaultAction(globalId -> {
            dataSource.deleteById(globalId);
            return null;
        });
        deleteBatchById.setDefaultAction(globalIdList -> {
            dataSource.deleteBatchById(globalIdList);
            return null;
        });
    }

    @Getter
    protected final ActionInterceptorChain<Insert> insertActionInterceptorChain =
        new ActionInterceptorChain<>(CurdTypeGetters.INSERT);

    @Getter
    protected final ActionInterceptorChain<Update> updateActionInterceptorChain =
        new ActionInterceptorChain<>(CurdTypeGetters.UPDATE);

    @Getter
    protected final ActionInterceptorChain<Select> selectActionInterceptorChain =
        new ActionInterceptorChain<>(CurdTypeGetters.SELECT);

    @Getter
    protected final ActionInterceptorChain<Delete> deleteActionInterceptorChain =
        new ActionInterceptorChain<>(CurdTypeGetters.DELETE);

    protected Type getListType() {
        return getListType(valueType);
    }

    protected Type getListType(Class<?> valueType) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{valueType};
            }

            @Override
            public Type getRawType() {
                return List.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    protected Type getIdAndValueType() {
        return getIdAndValueType(valueType);
    }

    protected Type getIdAndValueType(Class<?> valueType) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{valueType};
            }

            @Override
            public Type getRawType() {
                return IdAndValue.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    @Getter
    protected DataInterceptorChain<Value, MbdId<?>> insert4IdOrGetId;

    @Getter
    protected DataInterceptorChain<Value, MbdId<?>> insertAndReturnId;

    @Getter
    protected DataInterceptorChain<Value, Long> insert;

    @Getter
    protected DataInterceptorChain<List<Value>, Long> insertBatch;

    @Getter
    protected DataInterceptorChain<Value, MbdId<?>> selectId;

    @Getter
    protected DataInterceptorChain<MbdId<?>, Value> selectById;

    @Getter
    protected DataInterceptorChain<List<MbdId<?>>, List<Value>> selectBatchById;

    @Getter
    protected DataInterceptorChain<DoubleItem<Value, Value>, Void> update;

    @Getter
    protected DataInterceptorChain<IdAndValue<Value>, Void> updateById;

    @Getter
    protected DataInterceptorChain<Value, Void> delete;

    @Getter
    protected DataInterceptorChain<MbdId<?>, Void> deleteById;

    @Getter
    protected DataInterceptorChain<List<MbdId<?>>, Void> deleteBatchById;

    public DataInterceptorChain<?, ?> getDataInterceptorManager(EnumDataSourceApiName name) {
        DataInterceptorChain<?, ?> result;
        switch (name) {
            case insert4IdOrGetId -> result = insert4IdOrGetId;
            case insertAndReturnId -> result = insertAndReturnId;
            case insert -> result = insert;
            case insertBatch -> result = insertBatch;
            case selectId -> result = selectId;
            case selectById -> result = selectById;
            case selectBatchById -> result = selectBatchById;
            case update -> result = update;
            case updateById -> result = updateById;
            case delete -> result = delete;
            case deleteById -> result = deleteById;
            case deleteBatchById -> result = deleteBatchById;
            default -> result = null;
        }

        return result;
    }
}
