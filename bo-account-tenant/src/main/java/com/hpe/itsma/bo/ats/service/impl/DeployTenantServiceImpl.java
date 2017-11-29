package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.agent.BoLicenseAgent;
import com.hpe.itsma.bo.ats.agent.BoUserAgent;
import com.hpe.itsma.bo.ats.agent.SawTenantManageAgent;
import com.hpe.itsma.bo.ats.model.deploy.*;
import com.hpe.itsma.bo.ats.model.User2TenantRelationModel;
import com.hpe.itsma.bo.ats.service.AccountService;
import com.hpe.itsma.bo.ats.service.DeployTenantService;
import com.hpe.itsma.bo.ats.service.TenantService;
import com.hpe.itsma.bo.ats.service.domain.AccountEntity;
import com.hpe.itsma.bo.ats.service.domain.TenantEntity;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantState;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantType;
import com.hpe.itsma.bo.ats.service.repository.TenantEntityRepository;
import com.hpe.itsma.bo.ats.util.LicenseUtil;
import com.hpe.itsma.bo.common.api.MultipleChangeResult;
import com.hpe.itsma.bo.common.api.PageQueryModel;
import com.hpe.itsma.bo.common.api.UserJSON;
import com.hpe.itsma.bo.common.exception.BoConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxiaodon on 10/24/2017.
 */
@Service
public class DeployTenantServiceImpl implements DeployTenantService {

    @Value("${saw.integration.user.internal.username:}")
    private String sawIntegrationInternalUserName;

    @Value("${saw.integration.user.internal.password:}")
    private String sawIntegrationInternalPassword;

    @Value("${saw.integration.user.external.username:}")
    private String sawIntegrationExternalUserName;

    @Value("${saw.integration.user.external.password:}")
    private String sawIntegrationExternalPassword;

    @Value("${saw.callback.service.host}")
    private String host;

    @Value("${saw.callback.service.port}")
    private String port;

    @Value("${saw.callback.service.url}")
    private String url;

    @Autowired
    private SawTenantManageAgent sawAgent;

    @Autowired
    private BoUserAgent userAgent;

    @Autowired
    private BoLicenseAgent licenseAgent;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TenantEntityRepository tenantEntityRepository;

    private Logger logger = LoggerFactory.getLogger(DeployTenantServiceImpl.class);

    @Override
    public DeployTenantResponse deployTenant(Long tenantId, Long accountId, Long tenantAdminUserId) {
        TenantEntity tenant = tenantService.getTenantById(tenantId);
        RequestTenant requestTenant = new RequestTenant();
        requestTenant.setActive(true);
        requestTenant.setTenantId(String.valueOf(tenantId));
        requestTenant.setTenantName(tenant.getName());
        requestTenant.setCustomerId(String.valueOf(accountId));
        UserJSON customerUser = userAgent.getUserById(tenantAdminUserId);
        requestTenant.setCustomerUser(customerUser.getName());
        IntegrationUser integrationUser = new IntegrationUser(sawIntegrationInternalUserName, sawIntegrationInternalPassword);
        IntegrationUser integrationUserOfEx = new IntegrationUser(sawIntegrationExternalUserName, sawIntegrationExternalPassword);
        IntegrationUsers integrationUsers = new IntegrationUsers(integrationUser, integrationUserOfEx);
        requestTenant.setIntegrationUsers(integrationUsers);
        requestTenant.setLicenseDetails(setDeployTenantLicense(tenantId, tenant.getTenantType()));
        ProductExtendedInfo productExtendedInfo = new ProductExtendedInfo();
        switch (tenant.getTenantType()) {
            case INTERNAL:
                productExtendedInfo.setPurchaseStatus("internal");
                break;
            case TRIALPROVISION:
                requestTenant.setTenantType("Freemium");
                break;
            case PRODUCTION:
                productExtendedInfo.setEnvironment("Prod");
                break;
            default:
                requestTenant.setTenantType(String.valueOf(tenant.getTenantType()));
        }
        ExtendedInfo extendedInfo = new ExtendedInfo("", productExtendedInfo);
        List<ExtendedInfo> extendedInfos = new ArrayList<>();
        extendedInfos.add(extendedInfo);
        requestTenant.setExtendedInfo(extendedInfos);
        CallbackInformation callbackInformation = new CallbackInformation(host, port, url);
        requestTenant.setCallbackInformation(callbackInformation);
        addUserRoleForTenantInDeployProcess(tenantAdminUserId, tenant);
        tenantEntityRepository.updateTenantState(tenantId, TenantState.IN_PROVISION);
        return sawAgent.deployTenantToSaw(requestTenant);
    }

