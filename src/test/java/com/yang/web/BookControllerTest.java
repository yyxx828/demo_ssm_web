package com.yang.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
//@Transactional
public class BookControllerTest extends AbstractContextControllerTest {

    @Autowired
    private BookController bookController;

    private String listUrl = "/book/list";
    private String detailUrl = "/book/{bookId}/detail";
    private String appointUrl = "/book/{bookId}/appoint";

    private long bookId = 1000;

    //该mvc方式为简洁展示，使用父类的将会把详细内容全部打印出来
//    @Before
//    public void setUp(){
//        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
//    }

    @Test
    public void testList() throws Exception {
        this.mockMvc.perform(get(listUrl))
                .andExpect(status().isOk())
        .andExpect(view().name("book/list"));
    }

    @Test
    public void testListJson() throws Exception {
        this.mockMvc.perform(get(listUrl))
                .andExpect(status().isOk()).andExpect(model().attributeExists("list")).andExpect(view().name("book/list"));
    }



    @Test
    public void list() throws Exception {
        this.mockMvc.perform(get(listUrl)).andExpect(view().name("list"));
    }

    @Test
    public void existDetail() throws Exception{
        this.mockMvc.perform(get(detailUrl,bookId)).andExpect(view().name("detail")).andExpect(model().attributeExists("book"));
    }

    @Test
    public void notExistDetail() throws Exception{
        this.mockMvc.perform(get(detailUrl,1100)).andExpect(forwardedUrl(listUrl));
    }

    @Test
    public void appoint() throws Exception{
        this.mockMvc.perform(post(appointUrl,bookId).param("studentId","111").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=utf-8"));
    }

}
