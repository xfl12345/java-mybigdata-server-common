package cc.xfl12345.mybigdata.server.common.web;

import cc.xfl12345.mybigdata.server.common.api.InstanceGenerator;
import cc.xfl12345.mybigdata.server.common.appconst.DefaultSingleton;
import cc.xfl12345.mybigdata.server.common.appconst.TableCurdResult;
import cc.xfl12345.mybigdata.server.common.appconst.api.result.JsonApiResult;
import cc.xfl12345.mybigdata.server.common.database.error.SqlErrorAnalyst;
import cc.xfl12345.mybigdata.server.common.database.error.TableDataException;
import cc.xfl12345.mybigdata.server.common.database.error.TableOperationException;
import cc.xfl12345.mybigdata.server.common.pojo.FieldNotNullChecker;
import cc.xfl12345.mybigdata.server.common.web.pojo.response.JsonApiResponseData;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class WebApiExecutor {
    @Getter
    @Setter
    protected FieldNotNullChecker fieldNotNullChecker = DefaultSingleton.FIELD_NOT_NULL_CHECKER;

    @Getter
    @Setter
    protected SqlErrorAnalyst sqlErrorAnalyst;

    @Getter
    @Setter
    protected InstanceGenerator<JsonApiResponseData> responseDataInstanceGenerator;

    @PostConstruct
    public void init() throws Exception {
        fieldNotNullChecker.check(responseDataInstanceGenerator, "responseDataInstanceGenerator");
    }

    public  <Param> JsonApiResponseData handle(
        HttpServletResponse httpServletResponse,
        Param param,
        Function<Param, Object> action) {
        return handleImpl(httpServletResponse, param, action);
    }

    public  <Param> JsonApiResponseData handle(
        HttpServletResponse httpServletResponse,
        Param param,
        Consumer<Param> action) {
        return handle(httpServletResponse, param, (theParam) -> {
            action.accept(theParam);
            return null;
        });
    }

    protected <Param> JsonApiResponseData handleImpl(
        HttpServletResponse httpServletResponse,
        Param param,
        Function<Param, Object> action) {
        JsonApiResponseData responseData = responseDataInstanceGenerator.getNewInstance();
        try {
            responseData.setData(action.apply(param));
            responseData.setApiResult(JsonApiResult.SUCCEED);
        } catch (Exception e) {
            onError(httpServletResponse, param, responseData, e);
        }

        return responseData;
    }

    protected <Param> void onError(
        HttpServletResponse httpServletResponse,
        Param param,
        JsonApiResponseData responseData,
        Exception exception) {
        if (exception instanceof TableOperationException e) {
            if (e.getAffectedRowsCount() == 0) {
                switch (e.getOperation()) {
                    case UPDATE, DELETE, RETRIEVE -> {
                        httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        responseData.setApiResult(JsonApiResult.FAILED_NOT_FOUND);
                    }
                    case CREATE -> {
                        httpServletResponse.setStatus(HttpServletResponse.SC_GONE);
                        responseData.setApiResult(JsonApiResult.FAILED);
                    }
                }
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                responseData.setApiResult(JsonApiResult.FAILED_FORBIDDEN);
                responseData.setMessage(e.getMessage());
            }
        } else if (exception instanceof TableDataException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            responseData.setApiResult(JsonApiResult.FAILED_FORBIDDEN);
            responseData.setMessage(e.getMessage());
        } else {
            TableCurdResult curdResult = sqlErrorAnalyst == null ? null : sqlErrorAnalyst.getTableCurdResult(exception);
            if (curdResult != null && !curdResult.equals(TableCurdResult.UNKNOWN_FAILED)) {
                responseData.setApiResult(JsonApiResult.FAILED);
                responseData.setMessage(curdResult.name());
            } else {
                log.warn(exception.getMessage(), exception);
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseData.setApiResult(JsonApiResult.OTHER_FAILED);
            }
        }
    }

}
