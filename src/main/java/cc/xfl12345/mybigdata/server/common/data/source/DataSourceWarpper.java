package cc.xfl12345.mybigdata.server.common.data.source;

public interface DataSourceWarpper<Value> {
    DataSource<Value> getRawImpl();
}
