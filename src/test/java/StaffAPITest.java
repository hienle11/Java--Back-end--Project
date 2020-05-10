import config.AppConfig;
import controller.StaffController;
import exception.EntityExceptionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@Sql("file:src/test/java/resources/schema.sql")
public class StaffAPITest {

    @Autowired
    StaffController StaffController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(StaffController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void findPaginatedTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/Staffs")
                        .contentType(APPLICATION_JSON)
                        .param("size", "2")
                        .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resultBody = result.getResponse().getContentAsString();
        System.out.println(resultBody);
        String expectedString = " \"content\": [\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"name\": \"edison\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": null,\n" +
                "            \"fax\": \"1111\",\n" +
                "            \"email\": null,\n" +
                "            \"contactPerson\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"name\": \"koke\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": null,\n" +
                "            \"fax\": \"2222\",\n" +
                "            \"email\": null,\n" +
                "            \"contactPerson\": null\n" +
                "        }\n" +
                "    ],";
        expectedString = expectedString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(expectedString));
    }

//    wa
}
