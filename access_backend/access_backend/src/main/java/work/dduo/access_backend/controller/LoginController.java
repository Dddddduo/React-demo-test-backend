package work.dduo.access_backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.dduo.access_backend.common.BaseResponse;
import work.dduo.access_backend.model.dto.LoginRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/v1")
@Slf4j
@CrossOrigin
public class LoginController {

    @PostMapping("/admin-login")
    public BaseResponse<Map<String, Object>> login(@RequestBody LoginRequest request) {
        // 登录模拟请求
        String email = request.getEmail();
        String password = request.getPassword();

        Map<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("name", "Anthony");
        user.put("en_name", "Anthony");
        user.put("email", "king19800105@163.com");
        user.put("mobile", "13888888888");
        user.put("employee_id", "0001");
        user.put("remark", "Super Admin");
        user.put("status", 1);
        user.put("deleted_at", null);
        user.put("created_at", "2024-08-24T13:47:32.000000Z");
        user.put("updated_at", "2024-08-24T13:47:35.000000Z");

        Map<String, Object> successData = new HashMap<>();
        successData.put("user", user);
        successData.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...");
        successData.put("type", "Bearer");
        successData.put("expires", 3600);

        BaseResponse<Map<String, Object>> successResponse = new BaseResponse<>(0, "success", successData);

        // 失败响应示例
        Map<String, Object> errorData = new HashMap<>();
        errorData.put("email", "邮箱 不能为空。");

        BaseResponse<Map<String, Object>> errorResponse = new BaseResponse<>(1422, "请求参数有误, 原因: 邮箱 不能为空。", errorData);

        System.out.println(successResponse);
        System.out.println(errorResponse);

        if(email.equals("king19800105@163.com")&&password.equals("123456")){
            return successResponse;
        }else {
            return errorResponse;
        }

    }

}
