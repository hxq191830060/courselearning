package cn.seecoder.courselearning.DiscussionForumView;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DiscussionForumViewController {

    private MockMvc mvc;

    //用户将对象转换为JSON字符串
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setMockMvc(){
        mvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testGetOneUserAllPosts() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/post/getOneUserAllPosts/{userId}",1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testGetAll() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/post/getAll/{courseId}",1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testGetPostByPostId() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/post/getPostByPostId/{postId}",1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }



}
