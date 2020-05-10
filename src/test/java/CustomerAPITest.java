import config.AppConfig;
import controller.CustomerController;
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
public class CustomerAPITest {

    @Autowired
    CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void findPaginatedTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/customers")
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

    @Test
    public void findByIdTest() throws Exception {
        String expectedString = "{\n" +
                "    \"id\": 103,\n" +
                "    \"name\": \"ronaldo\",\n" +
                "    \"address\": null,\n" +
                "    \"phone\": null,\n" +
                "    \"fax\": \"4444\",\n" +
                "    \"email\": null,\n" +
                "    \"contactPerson\": null\n" +
                "}";
        expectedString = expectedString.replaceAll("[\n ]", "");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test
    public void createTest() throws Exception {
        String newCustomer = "{\n" +
                "    \"name\": \"newCustomer\",\n" +
                "    \"address\": null,\n" +
                "    \"phone\": null,\n" +
                "    \"fax\": \"100\",\n" +
                "    \"email\": null,\n" +
                "    \"contactPerson\": null\n" +
                "}";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .contentType(APPLICATION_JSON)
                        .content(newCustomer))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String expectedResult =  "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"newCustomer\",\n" +
                "    \"address\": null,\n" +
                "    \"phone\": null,\n" +
                "    \"fax\": \"100\",\n" +
                "    \"email\": null,\n" +
                "    \"contactPerson\": null\n" +
                "}";
        expectedResult = expectedResult.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void updateTest() throws Exception {
        String updatedCustomer = "{\n" +
                "            \"id\": 103,\n" +
                "            \"name\": \"updated\",\n" +
                "            \"address\": \"updated\",\n" +
                "            \"phone\": null,\n" +
                // fax field is not provided
                "            \"email\": \"updated\",\n" +
                "            \"contactPerson\": null\n" +
                "        }";

        String resultCustomer = "{\n" +
                "            \"id\": 103,\n" +
                "            \"name\": \"updated\",\n" +
                "            \"address\": \"updated\",\n" +
                "            \"phone\": null,\n" +
                "            \"fax\": \"4444\",\n" +        // fax field is not updated
                "            \"email\": \"updated\",\n" +
                "            \"contactPerson\": null\n" +
                "        }";
        resultCustomer = resultCustomer.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/customers")
                        .contentType(APPLICATION_JSON)
                        .content(updatedCustomer))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(resultCustomer));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/customers/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();
        Assert.assertTrue(resultBody.contains("The entity id not found"));
    }

    @Test
    public void searchTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/search")
                        .param("field", "name")
                        .param("searchKey", "koke"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = " \"content\": [\n" +
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
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
    }

}
