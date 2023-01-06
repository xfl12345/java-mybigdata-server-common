package cc.xfl12345.mybigdata.server.common.data.source;

import cc.xfl12345.mybigdata.server.common.appconst.AppDataType;
import cc.xfl12345.mybigdata.server.common.data.source.pojo.MbdObject;

public interface ObjectTypeSource extends DataSource<MbdObject> {
    @Override
    default AppDataType getDataEnumType() {
        return AppDataType.Object;
    }
}
