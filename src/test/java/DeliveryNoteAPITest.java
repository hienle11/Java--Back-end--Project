import config.AppConfig;
import controller.DeliveryNoteController;
import entity.DeliveryNoteDetail;
import exception.EntityExceptionHandler;
import org.hibernate.SessionFactory;
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
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@Sql("file:src/test/java/resources/schema.sql")
public class DeliveryNoteAPITest {

    @Autowired
    DeliveryNoteController deliveryNoteController;

    @Autowired
    SessionFactory sessionFactory;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(deliveryNoteController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void findPaginatedTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/delivery-notes")
                        .contentType(APPLICATION_JSON)
                        .param("size", "2")
                        .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resultBody = result.getResponse().getContentAsString();
        System.out.println(resultBody);
        String expectedString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"date\": \"11-06-2019\",\n" +
                "            \"deliveryNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1001,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 101,\n" +
                "                        \"name\": \"bmw\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 2\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1002,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 103,\n" +
                "                        \"name\": \"camry\",\n" +
                "                        \"model\": \"model1234\",\n" +
                "                        \"brand\": \"toyota\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 15.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 4\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1003,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 101,\n" +
                "                        \"name\": \"bmw\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 3\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"date\": \"17-06-2019\",\n" +
                "            \"deliveryNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1011,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 102,\n" +
                "                        \"name\": \"exciter\",\n" +
                "                        \"model\": \"model2020\",\n" +
                "                        \"brand\": \"yamaha\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 100,\n" +
                "                            \"name\": \"motobikeTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 8\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1012,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 103,\n" +
                "                        \"name\": \"camry\",\n" +
                "                        \"model\": \"model1234\",\n" +
                "                        \"brand\": \"toyota\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 15.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 1\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        expectedString = expectedString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(expectedString));
    }

