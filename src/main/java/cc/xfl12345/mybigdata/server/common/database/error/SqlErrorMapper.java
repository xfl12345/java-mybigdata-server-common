package cc.xfl12345.mybigdata.server.common.database.error;


import cc.xfl12345.mybigdata.server.common.appconst.TableCurdResult;

public interface SqlErrorMapper {
    TableCurdResult getSimpleCoreTableCurdResult(String dbType, int vendorCode);
}
