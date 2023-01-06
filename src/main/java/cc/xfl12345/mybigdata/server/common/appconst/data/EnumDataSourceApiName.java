package cc.xfl12345.mybigdata.server.common.appconst.data;

import java.util.HashMap;
import java.util.Map;

public enum EnumDataSourceApiName {
    selectIdOrInsert4Id,
    insertAndReturnId,
    insert,
    insertBatch,
    selectId,
    selectById,
    selectBatchId,
    selectBatchById,
    update,
    updateById,
    delete,
    deleteById,
    deleteBatchById;

    private static final Map<String, EnumDataSourceApiName> nameMap;

    static {
        nameMap = new HashMap<>(EnumDataSourceApiName.values().length);
        for (EnumDataSourceApiName item : EnumDataSourceApiName.values()) {
            nameMap.put(item.name(), item);
        }
    }

    public static EnumDataSourceApiName getByName(String name) {
        return nameMap.get(name);
    }
}
