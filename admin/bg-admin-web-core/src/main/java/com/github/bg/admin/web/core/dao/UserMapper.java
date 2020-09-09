package com.github.bg.admin.web.core.dao;

import com.github.bg.admin.web.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    User findUser(@Param("map") Map map);

}
