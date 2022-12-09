package cc.xfl12345.mybigdata.server.common.api;

import cc.xfl12345.mybigdata.server.common.appconst.AppDataType;
import cc.xfl12345.mybigdata.server.common.data.requirement.DataRequirementPack;
import cc.xfl12345.mybigdata.server.common.pojo.MbdId;

public interface IdViewer {
    AppDataType getDataTypeById(MbdId<?> id);

    Object getDataById(MbdId<?> id, DataRequirementPack dataRequirement);
}
