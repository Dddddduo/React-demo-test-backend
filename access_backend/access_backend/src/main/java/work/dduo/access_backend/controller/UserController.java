package work.dduo.access_backend.controller;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import work.dduo.access_backend.common.BaseResponse;
import work.dduo.access_backend.common.ErrorData;
import work.dduo.access_backend.model.dto.UserQueryRequest;
import work.dduo.access_backend.utils.ResultUtils;
import work.dduo.access_backend.exception.BusinessException;
import work.dduo.access_backend.model.dto.UserAddRequest;
import work.dduo.access_backend.model.dto.UserDeleteRequest;
import work.dduo.access_backend.model.dto.UserUpdateRequest;
import work.dduo.access_backend.model.entity.User;
import work.dduo.access_backend.service.UserService;

import static work.dduo.access_backend.common.ErrorCode.*;

/**
 * 题目用户接口
 */

@RestController
@RequestMapping("/api/backend/v1")
@Slf4j
@CrossOrigin
public class  UserController {

    @Resource
    private UserService userService;

    /**
     * 用户列表
     *
     * @param userQueryRequest
     * @return
     */
    @PostMapping("/users")
    public BaseResponse<Page<User>> listUserByPage(@RequestBody UserQueryRequest userQueryRequest) {

//        System.out.println("123");
//        System.out.println(userQueryRequest);

         long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        System.out.println( ResultUtils.success(userPage));
        return ResultUtils.success(userPage);
    }

    /**
     * 添加用户
     *
     * @param userAddRequest
     * @return
     */
    @PostMapping("/create-user")
    public BaseResponse<Object> addUser(@RequestBody UserAddRequest userAddRequest) {

        // 参数为空
        if (userAddRequest == null) {
            throw new BusinessException(PARAMS_ERROR, "前端传入参数为空");
        }

        User user = new User();

        // 复制
        BeanUtils.copyProperties(userAddRequest, user);

        // 得到字段
        String password = userAddRequest.getPassword();
        String passwordConfirmation = userAddRequest.getPasswordConfirmation();
        String status = userAddRequest.getStatus();
        String date_string = userAddRequest.getDate_string();



//         参数校验
//        if (date_string == null || status == null) {
//            throw new BusinessException(PARAMS_ERROR, "前端传入的日期和状态有误");
//        }

        // 存储错误信息
        ErrorData errorData = new ErrorData();

        // 密码校验
        if (!password.equals(passwordConfirmation)) {
            errorData.addError("password", "密码 两次输入不一致。");
            return new BaseResponse<>(1422, "请求参数有误, 原因: 密码两次输入不一致。", errorData);
        }

        // 设置为未删除
        user.setIsDelete(0);

        // 到数据库中存数据
        boolean result = userService.save(user);

        // 邮箱校验
        if (!result) {
            errorData.addError("email", "邮箱 已经存在。");
            return new BaseResponse<>(1422, "请求参数有误, 邮箱 已经存在。", errorData);
        } else {
            return ResultUtils.success(user);
        }
    }

    /**
     * 修改用户
     *
     * @param userUpdateRequest
     * @return
     */
    @PostMapping("/update-user")
    public BaseResponse<Object> updateQuestion(@RequestBody UserUpdateRequest userUpdateRequest) {

        // 参数校验
        if (userUpdateRequest == null || userUpdateRequest.getId() <= 0) {
            throw new BusinessException(PARAMS_ERROR, "请求参数无效");
        }

        long id = userUpdateRequest.getId();

        // 第一步：删除用户
        UserDeleteRequest userDeleteRequest = new UserDeleteRequest();
        userDeleteRequest.setId(id);

        BaseResponse<Object> deleteResponse = deleteUser(userDeleteRequest);
        // 如果删除失败，直接返回删除的响应
        if (deleteResponse.getCode() !=0 ) {
            return deleteResponse;
        }

        // 第二步：添加新用户
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);

        // 设置为未删除
        user.setIsDelete(0);

        boolean addResult = userService.save(user);
        if (!addResult) {
            // 如果添加失败，返回相应的错误信息
            throw new BusinessException(NOT_FOUND_ERROR, "更新用户失败，可能是邮箱已存在。");
        }

        return ResultUtils.success(user);

    }

    /**
     * 删除用户
     *
     * @param userDeleteRequest
     * @return
     */
    @PostMapping("/delete-user")
    public BaseResponse<Object> deleteUser(@RequestBody UserDeleteRequest userDeleteRequest ) {
        if (userDeleteRequest == null || userDeleteRequest.getId() <= 0) {
            throw new BusinessException(PARAMS_ERROR);
        }

        // 获取id
        long id = userDeleteRequest.getId();


        // 判断数据库中是否存在
        User oldUser = userService.getById(id);
        if(oldUser == null){
            throw new BusinessException(NOT_FOUND_ERROR);
        }else {
            boolean b = userService.removeById(id);
            return ResultUtils.success(id);
        }

    }


}
