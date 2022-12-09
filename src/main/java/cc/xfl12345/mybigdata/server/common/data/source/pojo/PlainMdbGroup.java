package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import cc.xfl12345.mybigdata.server.common.pojo.MbdId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PlainMdbGroup<ID extends MbdId<?>> implements MbdGroup<ID> {
    @Getter
    @Setter
    protected ID globalId;

    @Getter
    @Setter
    protected String name;

    @Getter
    @Setter
    protected boolean uniqueItems;

    @Getter
    @Setter
    protected List<ID> items;
}
