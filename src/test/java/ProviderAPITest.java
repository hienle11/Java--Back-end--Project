import config.AppConfig;
import controller.ProviderController;
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
public class ProviderAPITest {

    @Autowired
    ProviderController providerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(providerController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void findPaginatedTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/providers")
                        .contentType(APPLICATION_JSON)
                        .param("size", "2")
                        .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resultBody = result.getResponse().getContentAsString();
        String expectedString = " \"content\": [\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"name\": \"david\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": \"+123\",\n" +
                "            \"fax\": null,\n" +
                "            \"email\": null,\n" +
                "            \"contactPerson\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"name\": \"edward\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": \"+111\",\n" +
                "            \"fax\": null,\n" +
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
                "    \"name\": \"alison\",\n" +
                "    \"address\": null,\n" +
                "    \"phone\": \"+111\",\n" +
                "    \"fax\": null,\n" +
                "    \"email\": null,\n" +
                "    \"contactPerson\": null\n" +
                "}";
        expectedString = expectedString.replaceAll("[\n ]", "");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/providers/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test
    public void createTest() throws Exception {
        String newProvider = "{\n" +
                // id is not provided
                "    \"name\": \"newProvider\",\n" +
                "    \"address\": \"newAddress\",\n" +
                "    \"phone\": \"newPhone\",\n" +
                "    \"fax\": \"newFax\",\n" +
                "    \"email\": \"newEmail\",\n" +
                "    \"contactPerson\": \"newContactPerson\"\n" +
                "}";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/providers")
                        .contentType(APPLICATION_JSON)
                        .content(newProvider))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String expectedResult =  "{\n" +
                "    \"id\": 1,\n" +                                // id is added
                "    \"name\": \"newProvider\",\n" +
                "    \"address\": \"newAddress\",\n" +
                "    \"phone\": \"newPhone\",\n" +
                "    \"fax\": \"newFax\",\n" +
                "    \"email\": \"newEmail\",\n" +
                "    \"contactPerson\": \"newContactPerson\"\n" +
                "}";
        expectedResult = expectedResult.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/providers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void updateTest() throws Exception {
        String updatedProduct = "{\n" +
                "    \"id\": 103,\n" +
                "    \"name\": \"newProvider\",\n" +
                "    \"address\": \"newAddress\",\n" +
                // phone field is not provided
                "    \"fax\": \"newFax\",\n" +
                "    \"email\": \"newEmail\",\n" +
                "    \"contactPerson\": \"newContactPerson\"\n" +
                "}";

        String resultProduct = "{\n" +
                "    \"id\": 103,\n" +
                "    \"name\": \"newProvider\",\n" +
                "    \"address\": \"newAddress\",\n" +
                "    \"phone\": \"+111\",\n" +                  // phone field is not changed
                "    \"fax\": \"newFax\",\n" +
                "    \"email\": \"newEmail\",\n" +
                "    \"contactPerson\": \"newContactPerson\"\n" +
                "}";
        resultProduct = resultProduct.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/providers")
                        .contentType(APPLICATION_JSON)
                        .content(updatedProduct))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/providers/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(resultProduct));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/providers/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/providers/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();
        Assert.assertTrue(resultBody.contains("The entity id not found"));
    }

    @Test
    public void searchTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/providers/search")
                        .param("field", "phone")
                        .param("searchKey", "111"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = " \"content\": [\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"name\": \"edward\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": \"+111\",\n" +
                "            \"fax\": null,\n" +
                "            \"email\": null,\n" +
                "            \"contactPerson\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 103,\n" +
                "            \"name\": \"alison\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": \"+111\",\n" +
                "            \"fax\": null,\n" +
                "            \"email\": null,\n" +
                "            \"contactPerson\": null\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
    }

}
