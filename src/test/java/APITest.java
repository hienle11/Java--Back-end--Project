import config.AppConfig;
import controller.CategoryController;
import org.jboss.jandex.JandexAntTask;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class APITest {

    @Autowired
    CategoryController categoryController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void test1() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\": \"test\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/categories/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":2,\"name\":\"test\"}"));

    }
}
