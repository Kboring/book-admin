package com.github.bg.admin.web.core.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

//添加Data注解，帮助快速生成类（可以自动生成get,set方法）

//创建实体类时，数据类型需与数据库中设计的数据类型保持一致！！！
public class BookInformationDTO {
    public Integer BookId;     //图书ID，后台获取
    public String BookName;     //书名
    public String Subhead;      //副标题
    public String Publisher;     //出版社
    public String Price;        //定价
    public String Introduction;    //简介
    public String BookThumb;      //缩略图，存储路径
    public String StyleBook;      //样书文件，存储路径
    public Integer CategoryId;

    @Override
    public String toString() {
        return "BookInformationDTO{" +
                "BookId=" + BookId +
                ", BookName='" + BookName + '\'' +
                ", Subhead='" + Subhead + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", Price='" + Price + '\'' +
                ", Introduction='" + Introduction + '\'' +
                ", BookThumb='" + BookThumb + '\'' +
                ", StyleBook='" + StyleBook + '\'' +
                ", CategoryId=" + CategoryId +
                '}';
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }

    public Integer getBookId() {
        return BookId;
    }

    public void setBookId(Integer bookId) {
        BookId = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getSubhead() {
        return Subhead;
    }

    public void setSubhead(String subhead) {
        Subhead = subhead;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public String getBookThumb() {
        return BookThumb;
    }

    public void setBookThumb(String bookThumb) {
        BookThumb = bookThumb;
    }

    public String getStyleBook() {
        return StyleBook;
    }

    public void setStyleBook(String styleBook) {
        StyleBook = styleBook;
    }
}
