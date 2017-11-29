package com.hpe.itsma.bo.ats.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.itsma.bo.ats.ApplicationTests;
import com.hpe.itsma.bo.ats.model.deploy.*;
import com.hpe.itsma.bo.common.exception.MsgConstants;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.hpe.itsma.bo.ats.api.TenantsController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by sheyu on 2017/6/10.
 */
@WithMockUser(roles = "FARM_ADMIN")
@Transactional
@ActiveProfiles("test")
public class TenantsControllerTest extends ApplicationTests {

    @Test
    public void getAllTenants() throws Exception {
        mvc.perform(get(URI_TENANTS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(new GreaterThan(0)));
    }

    @Test
    public void getAllTenantsBadParam() throws Exception {
        mvc.perform(get(URI_TENANTS + "?limit=-2"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()").value(new GreaterThan(0)));
    }

    @Test
    public void getTenantByIdNotExists() throws Exception {
        mvc.perform(get(URI_THE_TENANT, 123456L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("reason").value("Cannot find record with entity type: Tenant and id: 123456"))
                .andExpect(jsonPath("reasonMsgKey").value(MsgConstants.EXCEPTION_ENTITY_NOT_FOUND_MSG_KEY))
                .andExpect(jsonPath("msgArguments").isArray())
                .andExpect(jsonPath("msgArguments", Matchers.hasSize(2)))
                .andExpect(jsonPath("msgArguments", Matchers.contains("Tenant", "123456")))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void getTenantByIdInvalidId() throws Exception {
        mvc.perform(get(URI_THE_TENANT, -100L))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("[\"Entity Property:id Param id must be greater than 0\"]"))
                .andExpect(jsonPath("success").value(false));;
    }

    @Test
    public void getTenantById() throws Exception {
        mvc.perform(get(URI_THE_TENANT, 12345L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("testA"))
                .andExpect(jsonPath("displayLabel").value("test ats a"))
                .andExpect(jsonPath("description").value("just a test ats A"));
    }

    @Test
    public void saveATenant() throws Exception{
        mvc.perform(post(URI_TENANTS)
                .content("{\"accountId\": 12344, \"name\": \"tenant 1\",\"name\": \"tenant 1\", \"tenantType\": \"DEV\", \"tenantState\": \"NEW\", \"tenantAccessControl\": \"LIMITED\", \"productInstance\": \"20004\", \"suiteInstance\": \"30004\", \"namespace\": \"100003\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("tenant 1"));
    }

    @Test
    public void searchTenants() throws Exception{
        mvc.perform(get(URI_TENANTS_SEARCH)
                .param("limit", "20")
                .param("keywords", "test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(new GreaterThan(0)));
    }

    @Test
    public void searchTenantsBadParam() throws Exception{
        mvc.perform(get(URI_TENANTS_SEARCH)
                .param("limit", "-2")
                .param("keywords", "test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateTenant() throws Exception{
        mvc.perform(put(URI_THE_TENANT, 12345L)
                .content("{\"id\": \"12345\", \"accountId\": 12344, \"name\": \"tenant 1 updated\",\"description\": \"tenant 1\", \"tenantType\": \"DEV\", \"tenantState\": \"NEW\", \"tenantAccessControl\": \"LIMITED\", \"productInstance\": \"20004\", \"suiteInstance\": \"30004\", \"namespace\": \"100003\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("tenant 1 updated"));
    }


    @Test
    public void updateTenantInvalid() throws Exception{
        mvc.perform(put(URI_THE_TENANT, -100L)
                .content("{\"account\": 12344, \"id\": -100, \"name\": \"yet another tenant\", \"creatorId\": 12344, \"tenantType\": \"DEV\", \"tenantState\": \"NEW\", \"tenantAccessControl\": \"LIMITED\", \"productInstance\": \"20004\", \"suiteInstance\": \"30004\", \"namespace\": \"100003\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("reason").value("[\"Entity Property:id Param id must be greater than 0\"]"))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void updateTenantNotExists() throws Exception{
        mvc.perform(put(URI_THE_TENANT, 123456L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"account\": 12344, \"id\": 123456, \"name\": \"yet another tenant\", \"creatorId\": 12344, \"tenantType\": \"DEV\", \"tenantState\": \"NEW\", \"tenantAccessControl\": \"LIMITED\", \"productInstance\": \"20004\", \"suiteInstance\": \"30004\", \"namespace\": \"100003\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("reason").value("Cannot find record with entity type: Tenant and id: 123456"))
                .andExpect(jsonPath("reasonMsgKey").value(MsgConstants.EXCEPTION_ENTITY_NOT_FOUND_MSG_KEY))
                .andExpect(jsonPath("msgArguments").isArray())
                .andExpect(jsonPath("msgArguments", Matchers.hasSize(2)))
                .andExpect(jsonPath("msgArguments", Matchers.contains("Tenant", "123456")))
                .andExpect(jsonPath("success").value(false));
    }

    public void run() {
        RequestTenant requestTenant = new RequestTenant();
        requestTenant.setTenantType("Trial");
        requestTenant.setActive(true);
        requestTenant.setTenantId(String.valueOf(10000));
        requestTenant.setTenantName("test");
        requestTenant.setCustomerId("10000");
        requestTenant.setCustomerUser("sysadmin");
        IntegrationUser integrationUser = new IntegrationUser("","");
        IntegrationUser integrationUserOfEx = new IntegrationUser("","");
        IntegrationUsers integrationUsers = new IntegrationUsers(integrationUser, integrationUserOfEx);
        requestTenant.setIntegrationUsers(integrationUsers);
        LicenseObject licenseObject = new LicenseObject();
        LicenseDetail licenseDetail = new LicenseDetail("1234-5678-ASDF-BVCX-SDFG-3452", licenseObject);
        List<LicenseDetail> licenseDetails = new ArrayList<>();
        licenseDetails.add(licenseDetail);
        requestTenant.setLicenseDetails(licenseDetails);
        ProductExtendedInfo productExtendedInfo = new ProductExtendedInfo();
        productExtendedInfo.setEnvironment("Trial");
        ExtendedInfo extendedInfo = new ExtendedInfo("", productExtendedInfo);
        List<ExtendedInfo> extendedInfos = new ArrayList<>();
        extendedInfos.add(extendedInfo);
        requestTenant.setExtendedInfo(extendedInfos);
        CallbackInformation callbackInformation = new CallbackInformation();
        callbackInformation.setHost("localhost");
        callbackInformation.setPort("9090");
        callbackInformation.setUrl("1234");
        requestTenant.setCallbackInformation(callbackInformation);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(System.out, requestTenant);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void saveATenantWithDuplicateName() throws Exception{
        mvc.perform(post(URI_TENANTS)
                .content("{\"accountId\": 12344, \"name\": \"tenant #1\",\"name\": \"tenant 1\", \"tenantType\": \"DEV\", \"tenantState\": \"NEW\", \"tenantAccessControl\": \"LIMITED\", \"productInstance\": \"20004\", \"suiteInstance\": \"30004\", \"namespace\": \"100003\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("tenant 1"));
        mvc.perform(post(URI_TENANTS)
                .content("{\"accountId\": 12344, \"name\": \"tenant #1\",\"name\": \"tenant 1\", \"tenantType\": \"DEV\", \"tenantState\": \"NEW\", \"tenantAccessControl\": \"LIMITED\", \"productInstance\": \"20004\", \"suiteInstance\": \"30004\", \"namespace\": \"100003\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("reason").value("Tenant name (tenant 1) is taken."))
                .andExpect(jsonPath("reasonMsgKey").value("error.server.tenant_name_not_unique"))
                .andExpect(jsonPath("msgArguments").value("tenant 1"))
                .andExpect(jsonPath("success").value(false));
    }

    @Test
    public void deleteTenantById() throws Exception {
        mvc.perform(get(URI_THE_TENANT, 12345L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("deleted").value(false));
        mvc.perform(delete(URI_THE_TENANT, 12345L))
                .andExpect(status().isOk());
        mvc.perform(get(URI_THE_TENANT, 12345L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("deleted").value(true));

    }
}
