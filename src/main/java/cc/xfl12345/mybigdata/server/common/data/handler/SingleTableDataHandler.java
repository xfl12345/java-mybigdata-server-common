package cc.xfl12345.mybigdata.server.common.data.handler;

import cc.xfl12345.mybigdata.server.common.data.source.DataSource;
import cc.xfl12345.mybigdata.server.common.pojo.IdAndValue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class SingleTableDataHandler<Value> extends BaseDataHandler<Value> {
    @Getter
    @Setter
    protected DataSource<Value> dataSource;

    @SuppressWarnings("unchecked")
    @Override
    public void init() throws Exception {
        super.init();
        if (dataSource == null) {
            throw new IllegalArgumentException(fieldCanNotBeNullMessageTemplate.formatted("dataSource"));
        }

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
}
