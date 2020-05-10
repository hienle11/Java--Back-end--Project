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
                "            \"price\": 1.5,\n" +
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
        System.out.println(expectedString);
        Assert.assertTrue(resultBody.contains(expectedString));
    }

//    @Test
//    public void findByIdTest() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/categories/100"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("{\"id\":100,\"name\":\"motobikeTest\"}"));
//
//    }
//
//    @Test
//    public void createTest() throws Exception {
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/categories")
//                        .contentType(APPLICATION_JSON)
//                        .content("{\"name\": \"test\"}"))                       // id is not provided
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/categories")
//                        .contentType(APPLICATION_JSON)
//                        .content("{\"id\":\"1\",\"name\": \"test\"}"))      // id that already exists in the database
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void updateTest() throws Exception {
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/categories")
//                        .contentType(APPLICATION_JSON)
//                        .content("{\"id\": 100,\"name\": \"motobikeTestHasChanged\"}"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/categories/100"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("{\"id\":100,\"name\":\"motobikeTestHasChanged\"}"));
//    }
//
//    @Test
//    public void deleteTest() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.delete("/categories/999"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        MvcResult result = mockMvc.perform(
//                MockMvcRequestBuilders.get("/categories/999"))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andReturn();
//
//        String resultBody = result.getResponse().getContentAsString();
//        Assert.assertTrue(resultBody.contains("The entity id not found"));
//    }
//
//    @Test
//    public void searchTest() throws Exception {
//        MvcResult result = mockMvc.perform(
//                MockMvcRequestBuilders.get("/categories/search")
//                        .param("field", "name")
//                        .param("searchKey", "keyTest"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String resultBody = result.getResponse().getContentAsString();
//        Assert.assertTrue(resultBody.contains("[{\"id\":103,\"name\":\"keyTest1\"},{\"id\":104,\"name\":\"keyTest2\"}]"));
//    }

}
