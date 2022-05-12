package cn.seecoder.courselearning.PostCreate;

import cn.seecoder.courselearning.vo.forum.PostVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

//测试创建帖子的Controller
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostCreateControllerTest {

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
    @Transactional
    public void test1() throws Exception {
        //创建一个PostVO对象用于测试
        PostVO postVO=new PostVO(1,1,"testTitle1","testContent1");

        //将PostVO对象转换为JSON字符串
        String JsonStr=mapper.writeValueAsString(postVO);

        //返回的JSON格式{"code":1,"msg":"创帖成功",
        // "data":{"postId":42,"courseId":6,"userId":3,
        // "userName":"18851751538","createTime":"2021-07-09 21:04:15",
        // "title":"hello","content":"test"}}
        mvc.perform(MockMvcRequestBuilders.post("/api/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonStr.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Transactional
    public void test2() throws Exception {
        //创建一个PostVO对象用于测试
        PostVO postVO=new PostVO(2,2,"testTitle2","testContent2");

        //将PostVO对象转换为JSON字符串
        String JsonStr=mapper.writeValueAsString(postVO);

        //返回的JSON格式{"code":1,"msg":"创帖成功",
        // "data":{"postId":42,"courseId":6,"userId":3,
        // "userName":"18851751538","createTime":"2021-07-09 21:04:15",
        // "title":"hello","content":"test"}}
        mvc.perform(MockMvcRequestBuilders.post("/api/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonStr.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @Transactional
    public void test3() throws Exception{
        //创建一个PostVO对象用于测试
        PostVO postVO=new PostVO(3,3,"testTitle3","testContent3");

        //将PostVO对象转换为JSON字符串
        String JsonStr=mapper.writeValueAsString(postVO);

        //返回的JSON格式{"code":1,"msg":"创帖成功",
        // "data":{"postId":42,"courseId":6,"userId":3,
        // "userName":"18851751538","createTime":"2021-07-09 21:04:15",
        // "title":"hello","content":"test"}}
        mvc.perform(MockMvcRequestBuilders.post("/api/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonStr.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
