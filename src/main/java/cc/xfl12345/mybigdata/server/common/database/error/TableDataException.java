package cc.xfl12345.mybigdata.server.common.database.error;

import cc.xfl12345.mybigdata.server.common.pojo.MbdId;

public class TableDataException extends RuntimeException {
    public TableDataException() {
    }

    public TableDataException(String message) {
        super(message);
    }

    public TableDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableDataException(Throwable cause) {
        super(cause);
    }

    public TableDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected String tableName;

    protected MbdId<?>[] globalIds;

    protected String[] uuids;

    public TableDataException(String message, MbdId<?>[] globalIds, String tableName) {
        super(message);
        this.globalIds = globalIds;
        this.tableName = tableName;
    }

    public TableDataException(String message, MbdId<?>[] globalIds, String[] uuids, String tableName) {
        super(message);
        this.globalIds = globalIds;
        this.uuids = uuids;
        this.tableName = tableName;
    }

    public Object[] getGlobalIds() {
        return globalIds;
    }

    public String[] getUuids() {
        return uuids;
    }

    public String getTableName() {
        return tableName;
    }
}
