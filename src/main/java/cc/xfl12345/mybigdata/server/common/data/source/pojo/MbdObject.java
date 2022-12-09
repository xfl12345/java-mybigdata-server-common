package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import cc.xfl12345.mybigdata.server.common.pojo.MbdId;

import java.util.Map;

public interface MbdObject<ID extends MbdId<?>> extends BaseMbdObject<ID> {
    MbdJsonSchema<ID> getReactiveJsonSchema();

    void setReactiveJsonSchema(MbdJsonSchema<ID> reactiveJsonSchema);

    String getName();

    void setName(String name);

    Map<String, Object> getMap();

    void setMap(Map<String, Object> map);
}
