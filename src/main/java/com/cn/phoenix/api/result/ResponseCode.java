package com.cn.phoenix.api.result;


public enum ResponseCode {

    /* (code,message,httpCode)
     *
     * pay attention to :
     * the message must  end with '.'
     * */


    SUCCESS(200, "Success"),

    //service error , 500 begin
    SERVICE_ERROR(200, "The request service internal process error."),
    //server end

    //client error, 400 begin
    USER_IS_NO(400, "用户名不存在!"),

    USER_PASSWORD_NO(400, "用户或密码错误!"),

    PARAMETER_LACK(400, "缺少必填参数:"),
    PARAMETER_MUST_ONE(400, "必须有一个参数存在:"),

    PARAMETER_INVALID(400, "参数无效:"),
    DATA_REPEAT(400, "数据重复:"),
    DATA_NULL(400, "数据不存在:"),
    DATA_LINKED(400, "存在关联数据:"),
    DATA_ERROR(400,"数据不合法:"),
    DATA_CUSTOM(400,""),




    UNKNOWN_OPERATION(400, "The request operation is unsupported."),

    PARAMETER_MISS(400, "The request parameter is miss."),

    STATE_INCORRECT(400, "The request resource in an incorrect state for the request."),

    RESOURCE_NOTFOUND(400, "The request resource is not exist."),

    RESOURCE_INUSE(400, "The request resource is in use."),

    RESOURCE_DUPLICATE(400, "The request resource is duplicate."),

    RESOURCE_LIMITEXCEEDED(400, "The request resource is limitExceeded."),

    RESOURCE_RELATIONVIOLATE(400, "The request resource has relation resources,please remove the relations first."),

    FORBIDDEN_AUTHFAILURE(401, "The request is unauthorized or signature does not match, please check and try again."),

    FORBIDDEN_NOPERMISSION(403, "The request has no permissions."),

    //    FORBIDDEN_SIGNATURE_DOESNOT_MATCH("Forbidden.SignatureDoesNotMatch", "403", "The request signature does not match,please refer to the api reference."),
    FORBIDDEN_LOCKED(403, "The request account is locked."),

    TEN_PARAMETER_INVALID(4000, "The request parameter is invalid.");

    //client end

    public final int code;
    public final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

//    @Override
//    public String toString() {
//        return  code;
//    }

    public static ResponseCode toEnum(String name) {
        for (ResponseCode res : ResponseCode.values()) {
            if (res.toString().equalsIgnoreCase(name)) {
                return res;
            }
        }
        return null;
    }
}
