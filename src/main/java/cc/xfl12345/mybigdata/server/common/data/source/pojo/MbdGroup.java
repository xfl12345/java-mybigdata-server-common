package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import java.util.List;

public interface MbdGroup extends BaseMbdObject {
    String getName();

    void setName(String name);

    boolean isUniqueItems();

    void setUniqueItems(boolean unique);

    List<Object> getItems();

    void setItems(List<Object> items);
}