    @Test
    public void findByIdTest() throws Exception {
        String expectedString = "{\n" +
                "    \"id\": 103,\n" +
                "    \"date\": \"27-05-2019\",\n" +
                "    \"deliveryNoteDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1031,\n" +
                "            \"product\": {\n" +
                "                \"id\": 102,\n" +
                "                \"name\": \"exciter\",\n" +
                "                \"model\": \"model2020\",\n" +
                "                \"brand\": \"yamaha\",\n" +
                "                \"company\": null,\n" +
                "                \"price\": 25.0,\n" +
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 100,\n" +
                "                    \"name\": \"motobikeTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 4\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1032,\n" +
                "            \"product\": {\n" +
                "                \"id\": 103,\n" +
                "                \"name\": \"camry\",\n" +
                "                \"model\": \"model1234\",\n" +
                "                \"brand\": \"toyota\",\n" +
                "                \"company\": null,\n" +
                "                \"price\": 15.0,\n" +
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 101,\n" +
                "                    \"name\": \"carTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 12\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        expectedString = expectedString.replaceAll("[\n ]", "");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/delivery-notes/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test
    public void createTest() throws Exception {
        String newDeliveryNote = "{\n" +
                // date is not provided
                "    \"deliveryNoteDetails\": [\n" +
                "        {\n" +
                "            \"product\": {\n" +
                "                \"id\": 102\n" +
                "            },\n" +
                "            \"quantity\": 4\n" +
                "        },\n" +
                "        {\n" +
                "            \"product\": {\n" +
                "                \"id\": 100\n" +
                "            },\n" +
                "            \"quantity\": 12\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/delivery-notes")
                        .contentType(APPLICATION_JSON)
                        .content(newDeliveryNote))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); // get the current date
        String expectedResult =  "{\n" +
                "    \"id\": 1,\n" +
                "    \"date\": \"" + date + "\",\n" +
                "    \"deliveryNoteDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"product\": {\n" +
                "                \"id\": 102,\n" +
                "                \"name\": \"exciter\",\n" +
                "                \"model\": \"model2020\",\n" +
                "                \"brand\": \"yamaha\",\n" +
                "                \"company\": null,\n" +
                "                \"price\": 25.0,\n" +
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 100,\n" +
                "                    \"name\": \"motobikeTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 4\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"product\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"winner\",\n" +
                "                \"model\": null,\n" +
                "                \"brand\": null,\n" +
                "                \"company\": null,\n" +
                "                \"price\": 2.5,\n" +
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 100,\n" +
                "                    \"name\": \"motobikeTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 12\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        expectedResult = expectedResult.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/delivery-notes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void updateTest() throws Exception {
        String updatedDeliveryNote = "{\n" +
                "\t\"id\": 103,\n" +
                "    \"deliveryDetails\": [\n" +
                "    \t{\n" +
                "    \t\t\"id\": 1031,\n" +
                "    \t\t\"product\": {\n" +
                "    \t\t\t\"id\": 103\n" +
                "    \t\t},\n" +
                "    \t\t\"price\": 3\n" +
                "    \t},\n" +
                "    \t{\n" +
                "    \t\t\"product\": {\n" +
                "    \t\t\t\"id\": 101\n" +
                "    \t\t},\n" +
                "    \t\t\"quantity\": 2\n" +
                "    \t}\n" +
                "    ]\n" +
                "}";

        String resultDeliveryNote = "{\n" +
                "    \"id\": 103,\n" +
                "    \"date\": \"27-05-2019\",\n" +
                "    \"deliveryNoteDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1031,\n" +
                "            \"product\": {\n" +
                "                \"id\": 102,\n" +
                "                \"name\": \"exciter\",\n" +
                "                \"model\": \"model2020\",\n" +
                "                \"brand\": \"yamaha\",\n" +
                "                \"company\": null,\n" +
                "                \"price\": 25.0,\n" +
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 100,\n" +
                "                    \"name\": \"motobikeTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 4\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1032,\n" +
                "            \"product\": {\n" +
                "                \"id\": 103,\n" +
                "                \"name\": \"camry\",\n" +
                "                \"model\": \"model1234\",\n" +
                "                \"brand\": \"toyota\",\n" +
                "                \"company\": null,\n" +
                "                \"price\": 15.0,\n" +
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 101,\n" +
                "                    \"name\": \"carTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 12\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        resultDeliveryNote = resultDeliveryNote.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/delivery-notes")
                        .contentType(APPLICATION_JSON)
                        .content(updatedDeliveryNote))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/delivery-notes/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(resultDeliveryNote));
    }

    @Test
    @Transactional
    // Expect DeliveryNote details will be deleted along with DeliveryNote
    public void deleteTest() throws Exception {
        // get DeliveryNote detail 9991 and 9992 BEFORE DeliveryNote 999 is deleted
        DeliveryNoteDetail deliveryNoteDetail9991 = sessionFactory.getCurrentSession().get(DeliveryNoteDetail.class, Long.valueOf(9991));
        DeliveryNoteDetail deliveryNoteDetail9992 = sessionFactory.getCurrentSession().get(DeliveryNoteDetail.class, Long.valueOf(9992));

        Assert.assertNotNull(deliveryNoteDetail9991);      // expect to find DeliveryNote detail with id 9991
        Assert.assertNotNull(deliveryNoteDetail9992);      // expect to find DeliveryNote detail with id 9992

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/delivery-notes/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/delivery-notes/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();
        Assert.assertTrue(resultBody.contains("The entity id not found"));

        // get DeliveryNote detail 9991 and 9992 AFTER DeliveryNote 999 is deleted
        deliveryNoteDetail9991 = sessionFactory.getCurrentSession().get(DeliveryNoteDetail.class, Long.valueOf(9991));
        deliveryNoteDetail9992 = sessionFactory.getCurrentSession().get(DeliveryNoteDetail.class, Long.valueOf(9992));

        Assert.assertNull(deliveryNoteDetail9991);      // expect not to find DeliveryNote detail with id 9991
        Assert.assertNull(deliveryNoteDetail9992);      // expect not to find DeliveryNote detail with id 9992
    }

    @Test
    public void searchTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/delivery-notes/search")
                        .param("field", "product")
                        .param("searchKey", "103")
                        .param("page", "1")
                        .param("size", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"date\": \"11-06-2019\",\n" +
                "            \"deliveryNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1001,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 101,\n" +
                "                        \"name\": \"bmw\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 2\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1002,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 103,\n" +
                "                        \"name\": \"camry\",\n" +
                "                        \"model\": \"model1234\",\n" +
                "                        \"brand\": \"toyota\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 15.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 4\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1003,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 101,\n" +
                "                        \"name\": \"bmw\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 3\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"date\": \"17-06-2019\",\n" +
                "            \"deliveryNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1011,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 102,\n" +
                "                        \"name\": \"exciter\",\n" +
                "                        \"model\": \"model2020\",\n" +
                "                        \"brand\": \"yamaha\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 100,\n" +
                "                            \"name\": \"motobikeTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 8\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1012,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 103,\n" +
                "                        \"name\": \"camry\",\n" +
                "                        \"model\": \"model1234\",\n" +
                "                        \"brand\": \"toyota\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 15.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 1\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));

        result = mockMvc.perform(
                MockMvcRequestBuilders.get("/delivery-notes/search")
                        .param("field", "date")
                        .param("searchKey", "2019-05-27"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        resultBody = result.getResponse().getContentAsString();

        resultString = " [\n" +
                "        {\n" +
                "            \"id\": 103,\n" +
                "            \"date\": \"27-05-2019\",\n" +
                "            \"deliveryNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1031,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 102,\n" +
                "                        \"name\": \"exciter\",\n" +
                "                        \"model\": \"model2020\",\n" +
                "                        \"brand\": \"yamaha\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 100,\n" +
                "                            \"name\": \"motobikeTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 4\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1032,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 103,\n" +
                "                        \"name\": \"camry\",\n" +
                "                        \"model\": \"model1234\",\n" +
                "                        \"brand\": \"toyota\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 15.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 12\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
    }

    @Test
    public void searchByPeriodTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/delivery-notes/search-by-period")
                        .param("startDate", "2019-06-11")
                        .param("endDate", "2019-06-30")
                        .param("page", "1")
                        .param("size", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = " \"content\": [\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"date\": \"11-06-2019\",\n" +
                "            \"deliveryNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1001,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 101,\n" +
                "                        \"name\": \"bmw\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 2\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1002,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 103,\n" +
                "                        \"name\": \"camry\",\n" +
                "                        \"model\": \"model1234\",\n" +
                "                        \"brand\": \"toyota\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 15.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 4\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1003,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 101,\n" +
                "                        \"name\": \"bmw\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 3\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"date\": \"17-06-2019\",\n" +
                "            \"deliveryNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1011,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 102,\n" +
                "                        \"name\": \"exciter\",\n" +
                "                        \"model\": \"model2020\",\n" +
                "                        \"brand\": \"yamaha\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 25.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 100,\n" +
                "                            \"name\": \"motobikeTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 8\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1012,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 103,\n" +
                "                        \"name\": \"camry\",\n" +
                "                        \"model\": \"model1234\",\n" +
                "                        \"brand\": \"toyota\",\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 15.0,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 101,\n" +
                "                            \"name\": \"carTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 1\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
    }
}
