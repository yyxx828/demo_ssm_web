package com.yang.web;

import com.yang.dto.AppointExecution;
import com.yang.dto.Result;
import com.yang.entity.Book;
import com.yang.enums.AppointStateEnum;
import com.yang.exception.NoNumberException;
import com.yang.exception.RepeatAppointException;
import com.yang.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Book> list = bookService.getList();
        model.addAttribute("list",list);
        //list.jsp + model = ModelAndView
        log.info("查询列表");
        return "book/list";
    }

    @RequestMapping(value = "/{bookId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("bookId") Long bookId,Model model){
        if(bookId == null){
            return "redirect:/book/list";
        }
        Book book = bookService.getById(bookId);
        if(book == null){
            return "forward:/book/list";
        }
        model.addAttribute("entity",book);
        log.info("通过ID[{}]查询书本信息",bookId);
        return "book/detail";
    }

    @RequestMapping(value = "/{bookId}detailJson",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Object detailJson(@PathVariable("bookId") Long bookId){
        if(bookId == null){
            log.info("书籍ID为空，不能进行查询");
            return new Result(false,"书籍ID为空");
        }
        Book book = bookService.getById(bookId);
        if(book == null){
            log.info("该书籍ID[{}]找不到对应书籍",bookId);
            return new Result(false,"该编号书籍找不到");
        }
        log.info("通过ID[{}]查询书本信息，返回Json格式");
        return book;
    }

    @RequestMapping(value = "",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Result appint(@PathVariable("bookId") Long bookId, @RequestParam("studentId") Long studentId){
        if (studentId == null || studentId.equals("")) {
            return new Result(false, "学号不能为空");
        }
        AppointExecution execution = null;
        try {
            execution = bookService.appoint(bookId, studentId);
        } catch (NoNumberException e1) {
            execution = new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
        } catch (RepeatAppointException e2) {
            execution = new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
        } catch (Exception e) {
            execution = new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
        }
        return new Result<AppointExecution>(true, execution);
    }


}
