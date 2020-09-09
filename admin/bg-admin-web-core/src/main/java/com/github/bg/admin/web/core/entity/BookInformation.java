package com.github.bg.admin.web.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//添加Data注解，帮助快速生成类（可以自动生成get,set方法）
@Data
@AllArgsConstructor
@NoArgsConstructor
//创建实体类时，数据类型需与数据库中设计的数据类型保持一致！！！
public class BookInformation {
    public Integer BookId;     //图书ID，后台获取
    public String BookName;     //书名
    public String Subhead;      //副标题
    public String Publisher;     //出版社
    public String Price;        //定价
    public String Introduction;    //简介
    public String BookThumb;      //缩略图，存储路径
    public String StyleBook;      //样书文件，存储路径
    public Integer CategoryId;    //二级目录ID，类目表ID统一，不能为空
    public String create_by;       //创建人
    public Date create_date;       //创建
    public String update_by;       //最后更新人
    public Date update_date;       //最后更新时间
    public String remarks;         //备注信息
    public Character del_flag;     //删除标记,‘0’正常， ‘1’已删除
}
