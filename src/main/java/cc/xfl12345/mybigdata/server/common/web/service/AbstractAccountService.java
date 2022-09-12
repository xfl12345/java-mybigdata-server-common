package cc.xfl12345.mybigdata.server.common.web.service;


import cc.xfl12345.mybigdata.server.common.appconst.api.result.LoginApiResult;
import cc.xfl12345.mybigdata.server.common.appconst.api.result.LogoutApiResult;
import cc.xfl12345.mybigdata.server.common.appconst.field.AccountField;
import cc.xfl12345.mybigdata.server.common.database.error.SqlErrorAnalyst;
import cc.xfl12345.mybigdata.server.common.generator.RandomCodeGenerator;
import cc.xfl12345.mybigdata.server.common.web.pojo.response.JsonApiResponseData;
import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.InitializingBean;


/**
 * (AuthAccount)表服务抽象类
 *
 * @author makejava
 * @since 2021-04-19 16:00:51
 */
public abstract class AbstractAccountService implements InitializingBean {
    @Getter
    @Setter
    protected SqlErrorAnalyst sqlErrorAnalyst;
    @Getter
    @Setter
    protected RandomCodeGenerator randomCodeGenerator;

    @Getter
    @Setter
    protected String jsonApiVersion = "1";


    @Override
    public void afterPropertiesSet() throws Exception {
    }

    /**
     * 检查当前会话是否已登录
     *
     * @return 是否已登录
     */
    public abstract boolean checkIsLoggedIn();


    public abstract LoginApiResult login(String username, String passwordHash);


    /**
     * 踢人下线，根据账号id 和 设备类型
     * 当对方再次访问系统时，会抛出NotLoginException异常，场景值=-5
     *
     * @param loginId – 账号id
     * @param device  – 设备类型 (填null代表踢出所有设备类型)
     * @return 是否成功
     */
    public abstract LogoutApiResult kickout(Object loginId, String device);


    public abstract LogoutApiResult logout();


    public abstract JsonApiResponseData resetPassword(String passwordHash);

    /**
     * 注册账号
     *
     * @param registerInfo 注册信息
     * @return 返回注册结果
     */
    public abstract JsonApiResponseData register(JSONObject registerInfo);

    public String passwordHashEncrypt(String passwordHash, String salt) {
        return DigestUtils.sha512Hex(passwordHash + salt);
    }

    public String generatePasswordHash(String passwordStr, String salt) {
        return passwordHashEncrypt(DigestUtils.sha512Hex(passwordStr), salt);
    }

    public String generatePasswordSalt() {
        return randomCodeGenerator.generate(AccountField.PASSWORD_SALT_LENGTH);
    }
}
