package work.dduo.access_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import work.dduo.access_backend.constant.CommonConstant;
import work.dduo.access_backend.model.dto.UserQueryRequest;
import work.dduo.access_backend.model.entity.User;
import work.dduo.access_backend.service.UserService;
import work.dduo.access_backend.mapper.UserMapper;
import org.springframework.stereotype.Service;
import work.dduo.access_backend.utils.SqlUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author ZDY
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-09 10:27:26
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean save(User entity) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", entity.getEmail());

        if (userMapper.selectCount(queryWrapper) > 0) {
            return false;
        } else return super.save(entity);
    }

    // 分页和查询

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userQueryRequest == null) {
            return queryWrapper;
        }

        // 获取查询条件
        String name = userQueryRequest.getName();
        String mobile = userQueryRequest.getMobile();
        String email = userQueryRequest.getEmail();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();

        // 拼接查询条件
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.like(StringUtils.isNotBlank(mobile), "mobile", mobile);
        queryWrapper.like(StringUtils.isNotBlank(email), "email", email);


        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}
