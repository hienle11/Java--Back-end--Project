import config.AppConfig;
import controller.CategoryController;
import controller.InventoryController;
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
public class InventoryAPITest {

    @Autowired
    InventoryController inventoryController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void getInventoryReportTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/inventory")
                        .param("startDate", "2019-04-11")
                        .param("endDate", "2019-06-30"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // IMPORTANT: It is reasonable that balance can be negative value,
        // since more products could be received before the given period
        String expectedString = "\"content\": [\n" +
                "        {\n" +
                "            \"productName\": \"bmw\",\n" +
                "            \"receivedQuantity\": 5,\n" +
                "            \"deliveredQuantity\": 8,\n" +
                "            \"balance\": -3\n" +
                "        },\n" +
                "        {\n" +
                "            \"productName\": \"exciter\",\n" +
                "            \"receivedQuantity\": 0,\n" +
                "            \"deliveredQuantity\": 23,\n" +
                "            \"balance\": -23\n" +
                "        },\n" +
                "        {\n" +
                "            \"productName\": \"camry\",\n" +
                "            \"receivedQuantity\": 31,\n" +
                "            \"deliveredQuantity\": 17,\n" +
                "            \"balance\": 14\n" +
                "        }\n" +
                "    ],";
        expectedString = expectedString.replaceAll("[\n ]", "");
        String resultBody = result.getResponse().getContentAsString();
        resultBody = resultBody.replaceAll(" ", "");
        Assert.assertTrue(resultBody.contains(expectedString));

        result = mockMvc.perform(
                MockMvcRequestBuilders.get("/inventory")
                        .param("startDate", "2019-04-11")
                        .param("endDate", "2019-06-30")
                        .param("page", "1")
                        .param("size", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // IMPORTANT: It is reasonable that balance can be negative value,
        // since more products could be received before the given period
        expectedString = "\"content\": [\n" +
                "        {\n" +
                "            \"productName\": \"exciter\",\n" +
                "            \"receivedQuantity\": 0,\n" +
                "            \"deliveredQuantity\": 23,\n" +
                "            \"balance\": -23\n" +
                "        },\n" +
                "        {\n" +
                "            \"productName\": \"camry\",\n" +
                "            \"receivedQuantity\": 31,\n" +
                "            \"deliveredQuantity\": 17,\n" +
                "            \"balance\": 14\n" +
                "        }\n" +
                "    ],";
        expectedString = expectedString.replaceAll("[\n ]", "");
        resultBody = result.getResponse().getContentAsString();
        resultBody = resultBody.replaceAll(" ", "");
        Assert.assertTrue(resultBody.contains(expectedString));
    }

}
