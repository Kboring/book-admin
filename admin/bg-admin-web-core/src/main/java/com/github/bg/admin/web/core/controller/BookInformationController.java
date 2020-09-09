package com.github.bg.admin.web.core.controller;

import com.github.bg.admin.web.core.entity.BookInformation;
import com.github.bg.admin.web.core.entity.ReturnInfo;
import com.github.bg.admin.web.core.entity.dto.BookInformationDTO;
import com.github.bg.admin.web.core.service.BookInformationService;
import com.github.bg.admin.web.core.utils.JsonUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//直接返回json数据
@RestController
@RequestMapping("book")
public class BookInformationController {

    //    自动注入BookCategoryRepository对象
    @Autowired
    private BookInformationService bookInformationService;

    @RequestMapping("/getAllBookInformation")
    public ReturnInfo getAllBookInformation() {
        return bookInformationService.getAllBookInformation();
    }

    //    PathVariable将请求id赋值给形参
    @PostMapping("/getcategoryByBookId/{id}")
    public BookInformation getcategoryByBookId(@PathVariable("id") Integer id) {
        return bookInformationService.getcategoryByBookId(id);
    }

    //    RequestBody将请求的json对象转换成java对象
    //    ResponseBody将java对象转换成json对象返回客户端
    @PostMapping(value = "/saveBookInformation")
    public ReturnInfo save(@RequestParam Map<String, String> params) {
        BookInformationDTO bookInformationDTO = new BookInformationDTO();
        bookInformationDTO.setBookName(params.get("BookName"));
        bookInformationDTO.setSubhead( params.get("Subhead"));
        bookInformationDTO.setPublisher( params.get("Publisher"));
        bookInformationDTO.setPrice(params.get("Price"));
        bookInformationDTO.setIntroduction(params.get("Introduction"));
        bookInformationDTO.setBookThumb(params.get("BookThumb"));
        bookInformationDTO.setStyleBook(params.get("StyleBook"));
        bookInformationDTO.setCategoryId(Integer.parseInt(params.get("CategoryId")));
        return bookInformationService.save(bookInformationDTO);
    }
//    @PostMapping(value = "/saveBookInformation")
//    public ReturnInfo save(@RequestBody BookInformationDTO bookInformationDTO) {
//        return bookInformationService.save(bookInformationDTO);
//    }

    @RequestMapping("/updateBookInformation")
    public ReturnInfo update(@RequestParam(name = "BookId") Integer BookId,
                             @RequestParam(name = "Publisher") String Publisher,
                             @RequestParam(name = "BookName") String BookName,
                             @RequestParam(name = "Price") String Price,
                             @RequestParam(name = "Subhead") String Subhead,
                             @RequestParam(name = "Introduction") String Introduction,
                             @RequestParam(name = "BookThumb") String BookThumb,
                             @RequestParam(name = "StyleBook") String StyleBook
    ) {
        ReturnInfo update = bookInformationService.update(BookId, Publisher, BookName, Price,Subhead,Introduction,BookThumb,StyleBook);
        return update;
    }

    @RequestMapping("/deleteBookInformationById")
    public ReturnInfo deleteById(@RequestParam(name = "id") Integer id) {
        return bookInformationService.deleteById(id);
    }

    /**
     * 功能描述：获取数据字典列表
     *
     * @param search       模糊匹配数据字典的dictType、dictText、dictValue、dictCode 允许为空
     * @param pageSize     每页显示的记录的条数
     * @param current      当前访问第几页
     * @param orderKey     排序字段
     * @param orderByValue 排序方式，降序还是升序
     * @return 返回查询结果
     */
    @ApiOperation(value = "获取数据字典列表")
    @RequestMapping("queryBookList")
    public ReturnInfo queryBookList(@RequestParam(name = "search", required = false) String search,
                                    @RequestParam(name = "BookId", required = false) String BookId,
                                    @RequestParam(name = "pageSize") int pageSize,
                                    @RequestParam(name = "current") int current,
                                    @RequestParam(name = "orderKey", required = false) String orderKey,
                                    @RequestParam(name = "orderByValue", required = false) String orderByValue,
                                    @RequestParam(name = "NewIndex", required = false) String NewIndex) {
        return bookInformationService.queryBookList(search, BookId, pageSize, current, orderKey, orderByValue, NewIndex);
    }
}


