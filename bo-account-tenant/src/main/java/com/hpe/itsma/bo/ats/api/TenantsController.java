package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.model.deploy.*;
import com.hpe.itsma.bo.ats.model.express.TenantExpress;
import com.hpe.itsma.bo.ats.model.*;
import com.hpe.itsma.bo.ats.model.operation.CloneTenantModel;
import com.hpe.itsma.bo.ats.model.operation.ExportTenantModel;
import com.hpe.itsma.bo.ats.model.operation.ImportTenantModel;
import com.hpe.itsma.bo.ats.service.*;
import com.hpe.itsma.bo.ats.model.operation.CloneTenantResponse;
import com.hpe.itsma.bo.ats.service.domain.TenantEntity;
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
import java.security.InvalidParameterException;
import java.util.List;

@RestController
@Api(value = "Tenants Service API", description = "the tenants domain API")
@Validated
public class TenantsController extends BaseController {
    Logger logger = LoggerFactory.getLogger(TenantsController.class);

    public static final String TENANT_ID = "tenantId";
    public static final String URI_TENANTS = "/tenants/";
    public static final String URI_TENANTS_SEARCH = "/tenants/search";
    public static final String URI_THE_TENANT = URI_TENANTS + "{" + TENANT_ID + "}";


    @Autowired
    private TenantService tenantService;

    @Autowired
    private DeployTenantService deployTenantService;

    @Autowired
    private CloneTenantService cloneTenantService;

    @Autowired
    private MoveTenantService moveTenantService;

    @Autowired
    private MyTenantService myTenantService;

    @Autowired
    private ConvertUtilities convertUtilities;

    @ApiOperation(notes = "id must be integer", value = "Find tenant by ID", nickname = "getTenantById", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Tenant.class),
            @ApiResponse(code = 400, message = "Invalid tenant Id"),
            @ApiResponse(code = 404, message = "Tenant not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/tenants/{id:.+}", produces = "application/json")
    public ResponseEntity getTenantById(
            @Min(value = 0, message = "Param id must be greater than 0")
            @ApiParam(value = "ID of tenant that needs to be fetched", required = true)
            @PathVariable("id") Long id) {
        Tenant tenant = convertUtilities.entity2Model(tenantService.getTenantById(id), Tenant.class);
        return ResponseEntity.ok(tenant);
    }

    @ApiOperation(value = "Finds tenants by given criteria", notes = "all optional parameter will be joined as \"and\" condition", nickname = "listTenants", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Tenant.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/tenants", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity listTenants(
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
        logger.info("start getting tenants.");
        PageQueryModel result = convertUtilities.constructPageQueryModel(tenantService.getAllTenants(limit, offset, orderByFieldList, filter, count), Tenant.class);
        logger.info("ready to return tenants.");
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Finds public tenants by given account id", notes = "all optional parameter will be joined as \"and\" condition", nickname = "listTenants", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Tenant.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/tenants/public", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity listPublicTenantsInAccountOrId(
            @ApiParam(value = "given tenants id")
            @RequestParam(value = "ids", required = false) List<Long> tenants,
            @ApiParam(value = "given account id")
            @RequestParam(value = "accountId") List<Long> accounts,
            @ApiParam(value = "list of filter...")
            @RequestParam(value = "filter", required = false) String filter,
            @ApiParam(value = "Fetch total count or not, True indicate fetch total count ,False will not fetch total count ", defaultValue = "true")
            @RequestParam(value = "count", required = false, defaultValue = "true") boolean count
    ) {
        logger.info("start getting public tenants in given account.");
        PageQueryModel result = convertUtilities.constructPageQueryModel(tenantService.getAllPublicTenantsInAccountOrId(accounts, tenants, filter, count), Tenant.class);
        logger.info("ready to return public tenants in given account.");
        return ResponseEntity.ok(result);
    }


