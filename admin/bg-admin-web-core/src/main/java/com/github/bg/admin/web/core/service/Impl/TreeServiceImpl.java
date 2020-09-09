package com.github.bg.admin.web.core.service.Impl;

import com.github.bg.admin.web.core.constant.SystemStaticConst;
import com.github.bg.admin.web.core.dao.TreeMapper;
import com.github.bg.admin.web.core.entity.Page;
import com.github.bg.admin.web.core.entity.ReturnInfo;
import com.github.bg.admin.web.core.service.TreeService;
import com.github.bg.admin.web.core.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeMapper treeMapper;

    @Override
    public ReturnInfo getTreeList() {
        HashMap<String, Object> res = PageUtil.getResult(treeMapper.getTreeList());
        return new ReturnInfo(SystemStaticConst.SUCCESS, "获取书籍列表数据成功！", new Page((List) res.get("rows")));

    }

    @Override
    public ReturnInfo getTreeByTreeName() {
        HashMap<String, Object> res = PageUtil.getResult(treeMapper.getTreeByTreeName());
        return new ReturnInfo(SystemStaticConst.SUCCESS, "获取书籍列表数据成功！", new Page((List) res.get("rows")));

    }

    @Override
    public ReturnInfo getTreeByTreeId() {
        HashMap<String, Object> res = PageUtil.getResult(treeMapper.getTreeByTreeId());
        return new ReturnInfo(SystemStaticConst.SUCCESS, "获取书籍列表数据成功！", new Page((List) res.get("rows")));

    }

    @Override
    public ReturnInfo getTreeByCategoryId() {
        HashMap<String, Object> res = PageUtil.getResult(treeMapper.getTreeByCategoryId());
        return new ReturnInfo(SystemStaticConst.SUCCESS, "获取书籍列表数据成功！", new Page((List) res.get("rows")));

    }

    @Override
    public ReturnInfo getTreeByCategoryIdNot0() {
        HashMap<String, Object> res = PageUtil.getResult(treeMapper.getTreeByCategoryIdNot0());
        return new ReturnInfo(SystemStaticConst.SUCCESS, "获取书籍列表数据成功！", new Page((List) res.get("rows")));

    }

    @Override
    public ReturnInfo addFirstTree(String firstName,String parentTreeId) {
        try {
            int save = treeMapper.addFirstTree(firstName,parentTreeId);
            if (save > 0){
                return  new ReturnInfo(SystemStaticConst.SUCCESS, "添加目录成功");
            }
            return new ReturnInfo(SystemStaticConst.FAIL, "添加目录失败！" );
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnInfo(SystemStaticConst.FAIL, "添加目录失败！失败原因：" + e.getMessage());
        }
    }

    @Override
    public ReturnInfo updateFirstTree(String firstName,String parentTreeId) {
        try {
            int update = treeMapper.updateFirstTree(firstName,parentTreeId);
            if (update > 0){
                return  new ReturnInfo(SystemStaticConst.SUCCESS, "更新目录成功");
            }
            return new ReturnInfo(SystemStaticConst.FAIL, "更新目录失败！" );
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnInfo(SystemStaticConst.FAIL, "更新目录失败！失败原因：" + e.getMessage());
        }
    }

    @Override
    public ReturnInfo deleteFirstTree(String index) {
        try {
            int update = treeMapper.deleteFirstTree(index);
            if (update > 0){
                return  new ReturnInfo(SystemStaticConst.SUCCESS, "删除目录成功");
            }
            return new ReturnInfo(SystemStaticConst.FAIL, "删除目录失败！" );
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnInfo(SystemStaticConst.FAIL, "删除目录失败！失败原因：" + e.getMessage());
        }
    }

    @Override
    public ReturnInfo deleteSecondTree(String index) {
        try {
            int update = treeMapper.deleteSecondTree(index);
            if (update > 0){
                return  new ReturnInfo(SystemStaticConst.SUCCESS, "删除目录成功");
            }
            return new ReturnInfo(SystemStaticConst.FAIL, "删除目录失败！" );
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnInfo(SystemStaticConst.FAIL, "删除目录失败！失败原因：" + e.getMessage());
        }
    }

//    @Override
//    public ReturnInfo addSecondTree(String firstName, String parentTreeId) {
//        try {
//            int save = treeMapper.addSecondTree(firstName,parentTreeId);
//            if (save > 0){
//                return  new ReturnInfo(SystemStaticConst.SUCCESS, "添加目录成功");
//            }
//            return new ReturnInfo(SystemStaticConst.FAIL, "添加目录失败！" );
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ReturnInfo(SystemStaticConst.FAIL, "更新目录失败！失败原因：" + e.getMessage());
//        }
//    }
}
