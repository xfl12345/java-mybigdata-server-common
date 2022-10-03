package cc.xfl12345.mybigdata.server.common.database.mapper;

public interface ConditionSweet<Condition> {
    Condition getConditionWithSelectedFields(String... fields);

    void addFields2Condition(Condition condition, String... fields);

    Condition getConditionWithId(Object id);

    void addId2Condition(Condition condition, Object id);
}
