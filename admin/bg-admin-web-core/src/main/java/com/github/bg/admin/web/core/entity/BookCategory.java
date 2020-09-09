package com.github.bg.admin.web.core.entity;
/*
用到了： Java的包装类 Byte,Short, Integer, Long, Float, Double,Boolean，Character
 */

import lombok.Data;

import java.util.Date;

//下面的注解，可以自动生成get,set方法
@Data
public class BookCategory {
    public Integer ParentId;       //父类ID
    public Integer CategoryId;     //分类ID，若为一级分类，则为0，二级分类则为一级分类的CategoryId'
    public String CategoryName;    //分类名称
    public Short Sort;             //是否在页面上显示（0为是，1为否）
    public Long Display;           //'是否在页面上显示（0为是，1为否）'
    public String CategoryThumb;   //分类图标，存储路径
    public String create_by;       //创建人
    public Date create_date;       //创建
    public String update_by;       //最后更新人
    public Date update_date;       //最后更新时间
    public String remarks;         //备注信息
    public Character del_flag;     //删除标记,‘0’正常， ‘1’已删除
}
