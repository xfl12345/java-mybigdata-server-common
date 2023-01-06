package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import java.util.List;

public interface MbdGroup extends MbdCollection {
    boolean isUniqueItems();

    void setUniqueItems(boolean unique);

    List<MbdId> getItems();

    void setItems(List<MbdId> items);
}
