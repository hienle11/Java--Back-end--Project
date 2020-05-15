import config.AppConfig;
import controller.OrderController;
import entity.OrderDetail;
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
public class OrderAPITest {

    @Autowired
    OrderController orderController;
    @Autowired
    SessionFactory sessionFactory;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void findPaginatedTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/orders")
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
                "            \"date\": \"09-05-2020\",\n" +
                "            \"staff\": {\n" +
                "                \"id\": 102,\n" +
                "                \"name\": \"maroon5\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"email\": \"xyz@gmail.com\"\n" +
                "            },\n" +
                "            \"provider\": {\n" +
                "                \"id\": 101,\n" +
                "                \"name\": \"edward\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": \"+111\",\n" +
                "                \"fax\": null,\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"orderDetails\": [\n" +
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
                "                    \"quantity\": 2,\n" +
                "                    \"price\": 50.0\n" +
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
                "                    \"quantity\": 1,\n" +
                "                    \"price\": 25.0\n" +
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
                "                    \"quantity\": 3,\n" +
                "                    \"price\": 45.0\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"date\": \"06-05-2020\",\n" +
                "            \"staff\": {\n" +
                "                \"id\": 101,\n" +
                "                \"name\": \"katy\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"email\": \"xyz@gmail.com\"\n" +
                "            },\n" +
                "            \"provider\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"david\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": \"+123\",\n" +
                "                \"fax\": null,\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"orderDetails\": [\n" +
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
                "                    \"quantity\": 10,\n" +
                "                    \"price\": 250.0\n" +
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
                "                    \"quantity\": 20,\n" +
                "                    \"price\": 500.0\n" +
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
                "        \"id\": 102,\n" +
                "        \"name\": \"maroon5\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"email\": \"xyz@gmail.com\"\n" +
                "    },\n" +
                "    \"provider\": {\n" +
                "        \"id\": 100,\n" +
                "        \"name\": \"david\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": \"+123\",\n" +
                "        \"fax\": null,\n" +
                "        \"email\": null,\n" +
                "        \"contactPerson\": null\n" +
                "    },\n" +
                "    \"orderDetails\": [\n" +
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
                "            \"quantity\": 1,\n" +
                "            \"price\": 15.0\n" +
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
                "            \"quantity\": 5,\n" +
                "            \"price\": 7.5\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        expectedString = expectedString.replaceAll("[\n ]", "");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test
    public void createTest() throws Exception {
            String newOrder = "{\n" +
                    // id is not provided
                    // date is not provided and will be automatically assigned to the current date
                    "    \"staff\": {\n" +
                    "        \"id\": 102\n" +
                    "    },\n" +
                    "    \"provider\": {\n" +
                    "        \"id\": 102\n" +
                    "    },\n" +
                    "    \"orderDetails\": [\n" +
                    "        {\n" +
                    "            \"product\": {\n" +
                    "                \"id\": 100\n" +
                    "            },\n" +
                    "            \"quantity\": 4,\n" +
                    "            \"price\": 8\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"product\": {\n" +
                    "                \"id\": 101\n" +
                    "            },\n" +
                    "            \"quantity\": 7,\n" +
                    "            \"price\": 160\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(APPLICATION_JSON)
                        .content(newOrder))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); // get the current date
        String expectedResult =  "{\n" +
                "    \"id\": 1,\n" +
                "    \"date\": \"" + date +"\",\n" +                               // date field expected to have the current date
                "    \"staff\": {\n" +
                "        \"id\": 102,\n" +
                "        \"name\": \"maroon5\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"email\": \"xyz@gmail.com\"\n" +
                "    },\n" +
                "    \"provider\": {\n" +
                "        \"id\": 102,\n" +
                "        \"name\": \"alex\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": \"+789\",\n" +
                "        \"fax\": null,\n" +
                "        \"email\": null,\n" +
                "        \"contactPerson\": null\n" +
                "    },\n" +
                "    \"orderDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
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
                "            \"quantity\": 4,\n" +
                "            \"price\": 8.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
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
                "            \"quantity\": 7,\n" +
                "            \"price\": 160.0\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        expectedResult = expectedResult.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void updateTest() throws Exception {
        String updatedOrder = "{\n" +
                "    \"id\": 103,\n" +
                "    \"staff\": {\n" +
                "        \"id\": 103\n" +           // update new Staff, currently is 102
                "    },\n" +
                "    \"provider\": {\n" +
                "        \"id\": 102\n" +           // update new Provider, currently is 100
                "    },\n" +
                "    \"orderDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1031,\n" +     // update order detail that has id 1031,
                "            \"product\": {\n" +    // order detail 1032 is not provided here and will not be updated
                "                \"id\": 100\n" +   // new product, currently product is 103
                "            },\n" +
                "            \"quantity\": 10,\n" +  // new quantity, currently is 10
                "            \"price\": 25\n" +  // new quantity, currently is 10
                "        }\n" +
                "    ]\n" +
                "}";

        String resultOrder = "{\n" +
                "    \"id\": 103,\n" +
                "    \"date\": \"11-05-2019\",\n" +
                "    \"staff\": {\n" +                  // new Staff
                "        \"id\": 103,\n" +
                "        \"name\": \"adam\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"email\": \"asd@gmail.com\"\n" +
                "    },\n" +
                "    \"provider\": {\n" +               // new provider
                "        \"id\": 102,\n" +
                "        \"name\": \"alex\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": \"+789\",\n" +
                "        \"fax\": null,\n" +
                "        \"email\": null,\n" +
                "        \"contactPerson\": null\n" +
                "    },\n" +
                "    \"orderDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1032,\n" +         // order detail 1032 remains the same info
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
                "            \"quantity\": 5,\n" +
                "            \"price\": 7.5\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1031,\n" +        // order detail 1031 is updated
                "            \"product\": {\n" +
                "                \"id\": 100,\n" +      // new product with id 100
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
                "            \"quantity\": 10,\n" +  // quantity is updated to 10
                "            \"price\": 25.0\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        resultOrder = resultOrder.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/orders")
                        .contentType(APPLICATION_JSON)
                        .content(updatedOrder))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(resultOrder));
    }

    @Test
    @Transactional
    // Expect order details will be deleted along with order
    public void deleteTest() throws Exception {
        // get order detail 9991 and 9992 BEFORE order 999 is deleted
        OrderDetail orderDetail9991 = sessionFactory.getCurrentSession().get(OrderDetail.class, Long.valueOf(9991));
        OrderDetail orderDetail9992 = sessionFactory.getCurrentSession().get(OrderDetail.class, Long.valueOf(9992));

        Assert.assertNotNull(orderDetail9991);      // expect to find order detail with id 9991
        Assert.assertNotNull(orderDetail9992);      // expect to find order detail with id 9992

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/orders/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();
        Assert.assertTrue(resultBody.contains("The entity id not found"));

        // get order detail 9991 and 9992 AFTER order 999 is deleted
        orderDetail9991 = sessionFactory.getCurrentSession().get(OrderDetail.class, Long.valueOf(9991));
        orderDetail9992 = sessionFactory.getCurrentSession().get(OrderDetail.class, Long.valueOf(9992));

        Assert.assertNull(orderDetail9991);      // expect not to find order detail with id 9991
        Assert.assertNull(orderDetail9992);      // expect not to find order detail with id 9992
    }

    @Test
    public void searchTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/search")
                        .param("field", "date")
                        .param("searchKey", "2019"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 102,\n" +
                "            \"date\": \"11-04-2019\",\n" +
                "            \"staff\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"ladygaga\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"email\": \"abc@gmail.com\"\n" +
                "            },\n" +
                "            \"provider\": {\n" +
                "                \"id\": 103,\n" +
                "                \"name\": \"alison\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": \"+111\",\n" +
                "                \"fax\": null,\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"orderDetails\": [\n" +
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
                "                    \"quantity\": 30,\n" +
                "                    \"price\": 450.0\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 103,\n" +
                "            \"date\": \"11-05-2019\",\n" +
                "            \"staff\": {\n" +
                "                \"id\": 102,\n" +
                "                \"name\": \"maroon5\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"email\": \"xyz@gmail.com\"\n" +
                "            },\n" +
                "            \"provider\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"david\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": \"+123\",\n" +
                "                \"fax\": null,\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"orderDetails\": [\n" +
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
                "                    \"quantity\": 1,\n" +
                "                    \"price\": 15.0\n" +
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
                "                    \"quantity\": 5,\n" +
                "                    \"price\": 7.5\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
    }
}
