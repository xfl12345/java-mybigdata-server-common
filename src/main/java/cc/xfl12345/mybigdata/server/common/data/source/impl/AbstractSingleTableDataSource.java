package cc.xfl12345.mybigdata.server.common.data.source.impl;


import cc.xfl12345.mybigdata.server.common.appconst.AppConst;
import cc.xfl12345.mybigdata.server.common.data.source.DataSource;
import cc.xfl12345.mybigdata.server.common.database.mapper.TableNoConditionMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public abstract class AbstractSingleTableDataSource<ValueType, PojoType> implements DataSource<ValueType>, InitializingBean {
    @Getter
    @Setter
    protected String fieldCanNotBeNullMessageTemplate = AppConst.FIELD_CAN_NOT_BE_NULL_MESSAGE_TEMPLATE;

    @Getter
    @Setter
    protected TableNoConditionMapper<PojoType> mapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (mapper == null) {
            throw new IllegalArgumentException(fieldCanNotBeNullMessageTemplate.formatted("mapper"));
        }
    }

    protected abstract String[] getSelectContentFieldOnly();

    protected abstract ValueType getValue(PojoType pojoType);

    protected abstract PojoType getPojo(ValueType valueType);

    @Override
    public Object insertAndReturnId(ValueType value) throws Exception {
        return mapper.insertAndReturnId(getPojo(value));
    }

    @Override
    public long insert(ValueType value) throws Exception {
        return mapper.insert(getPojo(value));
    }

    @Override
    public long insertBatch(List<ValueType> values) throws Exception {
        return mapper.insertBatch(values.parallelStream().map(this::getPojo).toList());
    }

    @Override
    public Object selectId(ValueType value) throws Exception {
        return mapper.selectId(getPojo(value));
    }

    @Override
    public ValueType selectById(Object globalId) throws Exception {
        return getValue(mapper.selectById(globalId, getSelectContentFieldOnly()));
    }

    @Override
    public void updateById(ValueType value, Object globalId) throws Exception {
        mapper.updateById(getPojo(value), globalId);
    }

    @Override
    public void deleteById(Object globalId) throws Exception {
        mapper.deleteById(globalId);
    }
}
