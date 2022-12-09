package cc.xfl12345.mybigdata.server.common.data.source.pojo;

import cc.xfl12345.mybigdata.server.common.pojo.MbdId;

import javax.annotation.PreDestroy;

public interface BaseMbdObject<ID extends MbdId<?>> {
    @PreDestroy
    default void destoryInstance() {
    }

    ID getGlobalId();
}
