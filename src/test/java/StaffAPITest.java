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
    StaffController staffController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(staffController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void findPaginatedTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/staffs")
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
                "            \"name\": \"ladygaga\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": null,\n" +
                "            \"email\": \"abc@gmail.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"name\": \"katy\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": null,\n" +
                "            \"email\": \"xyz@gmail.com\"\n" +
                "        }\n" +
                "    ],";
        expectedString = expectedString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(expectedString));
    }

    @Test
    public void findByIdTest() throws Exception {
        String expectedString = "{\n" +
                "    \"id\": 103,\n" +
                "    \"name\": \"adam\",\n" +
                "    \"address\": null,\n" +
                "    \"phone\": null,\n" +
                "    \"email\": \"asd@gmail.com\"\n" +
                "}";
        expectedString = expectedString.replaceAll("[\n ]", "");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/staffs/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test
    public void createTest() throws Exception {
        String newStaff = "{\n" +
                // id is not provided
                "    \"name\": \"newStaff\",\n" +
                "    \"address\": \"newAddress\",\n" +
                "    \"phone\": null,\n" +
                "    \"email\": \"new@gmail.com\"\n" +
                "}";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/staffs")
                        .contentType(APPLICATION_JSON)
                        .content(newStaff))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String expectedResult =  "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"newStaff\",\n" +
                "    \"address\": \"newAddress\",\n" +
                "    \"phone\": null,\n" +
                "    \"email\": \"new@gmail.com\"\n" +
                "}";
        expectedResult = expectedResult.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/staffs/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void updateTest() throws Exception {
        String updatedStaff = "{\n" +
                "    \"id\": 103,\n" +
                "    \"name\": \"nameIsUpdated\",\n" + // update new name
                "    \"address\": null,\n" +
                "    \"phone\": null\n" +
               // email is not provided
                "}";

        String resultStaff = "{\n" +
                "    \"id\": 103,\n" +
                "    \"name\": \"nameIsUpdated\",\n" + // name is updated
                "    \"address\": null,\n" +
                "    \"phone\": null,\n" +
                "    \"email\": \"asd@gmail.com\"\n" + // email is not updated, not change to null
                "}";
        resultStaff = resultStaff.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/staffs")
                        .contentType(APPLICATION_JSON)
                        .content(updatedStaff))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/staffs/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(resultStaff));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/staffs/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/staffs/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();
        Assert.assertTrue(resultBody.contains("The entity id not found"));
    }

    @Test
    public void searchTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/staffs/search")
                        .param("field", "email")
                        .param("searchKey", "xyz@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"name\": \"katy\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": null,\n" +
                "            \"email\": \"xyz@gmail.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 102,\n" +
                "            \"name\": \"maroon5\",\n" +
                "            \"address\": null,\n" +
                "            \"phone\": null,\n" +
                "            \"email\": \"xyz@gmail.com\"\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
    }
}
