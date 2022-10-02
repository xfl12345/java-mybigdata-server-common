package cc.xfl12345.mybigdata.server.common.pojo;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@lombok.Data
public class CommonGlobalDataRecord implements Cloneable, Serializable {
    private Object id;

    private String uuid;

    private Date createTime;

    private Date updateTime;

    private Long modifiedCount;

    private Object tableName;

    private Object description;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public CommonGlobalDataRecord clone() throws CloneNotSupportedException {
        return (CommonGlobalDataRecord) super.clone();
    }
}
