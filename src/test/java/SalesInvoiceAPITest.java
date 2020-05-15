import config.AppConfig;
import controller.SalesInvoiceController;
import entity.SalesInvoiceDetail;
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
public class SalesInvoiceAPITest {

    @Autowired
    SalesInvoiceController salesInvoiceController;

    @Autowired
    SessionFactory sessionFactory;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(salesInvoiceController)
                .setControllerAdvice(new EntityExceptionHandler ()).build();
    }

    @Test
    public void findPaginatedTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices")
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
                "            \"staff\": \"ladygaga\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"edison\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"1111\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 160.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
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
                "                    \"subTotal\": 50.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1002,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 100,\n" +
                "                        \"name\": \"winner\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 2.5,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 100,\n" +
                "                            \"name\": \"motobikeTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 10.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1003,\n" +
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
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 100.0\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"date\": \"17-06-2019\",\n" +
                "            \"staff\": \"katy\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 102,\n" +
                "                \"name\": \"messi\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"3333\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 105.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
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
                "                    \"quantity\": 3,\n" +
                "                    \"subTotal\": 75.0\n" +
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
                "                    \"quantity\": 2,\n" +
                "                    \"subTotal\": 30.0\n" +
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
                "    \"staff\": \"adam\",\n" +
                "    \"customer\": {\n" +
                "        \"id\": 103,\n" +
                "        \"name\": \"ronaldo\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"fax\": \"4444\",\n" +
                "        \"email\": null,\n" +
                "        \"contactPerson\": null\n" +
                "    },\n" +
                "    \"total\": 75.0,\n" +
                "    \"salesInvoiceDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1031,\n" +
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
                "            \"quantity\": 10,\n" +
                "            \"subTotal\": 25.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1032,\n" +
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
                "            \"quantity\": 2,\n" +
                "            \"subTotal\": 50.0\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        expectedString = expectedString.replaceAll("[\n ]", "");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test
    public void createTest() throws Exception {
        String newSalesInvoice = "{\n" +
                // date is not provided and will be added automatically
                "\t\"staff\": {\n" +
                "\t\t\"id\": 101\n" +
                "\t},\n" +
                "\t\"customer\": {\n" +
                "\t\t\"id\": 103\n" +
                "\t},\n" +
                // total cost of the invoice is not added but will be automatically calculated
                "    \"salesInvoiceDetails\": [\n" +
                "        {\n" +
                "            \"product\": {\n" +
                "                \"id\": 102\n" +
                "            },\n" +
                "            \"quantity\": 11\n" +
                            // subTotal of each invoice details is calculated by product id multiplied by quantity
                "        },\n" +
                "        {\n" +
                "            \"product\": {\n" +
                "                \"id\": 100\n" +
                "            },\n" +
                "            \"quantity\": 22\n" +
                            // subTotal of each invoice details is calculated by product id multiplied by quantity
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/sales-invoices")
                        .contentType(APPLICATION_JSON)
                        .content(newSalesInvoice))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); // get the current date
        String expectedResult =  "{\n" +
                "    \"id\": 1,\n" +
                "    \"date\": \"" + date + "\",\n" +
                "    \"staff\": \"katy\",\n" +
                "    \"customer\": {\n" +
                "        \"id\": 103,\n" +
                "        \"name\": \"ronaldo\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"fax\": \"4444\",\n" +
                "        \"email\": null,\n" +
                "        \"contactPerson\": null\n" +
                "    },\n" +
                "    \"total\": 330.0,\n" +             // total amount is calculated
                "    \"salesInvoiceDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"product\": {\n" +
                "                \"id\": 102,\n" +
                "                \"name\": \"exciter\",\n" +
                "                \"model\": \"model2020\",\n" +
                "                \"brand\": \"yamaha\",\n" +
                "                \"company\": null,\n" +
                "                \"price\": 25.0,\n" +      // price of product
                "                \"description\": null,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 100,\n" +
                "                    \"name\": \"motobikeTest\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"quantity\": 11,\n" +
                "            \"subTotal\": 275.0\n" +         // subTotal of an invoice detail = price of product * quantity
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
                "            \"quantity\": 22,\n" +
                "            \"subTotal\": 55.0\n" +       // subTotal of an invoice detail = price of product * quantity
                "        }\n" +
                "    ]\n" +
                "}";
        expectedResult = expectedResult.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void updateTest() throws Exception {
        String updatedSalesInvoice = "{\n" +
                "\t\"id\": 103,\n" +
                "    \"salesInvoiceDetails\": [\n" +
                "    \t{\n" +
                "    \t\t\"id\": 1031,\n" +
                "    \t\t\"subTotal\": 20\n" +
                "    \t},\n" +
                "    \t{\n" +
                "    \t\t\"product\": {\n" +
                "    \t\t\t\"id\": 102\n" +
                "    \t\t},\n" +
                "    \t\t\"quantity\": 8\n" +
                "    \t}\n" +
                "    ]\n" +
                "}";

        String resultSalesInvoice = "{\n" +
                "    \"id\": 103,\n" +
                "    \"date\": \"27-05-2019\",\n" +
                "    \"staff\": \"adam\",\n" +          // staff remains the same
                "    \"customer\": {\n" +               // customer remains the same
                "        \"id\": 103,\n" +
                "        \"name\": \"ronaldo\",\n" +
                "        \"address\": null,\n" +
                "        \"phone\": null,\n" +
                "        \"fax\": \"4444\",\n" +
                "        \"email\": null,\n" +
                "        \"contactPerson\": null\n" +
                "    },\n" +
                "    \"total\": 270.0,\n" +             // new total amount
                "    \"salesInvoiceDetails\": [\n" +
                "        {\n" +
                "            \"id\": 1032,\n" +         // sale invoice detail 1032 remains the dame
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
                "            \"quantity\": 2,\n" +
                "            \"subTotal\": 50.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1,\n" +        // new invoice detail is added with id 1
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
                "            \"quantity\": 8,\n" +
                "            \"subTotal\": 200.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1031,\n" +
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
                "            \"quantity\": 10,\n" +
                "            \"subTotal\": 20.0\n" +           // subTotal of invoice detail 1031 is changed from 25 to 20
                "        }\n" +
                "    ]\n" +
                "}";
        resultSalesInvoice = resultSalesInvoice.replaceAll("[\n ]", "");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/sales-invoices")
                        .contentType(APPLICATION_JSON)
                        .content(updatedSalesInvoice))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/103"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(resultSalesInvoice));
    }

    @Test
    @Transactional
    // Expect SalesInvoice details will be deleted along with SalesInvoice
    public void deleteTest() throws Exception {
        // get SalesInvoice detail 9991 and 9992 BEFORE SalesInvoice 999 is deleted
        SalesInvoiceDetail salesInvoiceDetail9991 = sessionFactory.getCurrentSession().get(SalesInvoiceDetail.class, Long.valueOf(9991));
        SalesInvoiceDetail salesInvoiceDetail9992 = sessionFactory.getCurrentSession().get(SalesInvoiceDetail.class, Long.valueOf(9992));

        Assert.assertNotNull(salesInvoiceDetail9991);      // expect to find SalesInvoice detail with id 9991
        Assert.assertNotNull(salesInvoiceDetail9992);      // expect to find SalesInvoice detail with id 9992

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/sales-invoices/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();
        Assert.assertTrue(resultBody.contains("The entity id not found"));

        // get SalesInvoice detail 9991 and 9992 AFTER SalesInvoice 999 is deleted
        salesInvoiceDetail9991 = sessionFactory.getCurrentSession().get(SalesInvoiceDetail.class, Long.valueOf(9991));
        salesInvoiceDetail9992 = sessionFactory.getCurrentSession().get(SalesInvoiceDetail.class, Long.valueOf(9992));

        Assert.assertNull(salesInvoiceDetail9991);      // expect not to find SalesInvoice detail with id 9991
        Assert.assertNull(salesInvoiceDetail9992);      // expect not to find SalesInvoice detail with id 9992
    }

    @Test
    public void searchTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/search")
                        .param("field", "customer")
                        .param("searchKey", "102"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"date\": \"17-06-2019\",\n" +
                "            \"staff\": \"katy\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 102,\n" +
                "                \"name\": \"messi\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"3333\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 105.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
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
                "                    \"quantity\": 3,\n" +
                "                    \"subTotal\": 75.0\n" +
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
                "                    \"quantity\": 2,\n" +
                "                    \"subTotal\": 30.0\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));

        result = mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/search")
                        .param("field", "product")
                        .param("searchKey", "101")
                        .param("size","1")
                        .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        resultBody = result.getResponse().getContentAsString();

        resultString = " \"content\": [\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"date\": \"11-06-2019\",\n" +
                "            \"staff\": \"ladygaga\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"edison\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"1111\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 160.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
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
                "                    \"subTotal\": 50.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1002,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 100,\n" +
                "                        \"name\": \"winner\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 2.5,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 100,\n" +
                "                            \"name\": \"motobikeTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 10.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1003,\n" +
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
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 100.0\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));

        result = mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/search")
                        .param("field", "date")
                        .param("searchKey", "2019-06-21"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        resultBody = result.getResponse().getContentAsString();

        resultString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 102,\n" +
                "            \"date\": \"21-06-2019\",\n" +
                "            \"staff\": \"katy\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"edison\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"1111\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 100.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1021,\n" +
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
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 100.0\n" +
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
                MockMvcRequestBuilders.get("/sales-invoices/search-by-period")
                        .param("startDate", "2019-06-11")
                        .param("endDate", "2019-06-30")
                        .param("page", "1")
                        .param("size", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"date\": \"11-06-2019\",\n" +
                "            \"staff\": \"ladygaga\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"edison\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"1111\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 160.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
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
                "                    \"subTotal\": 50.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1002,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 100,\n" +
                "                        \"name\": \"winner\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 2.5,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 100,\n" +
                "                            \"name\": \"motobikeTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 10.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1003,\n" +
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
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 100.0\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 101,\n" +
                "            \"date\": \"17-06-2019\",\n" +
                "            \"staff\": \"katy\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 102,\n" +
                "                \"name\": \"messi\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"3333\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 105.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
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
                "                    \"quantity\": 3,\n" +
                "                    \"subTotal\": 75.0\n" +
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
                "                    \"quantity\": 2,\n" +
                "                    \"subTotal\": 30.0\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));

        result = mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/search-by-period")
                        .param("startDate", "2019-06-11")
                        .param("endDate", "2019-06-30")
                        .param("field", "customer")
                        .param("searchKey", "100")
                        .param("page", "1")
                        .param("size", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        resultBody = result.getResponse().getContentAsString();

        resultString = "\"content\": [\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"date\": \"11-06-2019\",\n" +
                "            \"staff\": \"ladygaga\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"edison\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"1111\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 160.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
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
                "                    \"subTotal\": 50.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1002,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 100,\n" +
                "                        \"name\": \"winner\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 2.5,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 100,\n" +
                "                            \"name\": \"motobikeTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 10.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1003,\n" +
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
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 100.0\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 102,\n" +
                "            \"date\": \"21-06-2019\",\n" +
                "            \"staff\": \"katy\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"edison\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"1111\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 100.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
                "                {\n" +
                "                    \"id\": 1021,\n" +
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
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 100.0\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
        result = mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/search-by-period")
                        .param("startDate", "2019-06-11")
                        .param("endDate", "2019-06-30")
                        .param("field", "staff")
                        .param("searchKey", "100")
                        .param("page", "1")
                        .param("size", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        resultBody = result.getResponse().getContentAsString();

        resultString = "[\n" +
                "        {\n" +
                "            \"id\": 100,\n" +
                "            \"date\": \"11-06-2019\",\n" +
                "            \"staff\": \"ladygaga\",\n" +
                "            \"customer\": {\n" +
                "                \"id\": 100,\n" +
                "                \"name\": \"edison\",\n" +
                "                \"address\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"fax\": \"1111\",\n" +
                "                \"email\": null,\n" +
                "                \"contactPerson\": null\n" +
                "            },\n" +
                "            \"total\": 160.0,\n" +
                "            \"salesInvoiceDetails\": [\n" +
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
                "                    \"subTotal\": 50.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1002,\n" +
                "                    \"product\": {\n" +
                "                        \"id\": 100,\n" +
                "                        \"name\": \"winner\",\n" +
                "                        \"model\": null,\n" +
                "                        \"brand\": null,\n" +
                "                        \"company\": null,\n" +
                "                        \"price\": 2.5,\n" +
                "                        \"description\": null,\n" +
                "                        \"category\": {\n" +
                "                            \"id\": 100,\n" +
                "                            \"name\": \"motobikeTest\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 10.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1003,\n" +
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
                "                    \"quantity\": 4,\n" +
                "                    \"subTotal\": 100.0\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],";
        resultString = resultString.replaceAll("[\n ]", "");
        Assert.assertTrue(resultBody.contains(resultString));
    }

    @Test
    public void getTotalRevenueTest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/revenue")
                        .param("startDate", "2019-06-11")
                        .param("endDate", "2019-06-30")
                        .param("field", "staff")
                        .param("searchKey", "101"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultBody = result.getResponse().getContentAsString();

        String resultString = "{\n" +
                "    \"description\": \"total revenue of staff id: 101\",\n" +
                "    \"period\": \"from 2019-06-11 to 2019-06-30\",\n" +
                "    \"totalRevenue\": 205.0\n" +
                "}";

        resultString = resultString.replaceAll("[\n ]", "");
        resultBody = resultBody.replaceAll(" ", "");

        Assert.assertTrue(resultBody.contains(resultString));

        result = mockMvc.perform(
                MockMvcRequestBuilders.get("/sales-invoices/revenue")
                        .param("startDate", "2019-06-11")
                        .param("endDate", "2019-06-30")
                        .param("field", "customer")
                        .param("searchKey", "100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        resultBody = result.getResponse().getContentAsString();

        resultString = "{\n" +
                "    \"description\": \"total revenue of customer id: 100\",\n" +
                "    \"period\": \"from 2019-06-11 to 2019-06-30\",\n" +
                "    \"totalRevenue\": 260.0\n" +
                "}";

        resultString = resultString.replaceAll("[\n ]", "");
        resultBody = resultBody.replaceAll(" ", "");

        Assert.assertTrue(resultBody.contains(resultString));
    }
}
