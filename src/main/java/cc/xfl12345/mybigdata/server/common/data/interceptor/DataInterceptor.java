package cc.xfl12345.mybigdata.server.common.data.interceptor;


import cc.xfl12345.mybigdata.server.common.appconst.data.EnumDataSourceApiName;

public class DataInterceptor<T, R> {
    protected EnumDataSourceApiName name;

    public EnumDataSourceApiName getName() {
        return name;
    }

    public DataInterceptor(EnumDataSourceApiName name) {
        this.name = name;
    }

    protected boolean shouldBranch = false;

    public boolean isShouldBranch() {
        return shouldBranch;
    }

    public void setShouldBranch(boolean shouldBranch) {
        this.shouldBranch = shouldBranch;
    }

    /**
     * 在执行操作之前，先处理一些事情。如果返回 false。则不继续冒泡，并判断是否执行分支操作。
     */
    public boolean beforeAction(T param) {
        return true;
    }

    /**
     * 分支操作。
     */
    public R branchAction(T param) {
        return null;
    }

    /**
     * 在操作完成之后再做点事情。
     *
     * @param actionInputData  传入参数
     * @param actionOutputData 返回值
     */
    public void afterAction(T actionInputData, R actionOutputData) {
    }
}
