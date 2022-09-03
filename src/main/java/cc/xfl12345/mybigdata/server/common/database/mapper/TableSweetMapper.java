package cc.xfl12345.mybigdata.server.common.database.mapper;

public interface TableSweetMapper<ValueType, ConditionType>
    extends TableMapper<ValueType, ConditionType>, ConditionSweet<ConditionType> {
}
