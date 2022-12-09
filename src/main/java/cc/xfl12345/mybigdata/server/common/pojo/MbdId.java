package cc.xfl12345.mybigdata.server.common.pojo;

import cc.xfl12345.mybigdata.server.common.utility.MyReflectUtils;
import org.bson.types.ObjectId;

public abstract class MbdId<ID> {
    private Class<ID> originType;

    protected ID idRawValue;

    protected Long theLongValue;

    protected String theStringValue;

    protected ObjectId theBsonObjectId;

    public MbdId(ID id) {
        idRawValue = id;
    }

    public Class<ID> getOriginType() {
        if (originType == null) {
            originType = MyReflectUtils.getGenericTypeFromRuntime(getClass(), 0);
        }

        return originType;
    }

    public Class<?> getFreeOriginType() {
        return getOriginType();
    }

    public ID getValue() {
        return idRawValue;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(Class<T> castType) {
        if (getOriginType().equals(castType)) {
            return (T) idRawValue;
        }

        if (String.class.equals(castType)) {
            return (T) idRawValue.toString();
        }

        return castType.cast(idRawValue);
    }

    public Long getLongValue() {
        if (theLongValue == null) {
            if (idRawValue instanceof String str) {
                theLongValue = Long.parseLong(str);
            } else {
                theLongValue = getValue(Long.class);
            }
        }

        return theLongValue;
    }

    public String getStringValue() {
        if (theStringValue == null) {
            theStringValue = getValue(String.class);
        }

        return theStringValue;
    }

    public ObjectId getBsonObjectId() {
        if (theBsonObjectId == null) {
            theBsonObjectId = new ObjectId(getStringValue());
        }

        return theBsonObjectId;
    }

    @Override
    public String toString() {
        return idRawValue == null ? "" : idRawValue.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (idRawValue == null) {
            return obj == null;
        }

        if (obj instanceof MbdId<?> mbdId) {
            return idRawValue.equals(mbdId.getValue());
        }

        return false;
    }

    public boolean equals(MbdId<ID> obj) {
        if (idRawValue == null) {
            return obj == null;
        }

        return idRawValue.equals(obj.getValue());
    }
}
