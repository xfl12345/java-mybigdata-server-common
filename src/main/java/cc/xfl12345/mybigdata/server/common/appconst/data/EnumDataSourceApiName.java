package cc.xfl12345.mybigdata.server.common.appconst.data;

public enum EnumDataSourceApiName {
    insert4IdOrGetId,
    insertAndReturnId,
    insert,
    insertBatch,
    selectId,
    selectById,
    selectBatchById,
    update,
    updateById,
    delete,
    deleteById,
    deleteBatchById;


    // insert4IdOrGetId(null, true),
    // insertAndReturnId(CURD.CREATE, false),
    // insert(CURD.CREATE, false),
    // insertBatch(CURD.CREATE, false),
    // selectId(CURD.RETRIEVE, false),
    // selectById(CURD.RETRIEVE, false),
    // selectBatchById(CURD.RETRIEVE, false),
    // update(CURD.UPDATE, false),
    // updateById(CURD.UPDATE, false),
    // delete(CURD.DELETE, false),
    // deleteById(CURD.DELETE, false),
    // deleteBatchById(CURD.DELETE, false);
    //
    // private final CURD curdType;
    //
    // private final boolean composit;
    //
    // EnumDataSourceApiName(CURD curdType, boolean composit) {
    //     this.curdType = curdType;
    //     this.composit = composit;
    // }
    //
    // public CURD getCurdType() {
    //     return curdType;
    // }
    //
    // public boolean isComposit() {
    //     return composit;
    // }
}
