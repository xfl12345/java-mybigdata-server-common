package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import javax.annotation.PreDestroy;

public interface BaseMbdObject {
    @PreDestroy
    default void destoryInstance() {
    }

    Object getGlobalId();
}
