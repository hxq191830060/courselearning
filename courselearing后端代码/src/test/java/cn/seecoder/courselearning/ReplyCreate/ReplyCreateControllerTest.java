package cn.seecoder.courselearning.ReplyCreate;

import cn.seecoder.courselearning.vo.forum.ReplyVO;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReplyCreateControllerTest {
    private MockMvc mvc;

    @Resource
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setMockMvc(){
        mvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void test() throws Exception {
        ReplyVO replyVO=new ReplyVO(1,1,"test1");
        String JsonStr=mapper.writeValueAsString(replyVO);

        mvc.perform(MockMvcRequestBuilders.post("/api/reply/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonStr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
