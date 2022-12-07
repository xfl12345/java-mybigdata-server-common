package cc.xfl12345.mybigdata.server.common.data.interceptor;


import cc.xfl12345.mybigdata.server.common.appconst.CURD;
import cc.xfl12345.mybigdata.server.common.appconst.data.EnumDataSourceApiName;
import cc.xfl12345.mybigdata.server.common.data.interceptor.type.CurdTypeGetter;
import cc.xfl12345.mybigdata.server.common.pojo.TypeAndObject;
import lombok.Getter;

import java.util.concurrent.CopyOnWriteArrayList;

public class ActionInterceptorChain<T extends CurdTypeGetter> {
    protected T curdTypeGetter;

    @Getter
    protected CopyOnWriteArrayList<ActionInterceptor<T>> actionInterceptors = new CopyOnWriteArrayList<>();

    public ActionInterceptorChain(T curdTypeGetter) {
        this.curdTypeGetter = curdTypeGetter;
    }

    public CURD getCurdType() {
        return curdTypeGetter == null ? null : curdTypeGetter.getCurdType();
    }

    /**
     * 在执行操作之前，先处理一些事情。如果返回 false。则表示终止此次操作。
     */
    public boolean beforeAction(EnumDataSourceApiName apiName, TypeAndObject param) {
        for (ActionInterceptor<T> interceptor : actionInterceptors) {
            if (!interceptor.beforeAction(apiName, param)) {
                return false;
            }
        }

        return true;
    }


    /**
     * 在操作完成之后再做点事情。
     *
     * @param actionInputData  传入参数
     * @param actionOutputData 返回值
     * @return 如果返回 false 则表示拦截此操作的返回值，如果返回 true 则表示正常返回 actionOutputData
     */
    public boolean afterAction(EnumDataSourceApiName apiName, TypeAndObject actionInputData, TypeAndObject actionOutputData) {
        for (ActionInterceptor<T> interceptor : actionInterceptors) {
            if (!interceptor.afterAction(apiName, actionInputData, actionOutputData)) {
                return false;
            }
        }

        return true;
    }
}
