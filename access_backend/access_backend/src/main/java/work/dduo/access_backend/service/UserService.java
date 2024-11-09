package work.dduo.access_backend.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import work.dduo.access_backend.model.dto.UserQueryRequest;
import work.dduo.access_backend.model.entity.User;

/**
* @author ZDY
* @description 针对表【user】的数据库操作Service
* @createDate 2024-10-09 10:27:26
*/
public interface UserService extends IService<User> {
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
