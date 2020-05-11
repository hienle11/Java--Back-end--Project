import config.AppConfig;
import controller.ProductController;
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
public class ProductAPITest {

    @Autowired
    ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void findPaginatedTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/products")
                        .contentType(APPLICATION_JSON)
                        .param("size", "2")
                        .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resultBody = result.getResponse().getContentAsString();
        String expectedString = " \"content\": [\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"name\": \"winner\",\n" +
                "            \"model\": null,\n" +
                "            \"brand\": null,\n" +
                "            \"company\": null,\n" +
                "            \"price\": 2.5,\n" +
                "            \"description\": null,\n" +
                "            \"category\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"motobikeTest\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"name\": \"bmw\",\n" +
                "            \"model\": null,\n" +
                "            \"brand\": null,\n" +
                "            \"company\": null,\n" +
                "            \"price\": 25.0,\n" +
                "            \"description\": null,\n" +
                "            \"category\": {\n" +
                "                \"id\": 101,\n" +
                "                \"name\": \"carTest\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],";
        expectedString = expectedString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(expectedString));
    }

    @Test
    public void findByIdTest() throws Exception {
        String expectedString = "{\n" +
                "    \"id\": 103,\n" +
                "    \"name\": \"camry\",\n" +
                "    \"model\": \"model1234\",\n" +
                "    \"brand\": \"toyota\",\n" +
                "    \"company\": null,\n" +
                "    \"price\": 15.0,\n" +
                "    \"description\": null,\n" +
                "    \"category\": {\n" +
                "        \"id\": 101,\n" +
                "        \"name\": \"carTest\"\n" +
                "    }\n" +
                "}";
        expectedString = expectedString.replaceAll("[\n ]", "");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedString));

    }

    @Test
    public void createTest() throws Exception {
        String newProduct = "{\n" +
                // id is not provided
                "    \"name\": \"testProduct\",\n" +
                "    \"model\": \"model1234\",\n" +
                "    \"brand\": \"toyota\",\n" +
                "    \"company\": \"hehe\",\n" +
                "    \"price\": 890214.1224,\n" +
                "    \"description\": null,\n" +
                "    \"category\": {\n" +
                "        \"id\": 103\n" +
                "    }\n" +
                "}";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(newProduct))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String expectedResult = "{\n" +
                "    \"id\": 1,\n" +                    // id is added
                "    \"name\": \"testProduct\",\n" +
                "    \"model\": \"model1234\",\n" +
                "    \"brand\": \"toyota\",\n" +
                "    \"company\": \"hehe\",\n" +
                "    \"price\": 890214.1224,\n" +
                "    \"description\": null,\n" +
                "    \"category\": {\n" +
                "        \"id\": 103,\n" +
                "        \"name\": \"keyTest2\"\n" +
                "    }\n" +
                "}";
        expectedResult = expectedResult.replaceAll("[\n ]", "");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void updateTest() throws Exception {
        String updatedProduct = "{\n" +
                "    \"id\": 103,\n" +
                "    \"name\": \"camryHasChanged\",\n" +
               //   model is not provided, and expected not to become null
                "    \"brand\": \"modelHasChanged\",\n" +
                "    \"company\": null,\n" +
                "    \"price\": 123,\n" +
                "    \"description\": null,\n" +
                "    \"category\": {\n" +
                "        \"id\": 103\n" +
                "    }\n" +
                "}";

        String resultProduct = "{\n" +
                "    \"id\": 103,\n" +
                "    \"name\": \"camryHasChanged\",\n" +
                "    \"model\": \"model1234\",\n" +  // field not provided will not be updated
                "    \"brand\": \"modelHasChanged\",\n" +
                "    \"company\": null,\n" +
                "    \"price\": 123.0,\n" +
                "    \"description\": null,\n" +
                "    \"category\": {\n" +
                "        \"id\": 103,\n" +
                "        \"name\": \"keyTest2\"\n" +
                "    }\n" +
                "}";
        resultProduct = resultProduct.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/products")
                        .contentType(APPLICATION_JSON)
                        .content(updatedProduct))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(resultProduct));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/products/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/products/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();
        Assert.assertTrue(resultBody.contains("The entity id not found"));
    }

    @Test
    public void searchTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/products/search")
                        .param("field", "price")
                        .param("searchKey", "25"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"name\": \"bmw\",\n" +
                "            \"model\": null,\n" +
                "            \"brand\": null,\n" +
                "            \"company\": null,\n" +
                "            \"price\": 25.0,\n" +
                "            \"description\": null,\n" +
                "            \"category\": {\n" +
                "                \"id\": 101,\n" +
                "                \"name\": \"carTest\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 102,\n" +
                "            \"name\": \"exciter\",\n" +
                "            \"model\": \"model2020\",\n" +
                "            \"brand\": \"yamaha\",\n" +
                "            \"company\": null,\n" +
                "            \"price\": 25.0,\n" +
                "            \"description\": null,\n" +
                "            \"category\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"motobikeTest\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
    }

}
