package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.service.OperationService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuyuf on 2017/7/19.
 */
@RestController
@Api(value = "operation Service API", description = "operation info regarding SAW integration")
@Validated
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    Logger logger = LoggerFactory.getLogger(OperationController.class);

    @ApiOperation(notes = "SAW tenant and url info", value = "SAW Info", nickname = "SAWInfo",tags = {"SAW"} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response=String.class),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/tenant",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity operationalTenantInfo(){
        return ResponseEntity.ok(operationService.getOperationalTenantInfo());
    }

}
