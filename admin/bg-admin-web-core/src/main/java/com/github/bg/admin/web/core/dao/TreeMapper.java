package com.github.bg.admin.web.core.dao;

import com.github.bg.admin.web.core.entity.BookInformation;
import com.github.bg.admin.web.core.entity.ReturnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TreeMapper {
    List getTreeList();

    List getTreeByTreeName();

    List getTreeByTreeId();

    List getTreeByCategoryId();

    List getTreeByCategoryIdNot0();

    int updateFirstTree(@Param("firstName") String firstName,@Param("parentTreeId") String parentTreeId);

    int addFirstTree(@Param("firstName") String firstName,@Param("parentTreeId")String parentTreeId);

    int deleteFirstTree(@Param("index") String index);

    int deleteSecondTree(@Param("index") String index);

//    int addSecondTree(@Param("firstName") String firstName,@Param("parentTreeId") String parentTreeId);

}
