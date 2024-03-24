package com.jzy.backend.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzy.backend.DO.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/23 21:40
 * @Description: TODO
 */
@Mapper
public interface MenuDAO extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userid);
}