    private void addUserRoleForTenantInDeployProcess(Long tenantAdminUserId, TenantEntity tenantEntity) {
        User2TenantRelationModel user2TenantRelation = new User2TenantRelationModel();
        user2TenantRelation.setTenantID(tenantEntity.getId());
        List<Long> addRelation = new ArrayList<>();
        addRelation.add(tenantAdminUserId);
        if (tenantEntity.getOwnerId() != null && !tenantEntity.getOwnerId().isEmpty()) {
            addRelation.add(Long.valueOf(tenantEntity.getOwnerId()));
        }
        user2TenantRelation.setAdd(addRelation);
        user2TenantRelation.setDelete(new ArrayList<>());
        MultipleChangeResult relationResult = userAgent.updateTenantAndUserRelation(user2TenantRelation);
        logger.info("update tenant user relation result: {}", relationResult);
    }

    private void validatorDeployTenantLicense(PageQueryModel<LicenseActivityModel> licenseResult, TenantType tenantType) {
        boolean licenseIsCoincidence = !TenantType.TRIALPROVISION.equals(tenantType) && (licenseResult == null || licenseResult.getEntities() == null || licenseResult.getEntities().isEmpty());
        if (licenseIsCoincidence) {
            throw new BoConstraintViolationException("error.server.cannot_deploy_tenant_no_license", messageSource.getMessage("error.server.cannot_deploy_tenant_no_license", null, null));
        }
    }

    private List<LicenseDetail> setDeployTenantLicense(Long tenantId, TenantType tenantType) {
        List<LicenseDetail> licenseDetails = new ArrayList<>();
        PageQueryModel<LicenseActivityModel> licenseResult = licenseAgent.getLicensesByTenantId(tenantId);
        if (!TenantType.TRIALPROVISION.equals(tenantType)) {
            validatorDeployTenantLicense(licenseResult, tenantType);
            licenseResult.getEntities().forEach(item -> {
                LicenseObject licenseObject = new LicenseObject();
                switch (tenantType) {
                    case INTERNAL:
                        licenseObject.setLicenseType(LicenseUtil.getLicenseType(1));
                        break;
                    case DEV:
                        licenseObject.setLicenseType(LicenseUtil.getLicenseType(2));
                        break;
                    default:
                        licenseObject.setLicenseType(LicenseUtil.getLicenseType(item.getMode()));
                }
                licenseObject.setCapacity(item.getCapacity());
                licenseObject.setLicenseId(item.getId().toString());
                licenseObject.setSubscriptionId(item.getId().toString());
                licenseObject.setAutopassProductCode("Service Anywhere");
                licenseObject.setLicenseModel(LicenseUtil.getLicenseModel(item.getEdition(), item.getAccessType()));
                licenseObject.setStartDate(item.getStartDate());
                licenseObject.setExpirationDate(item.getEndDate());
                LicenseDetail licenseDetail = new LicenseDetail(item.getId().toString(), licenseObject);
                licenseDetails.add(licenseDetail);
            });
        }
        return licenseDetails;
    }

    @Override
    public void deployTenantCallback(DeployTenantResponse deployTenantResponse) {
        switch (deployTenantResponse.getStatus()) {
            case "Completed":
            case "Partially Created":
            case "Running":
                tenantEntityRepository.updateTenantState(Long.valueOf(deployTenantResponse.getTenantId()), TenantState.ACTIVE);
                tenantEntityRepository.setTenantLastActiveDate(Long.valueOf(deployTenantResponse.getTenantId()), LocalDateTime.now());
                break;
            case "Pre-Provisioned":
                tenantEntityRepository.updateTenantState(Long.valueOf(deployTenantResponse.getTenantId()), TenantState.INACTIVE);
                break;
            case "Failed":
                tenantEntityRepository.updateTenantState(Long.valueOf(deployTenantResponse.getTenantId()), TenantState.PENDING_FOR_REMOVAL);
                break;
            default:
                tenantEntityRepository.updateTenantState(Long.valueOf(deployTenantResponse.getTenantId()), TenantState.IN_PROVISION);
        }
    }
}
