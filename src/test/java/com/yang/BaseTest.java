package com.yang;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml"})
public class BaseTest {
}