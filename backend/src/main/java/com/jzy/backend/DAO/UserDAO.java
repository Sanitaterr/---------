package com.jzy.backend.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzy.backend.DO.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测试用的
 * @author JZY
 * @version 1.0
 * Create by 2024/3/20 23:34
 * @Description: TODO
 */
@Mapper
public interface UserDAO extends BaseMapper<User> {
}
