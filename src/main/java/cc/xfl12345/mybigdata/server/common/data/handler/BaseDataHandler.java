package cc.xfl12345.mybigdata.server.common.data.handler;


import cc.xfl12345.mybigdata.server.common.appconst.AppConst;
import cc.xfl12345.mybigdata.server.common.appconst.CURD;
import cc.xfl12345.mybigdata.server.common.data.interceptor.DataHandlerInterceptor;
import cc.xfl12345.mybigdata.server.common.data.interceptor.DataHandlerInterceptorManager;
import cc.xfl12345.mybigdata.server.common.pojo.IdAndValue;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BaseDataHandler<Value> implements InitializingBean {
    protected String fieldCanNotBeNullMessageTemplate = AppConst.FIELD_CAN_NOT_BE_NULL_MESSAGE_TEMPLATE;

    @Override
    public void afterPropertiesSet() throws Exception {
    }


    public BaseDataHandler() {
        inserts = new CopyOnWriteArrayList<>(new DataHandlerInterceptorManager[] {
            insert, insertAndReturnId, insertBatch
        });
        selects = new CopyOnWriteArrayList<>(new DataHandlerInterceptorManager[] {
            selectId, selectById
        });
        updates = new CopyOnWriteArrayList<>(new DataHandlerInterceptorManager[] {
            updateById
        });
        deletes = new CopyOnWriteArrayList<>(new DataHandlerInterceptorManager[] {
            deleteById
        });
    }

    public CopyOnWriteArrayList<DataHandlerInterceptorManager> getManagers(CURD interceptorType) {
        CopyOnWriteArrayList<DataHandlerInterceptorManager> managers = null;
        switch (interceptorType) {
            case CREATE -> managers = inserts;
            case UPDATE -> managers = updates;
            case RETRIEVE -> managers = selects;
            case DELETE -> managers = deletes;
        }

        return managers;
    }

    /**
     * 统一添加拦截器
     * @param indexOfInterceptor 拦截器下标
     * @param interceptorType 应用拦截器到哪个操作类型
     * @param interceptor 拦截器
     */
    public void addInterceptor(int indexOfInterceptor, CURD interceptorType, DataHandlerInterceptor interceptor) {
        getManagers(interceptorType).forEach(item -> item.add(indexOfInterceptor, interceptor));
    }

    /**
     * 统一更新拦截器
     * @param indexOfInterceptor 拦截器下标
     * @param interceptorType 应用拦截器到哪个操作类型
     * @param interceptor 拦截器
     */
    public void updateInterceptor(int indexOfInterceptor, CURD interceptorType, DataHandlerInterceptor interceptor) {
        getManagers(interceptorType).forEach(item -> item.set(indexOfInterceptor, interceptor));
    }

    /**
     * 统一删除拦截器
     * @param indexOfInterceptor 拦截器下标
     * @param interceptorType 应用拦截器到哪个操作类型
     */
    public void removeInterceptor(int indexOfInterceptor, CURD interceptorType) {
        getManagers(interceptorType).forEach(item -> item.remove(indexOfInterceptor));
    }

    protected final CopyOnWriteArrayList<DataHandlerInterceptorManager> inserts;
    protected final CopyOnWriteArrayList<DataHandlerInterceptorManager> selects;
    protected final CopyOnWriteArrayList<DataHandlerInterceptorManager> updates;
    protected final CopyOnWriteArrayList<DataHandlerInterceptorManager> deletes;


    protected final DataHandlerInterceptorManager insertAndReturnId =
        new DataHandlerInterceptorManager();

    protected final DataHandlerInterceptorManager insert =
        new DataHandlerInterceptorManager();

    protected final DataHandlerInterceptorManager insertBatch =
        new DataHandlerInterceptorManager();

    protected final DataHandlerInterceptorManager selectId =
        new DataHandlerInterceptorManager();

    protected final DataHandlerInterceptorManager selectById =
        new DataHandlerInterceptorManager();

    protected final DataHandlerInterceptorManager updateById =
        new DataHandlerInterceptorManager();

    protected final DataHandlerInterceptorManager deleteById =
        new DataHandlerInterceptorManager();



    public Object insertAndReturnId(Value value) {
        return insertAndReturnId.execute(value);
    }

    public Object insert(Value value) {
        return insert.execute(value);
    }

    public Object insertBatch(List<Value> values) {
        return insertBatch.execute(values);
    }

    public Object selectId(Value value) {
        return selectId.execute(value);
    }

    public Object selectById(Object globalId) {
        return selectById.execute(globalId);
    }

    public void updateById(Value value, Object globalId) {
        IdAndValue<Value> idAndValue = new IdAndValue<>();
        idAndValue.id = globalId;
        idAndValue.value = value;
        updateById.execute(idAndValue);
    }

    public void deleteById(Object globalId) {
        deleteById.execute(globalId);
    }
}
