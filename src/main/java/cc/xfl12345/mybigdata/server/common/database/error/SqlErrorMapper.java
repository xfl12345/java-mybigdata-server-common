package cc.xfl12345.mybigdata.server.common.database.error;


import cc.xfl12345.mybigdata.server.common.appconst.SimpleCoreTableCurdResult;

public interface SqlErrorMapper {
    SimpleCoreTableCurdResult getSimpleCoreTableCurdResult(String dbType, int vendorCode);
}
