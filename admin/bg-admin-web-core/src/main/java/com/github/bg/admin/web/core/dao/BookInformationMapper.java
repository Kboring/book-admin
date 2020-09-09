package com.github.bg.admin.web.core.dao;


import com.github.bg.admin.web.core.entity.BookInformation;
import com.github.bg.admin.web.core.entity.dto.BookInformationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//接口内定义基本的方法
@Mapper
public interface BookInformationMapper {
    public List<BookInformation> getAllBookInformation();  //获取库中所有图书信息，用于测试，实际使用中会有查询条件
    public BookInformation getcategoryByBookId(Integer id); //根据父类ID查询对应分类，注意与分类中的方法区分


    public int save(@Param("bookInformationDTO") BookInformationDTO bookInformationDTO);  //插入新的图书信息

    public int update(Integer BookId, String Publisher, String BookName, String Price,String Subhead,String Introduction,String BookThumb,String StyleBook);  //更新已有图书信息

    public void deleteById(Integer id);   //根据图书ID删除对应图书信息
    /**
     * 功能描述：获取数据字典列表
     * @param search 模糊匹配数据字典的dictType、dictText、dictValue、dictCode
     * @param search 模糊匹配数据字典的BookId、BookName、Publisher、Price
     * @return 返回查询结果
     */
    List<BookInformation> queryBookList(@Param("search") String search, @Param("BookArray")String [] BookArray,String NewIndex);

}
