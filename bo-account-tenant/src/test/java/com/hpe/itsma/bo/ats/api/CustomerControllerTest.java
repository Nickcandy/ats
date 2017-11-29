package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.ApplicationTests;
import com.hpe.itsma.bo.ats.service.domain.CustomerEntity;
import com.hpe.itsma.bo.common.exception.MsgConstants;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.hpe.itsma.bo.ats.api.CustomerController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sheyu on 2017/6/10.
 */
@WithMockUser(roles = "FARM_ADMIN")
@Transactional
@ActiveProfiles("test")
public class CustomerControllerTest extends ApplicationTests {

    @Test
    public void getCustomerByIdNotFoundNotExists() throws Exception {
        mvc.perform(get(URI_THE_CUSTOMER, 123456L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("reason").value("Cannot find record with entity type: Customer and id: 123456"))
                .andExpect(jsonPath("reasonMsgKey").value(MsgConstants.EXCEPTION_ENTITY_NOT_FOUND_MSG_KEY))
                .andExpect(jsonPath("msgArguments").isArray())
                .andExpect(jsonPath("msgArguments", Matchers.hasSize(2)))
                .andExpect(jsonPath("msgArguments", Matchers.contains("Customer", "123456")))
                .andExpect(jsonPath("success").value(false));
    }


    @Test
    public void getCustomerByIdNotFoundInvalid() throws Exception {
        mvc.perform(get(URI_THE_CUSTOMER, -100L))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("[\"Entity Property:id Param id must be greater than 0\"]"))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void getCustomerById() throws Exception {
        mvc.perform(get(URI_THE_CUSTOMER, 12345L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("fullName").value("testA"));
    }

    @Test
    public void getAllCustomers() throws Exception {
        mvc.perform(get(URI_CUSTOMERS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(new GreaterThan(0)));
    }

    @Test
    public void getAllCustomersBadParam() throws Exception {
        mvc.perform(get(URI_CUSTOMERS + "?limit=-2"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveACustomer() throws Exception{
        mvc.perform(post(URI_CUSTOMERS)
                .content("{\"fullName\": \"new customer @\",\"shortName\":\"yet another customer @\",\"status\":\"ACTIVE\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("new customer @"));
    }


    @Test
    public void updateACustomer() throws Exception{
        mvc.perform(put(URI_THE_CUSTOMER, 12345)
                .content("{\"id\": 12345, \"fullName\": \"yet another customer\",\"shortName\":\"yet another customer\",\"status\":\"ACTIVE\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("fullName").value("yet another customer"));
    }

    @Test
    public void updateACustomerInvalid() throws Exception{
        mvc.perform(put(URI_THE_CUSTOMER, -100L)
                .content("{\"id\": -100, \"fullName\": \"yet another customer\",\"shortName\":\"yet another customer\",\"status\":\"ACTIVE\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("[\"Entity Property:id Param id must be greater than 0\"]"))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void updateACustomerNotExists() throws Exception{
        mvc.perform(put(URI_THE_CUSTOMER, 123456)
                .content("{\"id\": 123456, \"fullName\": \"yet another customer\",\"shortName\":\"yet another customer\",\"status\":\"ACTIVE\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("reason").value("Cannot find record with entity type: Customer and id: 123456"))
                .andExpect(jsonPath("reasonMsgKey").value(MsgConstants.EXCEPTION_ENTITY_NOT_FOUND_MSG_KEY))
                .andExpect(jsonPath("msgArguments").isArray())
                .andExpect(jsonPath("msgArguments", Matchers.hasSize(2)))
                .andExpect(jsonPath("msgArguments", Matchers.contains("Customer", "123456")))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void updateACustomerMismatch() throws Exception{
        mvc.perform(put(URI_THE_CUSTOMER, 123456)
                .content("{\"id\": 654321, \"fullName\": \"yet another customer\",\"shortName\":\"yet another customer\",\"status\":\"ACTIVE\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("[\"Entity Property:<cross-parameter> Param id not eq with Request body Customer.Id\"]"));
    }

    @Test
    public void searchCustomers() throws Exception{
        mvc.perform(get(URI_URI_CUSTOMERS_PAGE_COUNT)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalCount").value(2));
    }

    @Test
    public void saveCustomerWithDuplicatedShortName() throws Exception {
        mvc.perform(post(URI_CUSTOMERS)
                .content("{\"name\": \"Customer #1\",\"fullName\": \"Customer #1\",\"shortName\":\"yet another customer\",\"status\":\"ACTIVE\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Customer #1"));
        mvc.perform(post(URI_CUSTOMERS)
                .content("{\"name\": \"Customer #1\",\"fullName\": \"Customer #1 full\",\"shortName\":\"yet another customer\",\"status\":\"ACTIVE\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("Customer short name (yet another customer) is taken."))
                .andExpect(jsonPath("reasonMsgKey").value("error.server.customer_shortname_not_unique"))
                .andExpect(jsonPath("msgArguments").value("yet another customer"))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void saveCustomerWithDuplicatedFullName() throws Exception {
        mvc.perform(post(URI_CUSTOMERS)
                .content("{\"name\": \"Customer #1\",\"fullName\": \"Customer #1\",\"shortName\":\"yet another customer G\",\"status\":\"ACTIVE\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Customer #1"));
        mvc.perform(post(URI_CUSTOMERS)
                .content("{\"name\": \"Customer #1\",\"fullName\": \"Customer #1\",\"shortName\":\"yet another customer H\",\"status\":\"ACTIVE\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("Customer full name (Customer #1) is taken."))
                .andExpect(jsonPath("reasonMsgKey").value("error.server.customer_fullname_not_unique"))
                .andExpect(jsonPath("msgArguments").value("Customer #1"))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void deleteCustomer() throws Exception{
        mvc.perform(get(URI_THE_CUSTOMER, 12345L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("deleted").value(false));
        mvc.perform(delete(URI_THE_CUSTOMER, 12345L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(get(URI_THE_CUSTOMER, 12345L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("deleted").value(true));
    }
}
