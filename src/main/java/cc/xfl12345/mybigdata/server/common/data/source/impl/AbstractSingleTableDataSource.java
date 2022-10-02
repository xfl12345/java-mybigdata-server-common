package cc.xfl12345.mybigdata.server.common.data.source.impl;


import cc.xfl12345.mybigdata.server.common.data.source.DataSource;
import cc.xfl12345.mybigdata.server.common.database.mapper.TableNoConditionMapper;

import java.util.List;

public abstract class AbstractSingleTableDataSource<ValueType, PojoType> implements DataSource<ValueType> {
    public abstract TableNoConditionMapper<PojoType> getMapper();

    protected abstract String[] getSelectContentFieldOnly();

    protected abstract ValueType getValue(PojoType pojoType);

    protected abstract PojoType getPojo(ValueType valueType);

    @Override
    public Object insertAndReturnId(ValueType value) throws Exception {
        return getMapper().insertAndReturnId(getPojo(value));
    }

    @Override
    public long insert(ValueType value) throws Exception {
        return getMapper().insert(getPojo(value));
    }

    @Override
    public long insertBatch(List<ValueType> values) throws Exception {
        return getMapper().insertBatch(values.parallelStream().map(this::getPojo).toList());
    }

    @Override
    public Object selectId(ValueType value) throws Exception {
        return getMapper().selectId(getPojo(value));
    }

    @Override
    public ValueType selectById(Object globalId) throws Exception {
        return getValue(getMapper().selectById(globalId, getSelectContentFieldOnly()));
    }

    @Override
    public void updateById(ValueType value, Object globalId) throws Exception {
        getMapper().updateById(getPojo(value), globalId);
    }

    @Override
    public void deleteById(Object globalId) throws Exception {
        getMapper().deleteById(globalId);
    }
}
