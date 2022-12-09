package cc.xfl12345.mybigdata.server.common.data.source;

import cc.xfl12345.mybigdata.server.common.data.source.pojo.CommonMbdId;
import cc.xfl12345.mybigdata.server.common.data.source.pojo.CommonMdbGroup;
import cc.xfl12345.mybigdata.server.common.data.source.pojo.MbdGroup;
import cc.xfl12345.mybigdata.server.common.pojo.ReactiveMode;

public interface GroupTypeSource extends DataSource<CommonMdbGroup> {
    MbdGroup<?> getReactiveMbdGroup(CommonMbdId globalId, ReactiveMode mode);
}
