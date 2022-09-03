package cc.xfl12345.mybigdata.server.common.database.mapper;

public interface ConditionSweet<ConditionType> {
    ConditionType getConditionWithSelectedFields(String... fields);

    void addFields2Condition(ConditionType condition, String... fields);

    ConditionType getConditionWithId(Object id);

    void addId2Condition(ConditionType condition, Object id);
}
