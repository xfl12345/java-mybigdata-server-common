package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import java.util.Map;

public interface MbdObject extends MbdCollection {
    MbdJsonSchema getSchema();

    void setSchema(MbdJsonSchema reactiveJsonSchema);

    String getSchemaPath();

    void setSchemaPath(String schemaPath);

    Map<String, MbdId> getMap();

    void setMap(Map<String, MbdId> map);
}
