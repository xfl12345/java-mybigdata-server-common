package cc.xfl12345.mybigdata.server.common.database.pojo;

import cc.xfl12345.mybigdata.server.common.pojo.MbdId;

import java.io.Serial;
import java.io.Serializable;

/**
 * 表名：table_schema_record
*/
@lombok.Data
public class CommonTableSchemaRecord implements Cloneable, Serializable {
    private MbdId<?> globalId;

    /**
     * 插表模型名称
     */
    private MbdId<?> schemaName;

    /**
     * json_schema 字段的长度
     */
    private Short contentLength;

    /**
     * 插表模型
     */
    private String jsonSchema;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public CommonTableSchemaRecord clone() throws CloneNotSupportedException {
        return (CommonTableSchemaRecord) super.clone();
    }
}
