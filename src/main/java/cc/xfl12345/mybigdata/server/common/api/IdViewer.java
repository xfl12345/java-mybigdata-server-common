package cc.xfl12345.mybigdata.server.common.api;

import cc.xfl12345.mybigdata.server.common.appconst.AppDataType;
import cc.xfl12345.mybigdata.server.common.data.requirement.DataRequirementPack;

public interface IdViewer {
    AppDataType getDataTypeById(Object id);

    Object getDataById(Object id, DataRequirementPack dataRequirement);
}
