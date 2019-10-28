package com.yang.service.impl;

import com.yang.BaseTest;
import com.yang.dto.AppointExecution;
import com.yang.entity.Book;
import com.yang.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImplTest extends BaseTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testAppoint() throws Exception {
        long bookId = 1001;
        long studentId = 12345678910L;
        AppointExecution execution = bookService.appoint(bookId, studentId);
        System.out.println(execution);
    }

    /**
     * 查询一本图书
     *
     * @return
     */
    @Test
    public void testGetById(){
        long bookId = 1002;
        Book book = bookService.getById(bookId);
        System.out.println(book);
    }

    /**
     * 查询所有图书
     *
     * @return
     */
    @Test
    public void getList(){
        List list = bookService.getList();
        System.out.println(list);
    }

}