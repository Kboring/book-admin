package com.github.bg.admin.web.core.service;

import com.github.bg.admin.web.core.entity.ReturnInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeService {
    ReturnInfo getTreeList();

    ReturnInfo getTreeByTreeName();

    ReturnInfo getTreeByTreeId();

    ReturnInfo getTreeByCategoryId();

    ReturnInfo getTreeByCategoryIdNot0();

    ReturnInfo addFirstTree(String firstName,String parentTreeId);

    ReturnInfo updateFirstTree(String firstName,String parentTreeId);

    ReturnInfo deleteFirstTree(String index);

    ReturnInfo deleteSecondTree(String index);

//    ReturnInfo addSecondTree(String firstName,String parentTreeId);


}
