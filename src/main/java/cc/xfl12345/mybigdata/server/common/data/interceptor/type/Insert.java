package cc.xfl12345.mybigdata.server.common.data.interceptor.type;

import cc.xfl12345.mybigdata.server.common.appconst.CURD;

public class Insert implements CurdTypeGetter {
    @Override
    public CURD getCurdType() {
        return CURD.CREATE;
    }
}
