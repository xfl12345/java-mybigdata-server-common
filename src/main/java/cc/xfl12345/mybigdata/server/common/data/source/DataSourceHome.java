package cc.xfl12345.mybigdata.server.common.data.source;

import cc.xfl12345.mybigdata.server.common.appconst.AppDataType;
import cc.xfl12345.mybigdata.server.common.appconst.DefaultSingleton;
import cc.xfl12345.mybigdata.server.common.data.requirement.DataRequirementPack;
import cc.xfl12345.mybigdata.server.common.data.source.pojo.BaseMbdObject;
import cc.xfl12345.mybigdata.server.common.data.source.pojo.MbdId;
import cc.xfl12345.mybigdata.server.common.pojo.FieldNotNullChecker;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;

public abstract class DataSourceHome {
    @Getter
    @Setter
    protected FieldNotNullChecker fieldNotNullChecker = DefaultSingleton.FIELD_NOT_NULL_CHECKER;

    @Getter
    @Setter
    protected DataSourceBag dataSourceBag;

    @PostConstruct
    public void init() throws Exception {
        fieldNotNullChecker.check(dataSourceBag, "dataSourceBag");
    }

    public abstract AppDataType getDataTypeById(MbdId id);

    public abstract MbdId getIdByData(BaseMbdObject data);

    public abstract BaseMbdObject getDataById(MbdId id, DataRequirementPack dataRequirement);

    public abstract MbdId setData(BaseMbdObject data);
}
