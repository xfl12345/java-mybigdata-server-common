package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import cc.xfl12345.mybigdata.server.common.pojo.MbdId;

import java.util.List;

public interface MbdGroup<ID extends MbdId<?>> extends BaseMbdObject<ID> {
    String getName();

    void setName(String name);

    boolean isUniqueItems();

    void setUniqueItems(boolean unique);

    List<ID> getItems();

    void setItems(List<ID> items);
}
