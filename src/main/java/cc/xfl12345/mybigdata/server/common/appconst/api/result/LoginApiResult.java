package cc.xfl12345.mybigdata.server.common.appconst.api.result;

public enum LoginApiResult {
    SUCCEED("登录成功", 0),
    FAILED("用户名或密码错误", 1),
    OTHER_FAILED("未知错误", 2),
    DUPLICATE_KEY("账号已被他人登录", 3),
    FAILED_ALREADY_LOGINED("已登录，请先注销", 4);
    private final String name;
    private final int num;

    LoginApiResult(String str, int num) {
        this.name = str;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }
}
