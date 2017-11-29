package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.model.Tenant;
import com.hpe.itsma.bo.ats.service.ATSDevService;
import com.hpe.itsma.bo.ats.service.domain.TenantEntity;
import com.hpe.itsma.bo.common.controller.BaseController;
import com.hpe.itsma.bo.common.exception.BoInvalidParameterException;
import com.hpe.itsma.bo.common.utils.ConvertUtilities;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@Api(value = "Tenants Service API", description = "the API for development environment")
@Validated
@Profile({"dev", "devpg", "kube"})
public class ATSDevController extends BaseController {
    Logger logger = LoggerFactory.getLogger(ATSDevController.class);

    @Autowired
    private ATSDevService atsDevService;

    @Autowired
    private ConvertUtilities convertUtilities;

    @ApiOperation(value = "create tenant", notes = "allow tenant creation", nickname = "create tenant", tags = {"Tenants"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Tenant.class),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(value = "/atsdev/tenants",consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity createTenant(
            @ApiParam(value = "tenants to be created", required = true)
            @NotNull
            @Valid
            @RequestBody Tenant tenant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        TenantEntity instance = new TenantEntity();
        copyProperties(tenant,instance);
        Tenant result = convertUtilities.entity2Model(atsDevService.createTenants(instance), Tenant.class);
        return ResponseEntity.ok(result);
    }

}
