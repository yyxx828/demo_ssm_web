package com.yang.dao;

import com.yang.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {

    /***
     * 通过ID查询单本读书
     * @param id
     * @return
     */
    Book queryById(long id);

    /***
     * 分页查询所有图书
     * @param offset
     * @param limit
     * @return
     */
    List<Book> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /***
     * 减少图书数量
     * @param bookId
     * @return 如果影响行数>=1，表示更新成功
     */
    int reduceNumber(long bookId);

}
