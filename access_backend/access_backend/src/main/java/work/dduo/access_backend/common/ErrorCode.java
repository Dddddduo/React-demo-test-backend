package work.dduo.access_backend.common;

/**
 * 自定义错误码
 *
 */
public enum ErrorCode {

    SUCCESS(0, "success"),
    PARAMS_ERROR(40000, "请求参数错误"),
    ADD_PASSWORD_ERROR(1423,"请求参数有误, 原因: 两次密码不正确"),
    ADD_MAIL_ERROR(1422,"请求参数有误, 原因: 邮箱 已经存在"),
    NOT_FOUND_ERROR(40400, "请求数据在数据库中不存在"),
    DATABASE_INSERT_ERROR(1001, "未成功改动数据库");
    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
