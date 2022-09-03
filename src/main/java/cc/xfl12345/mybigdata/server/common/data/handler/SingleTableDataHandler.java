package cc.xfl12345.mybigdata.server.common.data.handler;

import cc.xfl12345.mybigdata.server.common.data.source.DataSource;
import cc.xfl12345.mybigdata.server.common.pojo.IdAndValue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class SingleTableDataHandler<ValueType> extends BaseDataHandler {
    @Getter
    @Setter
    protected DataSource<ValueType> dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        if (dataSource == null) {
            throw new IllegalArgumentException(fieldCanNotBeNullMessageTemplate.formatted("dataSource"));
        }
    }

    @SuppressWarnings("unchecked")
    public SingleTableDataHandler() {
        insertAndReturnId.setDefaultAction(valueType -> dataSource.insertAndReturnId((ValueType) valueType));
        insert.setDefaultAction(valueType -> dataSource.insert((ValueType) valueType));
        insertBatch.setDefaultAction(param -> dataSource.insertBatch((List<ValueType>) param));
        selectId.setDefaultAction(valueType -> dataSource.selectId((ValueType) valueType));
        selectById.setDefaultAction(idType -> dataSource.selectById(idType));
        updateById.setDefaultAction(param -> {
            IdAndValue<ValueType> idAndValue = (IdAndValue<ValueType>) param;
            dataSource.updateById(idAndValue.value, idAndValue.id);
            return null;
        });
        deleteById.setDefaultAction(param -> {
            dataSource.deleteById(param);
            return null;
        });
    }
}
