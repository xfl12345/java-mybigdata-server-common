package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import com.networknt.schema.JsonSchema;

public interface MbdJsonSchema extends BaseMbdObject {
    String getSchemaName();

    Short getContentLength();

    JsonSchema getJsonSchema();

    void setSchemaName(String schemaName);

    void setJsonSchema(JsonSchema jsonSchema);
}
