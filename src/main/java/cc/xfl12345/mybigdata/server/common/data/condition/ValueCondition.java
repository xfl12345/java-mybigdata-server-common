package cc.xfl12345.mybigdata.server.common.data.condition;

import com.alibaba.fastjson2.JSONArray;

public interface ValueCondition {
    public ValueCondition op(String field, Op op, Object value);

    public JSONArray getJson();
}
