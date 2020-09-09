package com.github.bg.admin.web.core.service;

import com.github.bg.admin.web.core.entity.BookInformation;
import com.github.bg.admin.web.core.entity.ReturnInfo;
import com.github.bg.admin.web.core.entity.dto.BookInformationDTO;

public interface BookInformationService {
    public ReturnInfo getAllBookInformation();  //获取库中所有图书信息，用于测试，实际使用中会有查询条件
    public BookInformation getcategoryByBookId(Integer id); //根据父类ID查询对应分类，注意与分类中的方法区分



    public ReturnInfo save(BookInformationDTO bookInformationDTO);  //插入新的图书信息

    public ReturnInfo update(Integer BookId,String Publisher,String BookName,String Price,String Subhead,String Introduction,String BookThumb,String StyleBook);  //更新已有图书信息

    public ReturnInfo deleteById(Integer id);   //根据图书ID删除对应图书信息


    public ReturnInfo queryBookList(String search,String BookId, int pageSize, int current, String orderKey, String orderByValue,String NewIndex);
}
