package com.github.bg.admin.web.core.service.Impl;

import com.github.bg.admin.web.core.constant.SystemStaticConst;
import com.github.bg.admin.web.core.dao.BookInformationMapper;
import com.github.bg.admin.web.core.entity.BookInformation;
import com.github.bg.admin.web.core.entity.Page;
import com.github.bg.admin.web.core.entity.ReturnInfo;
import com.github.bg.admin.web.core.entity.dto.BookInformationDTO;
import com.github.bg.admin.web.core.service.BookInformationService;
import com.github.bg.admin.web.core.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.bg.admin.web.core.utils.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BookInformationServiceImpl implements BookInformationService {

    @Autowired
    private BookInformationMapper bookInformationMapper;

    @Override
    public ReturnInfo getAllBookInformation() {
        List<BookInformation> allBookInformation = bookInformationMapper.getAllBookInformation();
        return new ReturnInfo(SystemStaticConst.SUCCESS, "加载全部的书籍数据成功",allBookInformation);
    }

    @Override
    public BookInformation getcategoryByBookId(Integer id) {
        return null;
    }

    @Override
    public ReturnInfo save(BookInformationDTO bookInformationDTO) {
        try {
            int save = bookInformationMapper.save(bookInformationDTO);
            if (save > 0){
                return  new ReturnInfo(SystemStaticConst.SUCCESS, "添加书籍数据成功");
            }
            return new ReturnInfo(SystemStaticConst.FAIL, "添加书籍数据失败！" );
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnInfo(SystemStaticConst.FAIL, "更新书籍数据失败！失败原因：" + e.getMessage());
        }
    }

    @Override
    public ReturnInfo update(Integer BookId, String Publisher, String BookName, String Price,String Subhead,String Introduction,String BookThumb,String StyleBook) {
        try {
            int update = bookInformationMapper.update(BookId, Publisher, BookName, Price, Subhead, Introduction, BookThumb, StyleBook);
            if (update > 0){
                return  new ReturnInfo(SystemStaticConst.SUCCESS, "更新书籍数据成功");
            }
            System.out.println("update===="+update);
            return new ReturnInfo(SystemStaticConst.FAIL, "更新书籍数据失败！" );

        }catch (Exception e){
            e.printStackTrace();
            return new ReturnInfo(SystemStaticConst.FAIL, "更新书籍数据失败！失败原因：" + e.getMessage());
        }
    }


    @Override
    public ReturnInfo deleteById(Integer id) {
        bookInformationMapper.deleteById(id);
        return new ReturnInfo(SystemStaticConst.SUCCESS, "删除书籍成功");
    }

    @Override
    public ReturnInfo queryBookList(String search, String BookId, int pageSize, int current, String orderKey, String orderByValue,String NewIndex) {
        PageHelper.startPage(current, (pageSize > 0 && pageSize <= 500) ? pageSize : 20,(orderKey != null && !"".equals(orderKey)) ? ((orderByValue != null && !"".equals(orderByValue)) ? (orderKey + " " + orderByValue) : orderKey) : "");
        String [] BookArray = StringUtils.getObjStr(BookId).split(" ");
        HashMap<String, Object> res = PageUtil.getResult(bookInformationMapper.queryBookList(search,BookArray,NewIndex));
        return new ReturnInfo(SystemStaticConst.SUCCESS, "获取书籍列表数据成功！", new Page(pageSize, current, (long) res.get("total"), (List) res.get("rows")));

    }
}
