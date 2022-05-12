package cn.seecoder.courselearning.CourseWareLoad;

import cn.seecoder.courselearning.mapperservice.course.CourseMapper;
import cn.seecoder.courselearning.mapperservice.course.CourseWareMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseWareLoadControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Resource
    private CourseWareMapper courseWareMapper;

    @Before
    public void setMockMvc(){
        mvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }



    @Test
    @Transactional
    public void test()throws Exception{
        String fileName=courseWareMapper.selectByPrimaryKey(218).getFileName();
        mvc.perform(MockMvcRequestBuilders.post("/api/file/download/{filename}",fileName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
