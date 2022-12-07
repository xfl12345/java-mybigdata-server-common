package cc.xfl12345.mybigdata.server.common.data.source;

import cc.xfl12345.mybigdata.server.common.data.source.pojo.MbdGroup;
import cc.xfl12345.mybigdata.server.common.pojo.ReactiveMode;

public interface GroupTypeSource extends DataSource<MbdGroup> {
    MbdGroup getReactiveMbdGroup(Object globalId, ReactiveMode mode);
}
