package cc.xfl12345.mybigdata.server.common.data.interceptor;


import cc.xfl12345.mybigdata.server.common.appconst.data.EnumDataSourceApiName;
import cc.xfl12345.mybigdata.server.common.pojo.TypeAndObject;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class DataInterceptorChain<T, R> {
    protected final ActionInterceptorChain<?> actionInterceptors;
    protected Function<T, R> defaultAction = (value) -> null;

    protected EnumDataSourceApiName name;

    protected Type paramType;

    protected Type returnType;

    protected boolean strictTypeCheck = true;

    @Getter
    protected CopyOnWriteArrayList<DataInterceptor<T, R>> interceptors = new CopyOnWriteArrayList<>();

    public DataInterceptorChain(
        ActionInterceptorChain<?> actionInterceptors,
        EnumDataSourceApiName name,
        Type paramType,
        Type returnType) {
        this.actionInterceptors = actionInterceptors;
        this.name = name;
        this.paramType = paramType;
        this.returnType = returnType;
    }

    public ActionInterceptorChain<?> getActionInterceptors() {
        return actionInterceptors;
    }

    public Function<T, R> getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(Function<T, R> defaultAction) {
        this.defaultAction = defaultAction;
    }

    public boolean isStrictTypeCheck() {
        return strictTypeCheck;
    }

    public void setStrictTypeCheck(boolean strictTypeCheck) {
        this.strictTypeCheck = strictTypeCheck;
    }

    public R execute(T param) {
        TypeAndObject actionInterceptorInput = new TypeAndObject(paramType, param, strictTypeCheck);
        boolean keepGoing = actionInterceptors.beforeAction(name, actionInterceptorInput);
        if (!keepGoing) {
            return null;
        }

        R actionOutputData = null;
        int lastIndex = 0;

        for (DataInterceptor<T, R> interceptor : interceptors) {
            keepGoing = interceptor.beforeAction(param);
            if (!keepGoing) {
                if (interceptor.isShouldBranch()) {
                    actionOutputData = interceptor.branchAction(param);
                    for (int i = 0; i <= lastIndex; i++) {
                        interceptors.get(i).afterAction(param, actionOutputData);
                    }
                }

                break;
            }

            lastIndex += 1;
        }

        if (keepGoing) {
            actionOutputData = defaultAction.apply(param);
            for (DataInterceptor<T, R> interceptor : interceptors) {
                interceptor.afterAction(param, actionOutputData);
            }
        }

        TypeAndObject actionInterceptorOutput = new TypeAndObject(returnType, actionOutputData, strictTypeCheck);
        return actionInterceptors.afterAction(name, actionInterceptorInput, actionInterceptorOutput) ?
            actionOutputData : null;
    }
}
