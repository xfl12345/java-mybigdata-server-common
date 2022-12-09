package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import cc.xfl12345.mybigdata.server.common.pojo.MbdId;
import com.networknt.schema.JsonSchema;

public interface MbdJsonSchema<ID extends MbdId<?>> extends BaseMbdObject<ID> {
    String getSchemaName();

    Short getContentLength();

    JsonSchema getJsonSchema();

    void setSchemaName(String schemaName);

    void setJsonSchema(JsonSchema jsonSchema);
}
