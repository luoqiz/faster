package org.wolf.common.entity.http;

public class ApiServiceBase {

    public static HttpResponseBase error() {
        return new HttpResponseBase(false, ResponseCodeConstant.HTTP_RES_CODE_500,
                ResponseCodeConstant.HTTP_RES_CODE_500_VALUE, null);
    }

    public static HttpResponseBase error(String code, String msg, Object data) {
        return new HttpResponseBase(false, code, msg, data);
    }

    public static HttpResponseBase error(String code, String msg) {
        return new HttpResponseBase(false, code, msg, null);
    }

    public static HttpResponseBase error(String msg, Object data) {
        return new HttpResponseBase(false, ResponseCodeConstant.HTTP_RES_CODE_500, msg, data);
    }

    public static HttpResponseBase success(String msg, Object data) {
        return new HttpResponseBase(ResponseCodeConstant.HTTP_RES_CODE_200, msg, data);
    }

    public static HttpResponseBase success(String code, String msg, Object data) {
        return new HttpResponseBase(code, msg, data);
    }

    public static HttpResponseBase success(Object data) {
        return new HttpResponseBase(ResponseCodeConstant.HTTP_RES_CODE_200,
                ResponseCodeConstant.HTTP_RES_CODE_200_VALUE, data);
    }

    public static HttpResponseBase result(boolean success, String code, String msg, Object data) {
        return new HttpResponseBase(success, code, msg, data);
    }
}
