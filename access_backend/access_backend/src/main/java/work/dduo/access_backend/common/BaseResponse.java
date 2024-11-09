package work.dduo.access_backend.common;

import lombok.Data;

import java.io.Serializable;

/*
 * 通用返回类
 *
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private String message;

    private T data;


    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应
    public BaseResponse(int code, T data) {
        this(code, "success", data);
    }

    // 失败响应
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage(), null);
    }
}
