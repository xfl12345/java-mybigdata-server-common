package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PlainMdbGroup implements MbdGroup {
    @Getter
    @Setter
    protected Object globalId;

    @Getter
    @Setter
    protected String name;

    @Getter
    @Setter
    protected boolean uniqueItems;

    @Getter
    @Setter
    protected List<Object> items;
}
