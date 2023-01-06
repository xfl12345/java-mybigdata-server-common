package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import com.networknt.schema.JsonSchema;
import lombok.Getter;
import lombok.Setter;

public class PlainMbdJsonSchema implements MbdJsonSchema {
    @Getter
    @Setter
    protected MbdId globalId;

    @Getter
    @Setter
    protected String name;

    @Getter
    @Setter
    protected JsonSchema jsonSchema;
}