    @ApiOperation(value = "create tenant", notes = "allow tenant creation", nickname = "create tenant", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Tenant.class),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(value = "/tenants",consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity createTenant(
            @ApiParam(value = "tenants to be created", required = true)
            @NotNull
            @Valid
            @RequestBody TenantExpress tenantExpress, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        Tenant result = convertUtilities.entity2Model(tenantService.createTenants(convertUtilities.model2Entity(tenantExpress, TenantEntity.class)), Tenant.class);
        return ResponseEntity.ok(result);
    }


    @ApiOperation(notes = "id must be integer", value = "Update tenant by ID", nickname = "updateTenant", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Tenant.class),
            @ApiResponse(code = 400, message = "Invalid tenant Id"),
            @ApiResponse(code = 404, message = "Tenant not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PutMapping(value = "/tenants/{id:.+}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity updateTenant(
            @Min(value = 0, message = "Param id must be greater than 0")
            @ApiParam(value = "ID of tenant that needs to be updated", required = true)
            @PathVariable("id") Long id,
            @Valid
            @NotNull
            @ApiParam(value = "tenant content", required = true)
            @RequestBody Tenant aTenant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        Tenant tenant = convertUtilities.entity2Model(tenantService.updateTenant(id, convertUtilities.model2Entity(aTenant, TenantEntity.class)), Tenant.class);
        return ResponseEntity.ok(tenant);
    }


    @ApiOperation(notes = "any keywords", value = "search tenant by keyword", nickname = "searchTenant", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Tenant.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid tenant Id"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/tenants/search", produces = {"application/json"})
    public ResponseEntity searchTenant(
            @Range(min = PageConstant.PAGE_MIN_SIZE, max = PageConstant.PAGE_MAX_SIZE)
            @ApiParam(value = "Page Size ", defaultValue = PageConstant.PAGE_SIZE + "", allowableValues = "range[" + PageConstant.PAGE_MIN_SIZE + "," + PageConstant.PAGE_MAX_SIZE + "]")
            @Min(0)
            @RequestParam(value = "limit", defaultValue = PageConstant.PAGE_SIZE + "") int limit,
            @ApiParam(value = "default 0", allowableValues = "range[0,infinity]")
            @Min(0)
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @ApiParam(value = "list of order by field name, value has following format: sortName1:[asc|desc],sortName2:[asc|desc] ...")
            @RequestParam(value = "orderBy", required = false) List<String> orderByFieldList,
            @ApiParam(value = "keywords for search", required = true)
            @RequestParam(value = "keywords") String keyWords,
            @ApiParam(value = "list of filter...")
            @RequestParam(value = "filter", required = false) String filter,
            @ApiParam(value = "Fetch total count or not, True indicate fetch total count ,False will not fetch total count ", defaultValue = "true")
            @RequestParam(value = "count", required = false, defaultValue = "true") boolean count) {
        PageQueryModel result = convertUtilities.constructPageQueryModel(tenantService.searchTenant(limit, offset, orderByFieldList, keyWords, filter, count), Tenant.class);
        return ResponseEntity.ok(result);
    }


    @ApiOperation(notes = "tenant id", value = "deploy tenant to saw", nickname = "deployTenant", tags = {"Tenant"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = DeployTenantResponse.class),
            @ApiResponse(code = 400, message = "Invalid tenant Id"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(value = "/tenants/deploy", produces = {"application/json"})
    public ResponseEntity deployTenant(
            @ApiParam(value = "deploy tenant payload", required = true)
            @Valid
            @NotNull
            @RequestBody DeployTenantModel deployTenantModel,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        DeployTenantResponse deployTenantResult = deployTenantService.deployTenant(
                deployTenantModel.getTenantId(), deployTenantModel.getAccountId(), deployTenantModel.getAdminUserId());
        return ResponseEntity.ok(deployTenantResult);
    }


    @ApiOperation(notes = "id must be integer", value = "Change Tenant Type", nickname = "changeTenantType", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 204, message = "Succeed!"),
            @ApiResponse(code = 400, message = "Tenant Type can not be changed"),
            @ApiResponse(code = 404, message = "Tenant not found"),
            @ApiResponse(code = 500, message = "System General error")
    })

    @PutMapping(value = "/bo/rest/tenants/{id:.+}/type", produces = {"application/json"})
    public void changeTenantType (
            @PathVariable("id") Long id,
            @NotNull
            @RequestBody ChangeTenantTypeModel changeTenantTypeModel
            ) {
        tenantService.changeTenantType(id, changeTenantTypeModel.getTenantType());
    }

    @PostMapping(value = "/tenants/deploy/callback", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity deployTenantCallback(
            @RequestBody DeployTenantResponse deployTenantResponse
    ) {
        logger.info(deployTenantResponse.toString());
        deployTenantService.deployTenantCallback(deployTenantResponse);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(notes = "delete tenant", value = "delete tenant by Id", nickname = "DeleteTenant", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Boolean.class),
            @ApiResponse(code = 400, message = "Invalid tenant Id"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @DeleteMapping(value = "/tenants/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTenantById(@Min(value = 0, message = "Param id must be greater than 0")
                                    @ApiParam(value = "ID of tenant that needs to be deleted", required = true)
                                    @PathVariable("id") Long id) {
        tenantService.delete(id);
        return ResponseEntity.ok(true);
    }

    @ApiOperation(notes = "clone tenant", value = "clone tenant by source tenant and target tenant", nickname = "CloneTenant", tags = {"Tenant"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Object.class),
            @ApiResponse(code = 400, message = "Invalid tenant Id"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(value = "/tenants/clone", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity cloneTenant(
            @ApiParam(value = "clone tenant payload", required = true)
            @Valid
            @NotNull
            @RequestBody CloneTenantModel cloneTenantModel,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException();
        }
        CloneTenantResponse response = cloneTenantService.cloneTenant(cloneTenantModel);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(notes = "get clone tenant process status", value = "clone tenant process status by process id", nickname = "CloneTenant", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Object.class),
            @ApiResponse(code = 400, message = "Invalid process Id"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/tenants/clone/{processId:.+}", produces = {"application/json"})
    public ResponseEntity getCloneTenantProcessStatus(
            @PathVariable("processId") String processId
    ) {
        Object result = cloneTenantService.getCloneTenantProcessStatus(processId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/tenants/import", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "import tenant", value = "given file path and tenant id import tenant", tags = {"Tenant"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Object.class),
            @ApiResponse(code = 400, message = "Invalid Parameter"),
            @ApiResponse(code = 500, message = "System General error")
    })
    public ResponseEntity importTenant(
            @ApiParam(value = "import tenant payload", required = true)
            @RequestBody ImportTenantModel model) {
        return ResponseEntity.ok(moveTenantService.importTenant(model));
    }

    @PostMapping(value = "/tenants/export", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "export tenant", value = "given tenant id for export tenant", tags = {"Tenant"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Object.class),
            @ApiResponse(code = 400, message = "Invalid Parameter"),
            @ApiResponse(code = 500, message = "System General error")
    })
    public ResponseEntity exportTenant(
            @ApiParam(value = "export tenant payload", required = true)
            @RequestBody ExportTenantModel model) {
        return ResponseEntity.ok(moveTenantService.exportTenant(model));
    }

    @PutMapping(value = "/tenants/change/state", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "change Trial-Provision tenant state", value = "given tenant id and state for change tenant state", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Object.class),
            @ApiResponse(code = 400, message = "Invalid Parameter"),
            @ApiResponse(code = 500, message = "System General error")
    })
    public ResponseEntity changeTenantState(
            @ApiParam(value = "change Trial-Provision tenant state", required = true)
            @NotNull
            @RequestBody TenantStateModel model) {
        return ResponseEntity.ok(tenantService.changeTenantState(model));
    }

    @GetMapping(value = "/tenants/import/selectList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "list of import tenants file", value = "", tags = {"Tenant"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Object.class),
            @ApiResponse(code = 400, message = "Invalid Parameter"),
            @ApiResponse(code = 500, message = "System General error")
    })
    public ResponseEntity listImportFile(
    ) {
        return ResponseEntity.ok(moveTenantService.listImportTenant());
    }

    @GetMapping(value = "/tenants/me/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(notes = "list of all tenants that current user can access", value = "", tags = {"Tenant"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Object.class),
            @ApiResponse(code = 400, message = "Invalid Parameter"),
            @ApiResponse(code = 500, message = "System General error")
    })
    public ResponseEntity getMyTenants(
    ) {
        return ResponseEntity.ok(myTenantService.getMyTenants());
    }
}
