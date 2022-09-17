package cc.xfl12345.mybigdata.server.common.data.condition;

import cc.xfl12345.mybigdata.server.common.data.condition.meta.FieldOperationValue;
import com.alibaba.fastjson2.JSONArray;

import java.util.ArrayList;

public class ValueConditionImpl implements ValueCondition {
    protected ArrayList<FieldOperationValue> arrayList = new ArrayList<>(6);

    @Override
    public ValueCondition op(String field, Op op, Object value) {
        FieldOperationValue fieldOperationValue = new FieldOperationValue();
        fieldOperationValue.setField(field);
        fieldOperationValue.setOperation(op);
        fieldOperationValue.setValue(value);
        arrayList.add(fieldOperationValue);
        return this;
    }

    @Override
    public JSONArray getJson() {
        return JSONArray.of(arrayList);
    }
}
