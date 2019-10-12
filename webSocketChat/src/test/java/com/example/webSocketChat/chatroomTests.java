package com.example.webSocketChat;

import com.example.webSocketChat.controller.WebSocketChatServer;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static javax.swing.UIManager.get;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class chatroomTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext applicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    public void login() throws Exception {
        assert(this.mockMvc != null);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void chat() throws Exception {
        assert(this.mockMvc != null);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/chat?username=David"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("chat"));
    }

    @Test
    public void validURL() throws Exception {
        assert(this.mockMvc != null);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void validURL2() throws Exception {
        assert(this.mockMvc != null);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/chat").param("username", "Molly"))
                .andExpect(status().isOk());
    }

    @Test
    public void logout() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage pageChat = webClient.getPage("http://localhost:8080/chat?username=Jack");
        Assert.assertTrue(pageChat.asText().contains("LOGOUT"));
        final HtmlButton button = pageChat.getHtmlElementById("logout");
        final HtmlPage pageLogin = button.click();
        assert(pageLogin != null);
        Assert.assertTrue(pageLogin.asText().contains("Chat Room"));
        Assert.assertTrue(pageLogin.asText().contains("Login"));
    }
 /*
    @Test
    public void logout() throws Exception {
        WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("http://localhost:8080/chat?username=David");
        Assert.assertTrue(page.asText().contains("LOGOUT"));
    }

 */

}
