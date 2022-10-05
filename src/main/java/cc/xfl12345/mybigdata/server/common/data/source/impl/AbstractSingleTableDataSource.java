package cc.xfl12345.mybigdata.server.common.data.source.impl;


import cc.xfl12345.mybigdata.server.common.appconst.AppConst;
import cc.xfl12345.mybigdata.server.common.data.source.DataSource;
import cc.xfl12345.mybigdata.server.common.database.mapper.TableNoConditionMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public abstract class AbstractSingleTableDataSource<Value, Pojo> implements DataSource<Value>, InitializingBean {
    @Getter
    @Setter
    protected String fieldCanNotBeNullMessageTemplate = AppConst.FIELD_CAN_NOT_BE_NULL_MESSAGE_TEMPLATE;

    @Getter
    @Setter
    protected TableNoConditionMapper<Pojo> mapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (mapper == null) {
            throw new IllegalArgumentException(fieldCanNotBeNullMessageTemplate.formatted("mapper"));
        }
    }

    protected abstract String[] getSelectContentFieldOnly();

    protected abstract Value getValue(Pojo pojo);

    protected abstract Pojo getPojo(Value value);

    @Override
    public Object insertAndReturnId(Value value) {
        return mapper.insertAndReturnId(getPojo(value));
    }

    @Override
    public long insert(Value value) {
        return mapper.insert(getPojo(value));
    }

    @Override
    public long insertBatch(List<Value> values) {
        return mapper.insertBatch(values.parallelStream().map(this::getPojo).toList());
    }

    @Override
    public Object selectId(Value value) {
        return mapper.selectId(getPojo(value));
    }

    @Override
    public Value selectById(Object globalId) {
        return getValue(mapper.selectById(globalId, getSelectContentFieldOnly()));
    }

    @Override
    public void updateById(Value value, Object globalId) {
        mapper.updateById(getPojo(value), globalId);
    }

    @Override
    public void deleteById(Object globalId) {
        mapper.deleteById(globalId);
    }
}
