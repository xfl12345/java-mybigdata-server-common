package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import java.util.Map;

public interface MbdObject extends BaseMbdObject {
    MbdJsonSchema getReactiveJsonSchema();

    void setReactiveJsonSchema(MbdJsonSchema reactiveJsonSchema);

    String getName();

    void setName(String name);

    Map<String, Object> getMap();

    void setMap(Map<String, Object> map);
}
