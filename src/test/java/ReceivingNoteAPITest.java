import config.AppConfig;
import controller.ReceivingNoteController;
import entity.ReceivingNoteDetail;
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
public class ReceivingNoteAPITest {

    @Autowired
    ReceivingNoteController receivingNoteController;

    @Autowired
    SessionFactory sessionFactory;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(receivingNoteController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void findPaginatedTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/receiving-notes")
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
                "            \"date\": \"11-05-2020\",\n" +
                "            \"staff\": {\n" +
                "                \"id\": 103,\n" +
                "                \"name\": \"adam\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"email\": \"asd@gmail.com\"\n" +
                "            },\n" +
                "            \"order\": 100,\n" +
                "            \"receivingNoteDetails\": [\n" +
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
                "                    \"quantity\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1003,\n" +
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
                "                    \"quantity\": 3\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"date\": \"15-05-2020\",\n" +
                "            \"staff\": {\n" +
                "                \"id\": 103,\n" +
                "                \"name\": \"adam\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"email\": \"asd@gmail.com\"\n" +
                "            },\n" +
                "            \"order\": 101,\n" +
                "            \"receivingNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1011,\n" +
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
                "                    \"quantity\": 10\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1012,\n" +
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
                "                    \"quantity\": 20\n" +
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
                "    \"date\": \"11-05-2019\",\n" +
                "    \"staff\": {\n" +
                "        \"id\": 103,\n" +
                "        \"name\": \"adam\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"email\": \"asd@gmail.com\"\n" +
                "    },\n" +
                "    \"order\": 103,\n" +
                "    \"receivingNoteDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1031,\n" +
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
                "            \"quantity\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1032,\n" +
                "            \"product\": {\n" +
                "                \"id\": 101,\n" +
                "                \"name\": \"bmw\",\n" +
                "                \"model\": null,\n" +
                "                \"brand\": null,\n" +
                "                \"company\": null,\n" +
                "                \"price\": 25.0,\n" +
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 101,\n" +
                "                    \"name\": \"carTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 5\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        expectedString = expectedString.replaceAll("[\n ]", "");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/receiving-notes/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test
    public void createTest() throws Exception {
        String newReceivingNote = "{\n" +
                // id is not provided,
                // date is not provided and will be assigned automatically
                "    \"order\": {\n" +      // the user must enter the order that they receiving products from
                "    \t\"id\": 101\n" +     // receiving notes detail will be populate automatically with...
                "    },\n" +                // .. products and quantity data from the order
                "    \"staff\": {\n" +
                "    \t\"id\": 101\n" +
                "    }\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/receiving-notes")
                        .contentType(APPLICATION_JSON)
                        .content(newReceivingNote))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); // get the current date
        String expectedResult =  "{\n" +
                "    \"id\": 1,\n" +
                "    \"date\": \"" + date + "\",\n" +
                "    \"staff\": {\n" +
                "        \"id\": 101,\n" +
                "        \"name\": \"katy\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"email\": \"xyz@gmail.com\"\n" +
                "    },\n" +
                "    \"order\": 101,\n" +
                "    \"receivingNoteDetails\": [\n" +   //receiving note details automatically added with data from order
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"product\": {\n" +
                "                \"id\": 101,\n" +
                "                \"name\": \"bmw\",\n" +
                "                \"model\": null,\n" +
                "                \"brand\": null,\n" +
                "                \"company\": null,\n" +
                "                \"price\": 25.0,\n" +
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 101,\n" +
                "                    \"name\": \"carTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 10\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
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
                "            \"quantity\": 20\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        expectedResult = expectedResult.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/receiving-notes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void updateTest() throws Exception {
        String updatedReceivingNote = "{\n" +
                "\t\"id\": 103,\n" +
                "    \"staff\": {\n" +
                "    \t\"id\": 100\n" +         // change staff
                "    },\n" +
                "    \"order\": {\n" +          // order is provided but will not be changed
                "    \t\"id\": 102\n" +
                "    }\n" +
                "}";

        String resultReceivingNote = "{\n" +
                "    \"id\": 103,\n" +
                "    \"date\": \"11-05-2019\",\n" +
                "    \"staff\": {\n" +
                "        \"id\": 100,\n" +
                "        \"name\": \"ladygaga\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"email\": \"abc@gmail.com\"\n" +
                "    },\n" +
                "    \"order\": 103,\n" +
                "    \"receivingNoteDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1031,\n" +
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
                "            \"quantity\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1032,\n" +
                "            \"product\": {\n" +
                "                \"id\": 101,\n" +
                "                \"name\": \"bmw\",\n" +
                "                \"model\": null,\n" +
                "                \"brand\": null,\n" +
                "                \"company\": null,\n" +
                "                \"price\": 25.0,\n" +
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 101,\n" +
                "                    \"name\": \"carTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 5\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        resultReceivingNote = resultReceivingNote.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/receiving-notes")
                        .contentType(APPLICATION_JSON)
                        .content(updatedReceivingNote))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/receiving-notes/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(resultReceivingNote));
    }

    @Test
    @Transactional
    // Expect ReceivingNote details will be deleted along with ReceivingNote
    public void deleteTest() throws Exception {
        // get ReceivingNote detail 9991 and 9992 BEFORE ReceivingNote 999 is deleted
        ReceivingNoteDetail receivingNoteDetail9991 = sessionFactory.getCurrentSession().get(ReceivingNoteDetail.class, Long.valueOf(9991));
        ReceivingNoteDetail receivingNoteDetail9992 = sessionFactory.getCurrentSession().get(ReceivingNoteDetail.class, Long.valueOf(9992));

        Assert.assertNotNull(receivingNoteDetail9991);      // expect to find ReceivingNote detail with id 9991
        Assert.assertNotNull(receivingNoteDetail9992);      // expect to find ReceivingNote detail with id 9992

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/receiving-notes/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/receiving-notes/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();
        Assert.assertTrue(resultBody.contains("The entity id not found"));

        // get ReceivingNote detail 9991 and 9992 AFTER ReceivingNote 999 is deleted
        receivingNoteDetail9991 = sessionFactory.getCurrentSession().get(ReceivingNoteDetail.class, Long.valueOf(9991));
        receivingNoteDetail9992 = sessionFactory.getCurrentSession().get(ReceivingNoteDetail.class, Long.valueOf(9992));

        Assert.assertNull(receivingNoteDetail9991);      // expect not to find ReceivingNote detail with id 9991
        Assert.assertNull(receivingNoteDetail9992);      // expect not to find ReceivingNote detail with id 9992
    }

    @Test
    public void searchTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/receiving-notes/search")
                        .param("field", "date")
                        .param("searchKey", "2019"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 102,\n" +
                "            \"date\": \"07-05-2019\",\n" +
                "            \"staff\": {\n" +
                "                \"id\": 103,\n" +
                "                \"name\": \"adam\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"email\": \"asd@gmail.com\"\n" +
                "            },\n" +
                "            \"order\": 102,\n" +
                "            \"receivingNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1021,\n" +
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
                "                    \"quantity\": 30\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 103,\n" +
                "            \"date\": \"11-05-2019\",\n" +
                "            \"staff\": {\n" +
                "                \"id\": 103,\n" +
                "                \"name\": \"adam\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"email\": \"asd@gmail.com\"\n" +
                "            },\n" +
                "            \"order\": 103,\n" +
                "            \"receivingNoteDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1031,\n" +
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
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1032,\n" +
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
                "                    \"quantity\": 5\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
    }
}
