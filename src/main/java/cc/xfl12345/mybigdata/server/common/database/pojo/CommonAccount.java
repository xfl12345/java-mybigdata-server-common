package cc.xfl12345.mybigdata.server.common.database.pojo;

import java.io.Serial;
import java.io.Serializable;

/**
 * 表名：auth_account
 */
@lombok.Data
public class CommonAccount implements Cloneable, Serializable {
    /**
     * 账号ID
     */
    private Object accountId;

    /**
     * 账号密码的哈希值
     */
    private String passwordHash;

    /**
     * 账号密码的哈希值计算的佐料
     */
    private String passwordSalt;

    /**
     * 账号额外信息
     */
    private Object extraInfoId;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public CommonAccount clone() throws CloneNotSupportedException {
        return (CommonAccount) super.clone();
    }
}
