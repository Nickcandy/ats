package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.ApplicationTests;
import com.hpe.itsma.bo.common.exception.MsgConstants;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import static com.hpe.itsma.bo.ats.api.AccountController.URI_ACCOUNTS;
import static com.hpe.itsma.bo.ats.api.AccountController.URI_THE_ACCOUNT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sheyu on 2017/6/10.
 */
@WithMockUser(roles = "FARM_ADMIN")
@Transactional
@ActiveProfiles("test")
public class AccountControllerTest extends ApplicationTests {

    @Test
    public void getAccountByIdNotFoundNotExists() throws Exception {
        mvc.perform(get(URI_THE_ACCOUNT, 123456L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("reason").value("Cannot find record with entity type: Account and id: 123456"))
                .andExpect(jsonPath("reasonMsgKey").value(MsgConstants.EXCEPTION_ENTITY_NOT_FOUND_MSG_KEY))
                .andExpect(jsonPath("msgArguments").isArray())
                .andExpect(jsonPath("msgArguments", Matchers.hasSize(2)))
                .andExpect(jsonPath("msgArguments", Matchers.contains("Account", "123456")))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void getAccountByIdNotFoundInvalid() throws Exception {
        mvc.perform(get(URI_THE_ACCOUNT, -100L))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("[\"Entity Property:id Param id must be greater than 0\"]"))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void getAccountById() throws Exception {
        mvc.perform(get(URI_THE_ACCOUNT, 12345L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("testA"));
    }

    @Test
    public void getAllAccounts() throws Exception {
        mvc.perform(get(URI_ACCOUNTS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(new GreaterThan(0)));
    }

    @Test
    public void getAllAccountsBadParam() throws Exception {
        mvc.perform(get(URI_ACCOUNTS + "?limit=-2"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void saveAnAccount() throws Exception{
        mvc.perform(post(URI_ACCOUNTS)
                .content("{\"name\": \"new account #2\",  \"accountType\": \"TEST\", \"customer\": \"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("new account #2"));
    }

    @Test
    public void updateAnAccount() throws Exception{
        mvc.perform(put(URI_THE_ACCOUNT, 12345)
                .content("{\"id\": 12345, \"name\": \"yet another account\", \"accountType\": \"TEST\", \"customer\": \"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("yet another account"));
    }

    @Test
    public void updateAnAccountInvalid() throws Exception{
        mvc.perform(put(URI_THE_ACCOUNT, -100L)
                .content("{\"id\": -100, \"name\": \"yet another account\", \"accountType\": \"TEST\", \"customer\": \"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("[\"Entity Property:id Param id must be greater than 0\"]"))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void updateAnAccountNotExists() throws Exception{
        mvc.perform(put(URI_THE_ACCOUNT, 123456L).header("corelationId","updateAnAccountNotExists")
                .content("{\"id\": 123456, \"name\": \"yet another account\", \"accountType\": \"TEST\", \"customer\": \"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("reason").value("Cannot find record with entity type: Account and id: 123456"))
                .andExpect(jsonPath("reasonMsgKey").value(MsgConstants.EXCEPTION_ENTITY_NOT_FOUND_MSG_KEY))
                .andExpect(jsonPath("msgArguments").isArray())
                .andExpect(jsonPath("msgArguments", Matchers.hasSize(2)))
                .andExpect(jsonPath("msgArguments", Matchers.contains("Account", "123456")))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void saveAccountsWithDuplicateName() throws Exception{
        mvc.perform(post(URI_ACCOUNTS)
                .content("{\"name\": \"new account #1\",  \"accountType\": \"TEST\", \"customer\": \"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("new account #1"));
        mvc.perform(post(URI_ACCOUNTS)
                .content("{\"name\": \"new account #1\",  \"accountType\": \"TEST\", \"customer\": \"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("Account name (new account #1) is taken."))
                .andExpect(jsonPath("reasonMsgKey").value("error.server.account_name_not_unique"))
                .andExpect(jsonPath("msgArguments").value("new account #1"))
                .andExpect(jsonPath("success").value(false));
    }

//    @Test
//    public void deleteAccountById() throws Exception {
//        mvc.perform(get(URI_THE_ACCOUNT, 12345L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("deleted").value(false));
//
//        mvc.perform(delete(URI_THE_ACCOUNT, 12345L))
//                .andExpect(status().isOk());
//        mvc.perform(get(URI_THE_ACCOUNT, 12345L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("deleted").value(true));
//    }

}
