package cc.xfl12345.mybigdata.server.common.data.handler;

import cc.xfl12345.mybigdata.server.common.data.source.DataSource;
import cc.xfl12345.mybigdata.server.common.pojo.IdAndValue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class SingleTableDataHandler<Value> extends BaseDataHandler {
    @Getter
    @Setter
    protected DataSource<Value> dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        if (dataSource == null) {
            throw new IllegalArgumentException(fieldCanNotBeNullMessageTemplate.formatted("dataSource"));
        }
    }

    @SuppressWarnings("unchecked")
    public SingleTableDataHandler() {
        insertAndReturnId.setDefaultAction(value -> dataSource.insertAndReturnId((Value) value));
        insert.setDefaultAction(value -> dataSource.insert((Value) value));
        insertBatch.setDefaultAction(param -> dataSource.insertBatch((List<Value>) param));
        selectId.setDefaultAction(value -> dataSource.selectId((Value) value));
        selectById.setDefaultAction(id -> dataSource.selectById(id));
        updateById.setDefaultAction(param -> {
            IdAndValue<Value> idAndValue = (IdAndValue<Value>) param;
            dataSource.updateById(idAndValue.value, idAndValue.id);
            return null;
        });
        deleteById.setDefaultAction(param -> {
            dataSource.deleteById(param);
            return null;
        });
    }

    public Object insertAndReturnId(Value value) throws Exception {
        return insertAndReturnId.execute(value);
    }

    public Object insert(Value value) throws Exception {
        return insert.execute(value);
    }

    public Object insertBatch(List<Value> values) throws Exception {
        return insertBatch.execute(values);
    }

    public Object selectId(Value value) throws Exception {
        return selectId.execute(value);
    }

    public Object selectById(Object globalId) throws Exception {
        return selectById.execute(globalId);
    }

    public void updateById(Value value, Object globalId) throws Exception {
        IdAndValue<Value> idAndValue = new IdAndValue<>();
        idAndValue.id = globalId;
        idAndValue.value = value;
        updateById.execute(idAndValue);
    }

    public void deleteById(Object globalId) throws Exception {
        deleteById.execute(globalId);
    }
}
