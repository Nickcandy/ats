package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.model.express.AccountExpress;
import com.hpe.itsma.bo.ats.model.Account;
import com.hpe.itsma.bo.ats.model.IDMOrg;
import com.hpe.itsma.bo.ats.service.domain.enumeration.IntegrationType;
import com.hpe.itsma.bo.ats.util.ATSConstants;
import com.hpe.itsma.bo.ats.service.AccountService;
import com.hpe.itsma.bo.ats.service.domain.AccountEntity;
import com.hpe.itsma.bo.ats.service.domain.enumeration.OrgType;
import com.hpe.itsma.bo.ats.util.OrgUtil;
import com.hpe.itsma.bo.common.api.PageQueryModel;
import com.hpe.itsma.bo.common.controller.BaseController;
import com.hpe.itsma.bo.common.exception.BoInvalidParameterException;
import com.hpe.itsma.bo.common.page.PageConstant;
import com.hpe.itsma.bo.common.utils.ConvertUtilities;
import io.swagger.annotations.*;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
@Api(value = "accounts Service API", description = "the accounts domain API")
@Validated
@RequestMapping("/accounts")
public class AccountController extends BaseController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    public static final String ACCOUNT_ID = "accountId";
    public static final String URI_ACCOUNTS = "/accounts/";
    public static final String URI_THE_ACCOUNT = URI_ACCOUNTS + "{" + ACCOUNT_ID + "}";
    public static final String SEARCH_PARAM_NAME = "name";

    @Autowired
    private AccountService accountService;

    @Autowired
    private ConvertUtilities convertUtilities;

    @ApiOperation(notes = "id must be integer", value = "Find account by ID", nickname = "getAccountById", tags = {"Accounts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Account.class),
            @ApiResponse(code = 400, message = "Invalid account Id"),
            @ApiResponse(code = 404, message = "Account not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAccountById(
            @ApiParam(value = "ID of account that needs to be fetched", required = true)
            @Min(value = 0, message = "Param id must be greater than 0")
            @PathVariable("id") Long id) {
        Account account = convertUtilities.entity2Model(accountService.getAccountById(id), Account.class);
        return ResponseEntity.ok(account);
    }

    @ApiOperation(value = "Finds accounts by given criteria", notes = "all optional parameter will be joined as \"and\" condition", nickname = "listAccounts", tags = {"Accounts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Account.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listAccounts(
            @Range(min = PageConstant.PAGE_MIN_SIZE, max = PageConstant.PAGE_MAX_SIZE)
            @ApiParam(value = "Page Size ", defaultValue = PageConstant.PAGE_SIZE + "", allowableValues = "range[" + PageConstant.PAGE_MIN_SIZE + "," + PageConstant.PAGE_MAX_SIZE + "]")
            @RequestParam(value = "limit", defaultValue = PageConstant.PAGE_SIZE + "") int limit,
            @ApiParam(value = "default 0", allowableValues = "range[0,infinity]")
            @Min(0)
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @ApiParam(value = "list of order by field name, value has following format: sortName1:[asc|desc],sortName2:[asc|desc] ...")
            @RequestParam(value = "orderBy", required = false) List<String> orderByFieldList,
            @ApiParam(value = "list of filter...")
            @RequestParam(value = "filter", required = false) String filter,
            @ApiParam(value = "Fetch total count or not, True indicate fetch total count ,False will not fetch total count ", defaultValue = "true")
            @RequestParam(value = "count", required = false, defaultValue = "true") boolean count
    ) {
        logger.info("Start retrieving account list according to criteria: {}", filter);
        PageQueryModel result = convertUtilities.constructPageQueryModel(accountService.getAllAccounts(limit, offset, filter, orderByFieldList, count), Account.class);
        logger.info("Got account list: {}", (result.getEntities() == null ? 0 : result.getEntities().size()));
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "create account", notes = "allow account creation", nickname = "create account", tags = {"Accounts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Account.class),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAccount(
            @ApiParam(value = "accounts to be created", required = true)
            @Valid
            @NotNull
            @RequestBody AccountExpress account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        Account result = convertUtilities.entity2Model(accountService.createAccounts(convertUtilities.model2Entity(account, AccountEntity.class)), Account.class);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(notes = "id must be integer", value = "Update account by ID", nickname = "updateAccount", tags = {"Accounts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Account.class),
            @ApiResponse(code = 400, message = "Invalid account Id"),
            @ApiResponse(code = 404, message = "Account not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PutMapping(value = "/{id:.+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAccount(
            @ApiParam(value = "ID of account that needs to be updated", required = true)
            @Min(value = 0, message = "Param id must be greater than 0")
            @PathVariable("id") Long id,
            @ApiParam(value = "account content", required = true)
            @Valid
            @NotNull
            @RequestBody Account anAccount, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        AccountEntity accountEntity = convertUtilities.model2Entity(anAccount, AccountEntity.class);
        Account account = convertUtilities.entity2Model(accountService.updateAccount(id, accountEntity), Account.class);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/userTypeOrg")
    public ResponseEntity getUserTypeOrgMapping(@RequestParam("ids") String accountIds) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @ApiOperation(notes = "delete account", value = "delete account by Id", nickname = "DeleteAccount", tags = {"Accounts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Boolean.class),
            @ApiResponse(code = 400, message = "Invalid account Id"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @DeleteMapping(value = "/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAccountById(@Min(value = 0, message = "Param id must be greater than 0")
                                            @ApiParam(value = "ID of account that needs to be updated", required = true)
                                            @PathVariable("id") Long id) {
        accountService.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/setting/suiteSSOAccountAllowable", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "suiteSSOAccountAllowable", value = "suiteSSOAccountAllowable", nickname = "suiteSSOAccountAllowable", tags = {"Accounts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Account.class),
            @ApiResponse(code = 400, message = "Invalid Parameter"),
            @ApiResponse(code = 500, message = "System General error")
    })
    public ResponseEntity suiteSSOAccountAllowable() {
        return ResponseEntity.ok(accountService.suiteSSOAccountAllowable());
    }


    @GetMapping(value = "/{id:.+}/org", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "get account by given id", value = "get account org by given id", nickname = "get account's orgs by given id", tags = {"Account"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = IDMOrg.class),
            @ApiResponse(code = 400, message = "The input Id is invalid!"),
            @ApiResponse(code = 404, message = "Entity not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    public ResponseEntity getOrg(@ApiParam(value = "id of account", allowableValues = "range[0,infinity]") @Min(0) @PathVariable("id") long id) {
        List<IDMOrg> ret = new ArrayList<>();
        AccountEntity acc = accountService.getAccountById(id);
        if(acc!=null && acc.getEnableSuiteSSO() && !acc.getDeleted()) {
            ret.add(new IDMOrg(ATSConstants.ORG_ID_SUITE_INTEGRATION, acc.getAccountIntegrationType()));
            ret.add(OrgUtil.getIdmOrgInstance(id, OrgType.DB));
            if (IntegrationType.LDAP.equals(acc.getAccountIntegrationType())) {
                ret.add(OrgUtil.getIdmOrgInstance(id, OrgType.SAML));
            } else if (IntegrationType.SAML.equals(acc.getAccountIntegrationType())){
                // integration type must be SAML if not LDAP
                ret.add(OrgUtil.getIdmOrgInstance(id, OrgType.LDAP));
            } else {
                throw new BoInvalidParameterException("Account integration type is invalid: " + acc.getAccountIntegrationType());
            }

        } else if (id == ATSConstants.OOTB_SYSTEM_TENANT_ID) {
            ret.add(new IDMOrg(ATSConstants.ORG_ID_SYSTEM_BACK_OFFICE, OrgType.DB));
        } else {
            ret.add(OrgUtil.getIdmOrgInstance(id, OrgType.DB));
            ret.add(OrgUtil.getIdmOrgInstance(id, OrgType.LDAP));
            ret.add(OrgUtil.getIdmOrgInstance(id, OrgType.SAML));
        }
        return ResponseEntity.ok(ret);
    }
}
