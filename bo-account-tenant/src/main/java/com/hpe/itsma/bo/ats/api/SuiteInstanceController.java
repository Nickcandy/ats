package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.model.express.SuiteInstanceExpress;
import com.hpe.itsma.bo.ats.model.SuiteInstance;
import com.hpe.itsma.bo.ats.service.SuiteInstanceService;
import com.hpe.itsma.bo.ats.service.domain.SuiteInstanceEntity;
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
import java.util.List;


@RestController
@Api(value = "Suite Instance Service API", description = "the suiteInstances domain API")
@Validated
@RequestMapping("/suiteInstances")
public class SuiteInstanceController extends BaseController {
    Logger logger = LoggerFactory.getLogger(SuiteInstanceController.class);
    public static final String SUITE_INSTANCE_ID = "suiteInstanceId";
    public static final String URI_SUITE_INSTANCE = "/suiteInstances/";
    public static final String URI_THE_SUITE_INSTANCE = URI_SUITE_INSTANCE + "{" + SUITE_INSTANCE_ID + "}";

    @Autowired
    private SuiteInstanceService suiteInstanceService;

    @Autowired
    private ConvertUtilities convertUtilities;

    @ApiOperation(notes = "id must be integer", value = "Find suite instance by ID", nickname = "getSuiteInstanceById", tags = {"SuiteInstances"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = SuiteInstance.class),
            @ApiResponse(code = 400, message = "Invalid suiteInstance Id"),
            @ApiResponse(code = 404, message = "SuiteInstance not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSuiteInstanceById(
            @ApiParam(value = "ID of suiteInstance that needs to be fetched", required = true)
            @Min(value = 0, message = "Param id must be greater than 0")
            @PathVariable("id") Long id) {
        SuiteInstance suiteInstance = convertUtilities.entity2Model(suiteInstanceService.getSuiteInstanceById(id), SuiteInstance.class);
        return ResponseEntity.ok(suiteInstance);
    }

    @ApiOperation(value = "Finds suiteInstances by given criteria", notes = "all optional parameter will be joined as \"and\" condition", nickname = "listSuiteInstances", tags = {"SuiteInstances"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = SuiteInstance.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listSuiteInstances(
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
        PageQueryModel<SuiteInstance> result = convertUtilities.constructPageQueryModel(suiteInstanceService.getAllSuiteInstances(limit, offset, filter, orderByFieldList, count), SuiteInstance.class);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "create suiteInstance", notes = "allow suiteInstance creation", nickname = "create suiteInstance", tags = {"SuiteInstances"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = SuiteInstanceExpress.class),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSuiteInstance(
            @ApiParam(value = "suiteInstances to be created", required = true)
            @Valid
            @NotNull
            @RequestBody  SuiteInstanceExpress suiteInstanceExpress, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        SuiteInstance result = convertUtilities.entity2Model(suiteInstanceService.createSuiteInstance(convertUtilities.model2Entity(suiteInstanceExpress, SuiteInstanceEntity.class)),SuiteInstance.class);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(notes = "id must be integer", value = "Update suiteInstance by ID", nickname = "updateSuiteInstance", tags = {"SuiteInstances"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = SuiteInstance.class),
            @ApiResponse(code = 400, message = "Invalid suiteInstance Id"),
            @ApiResponse(code = 404, message = "SuiteInstance not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PutMapping(value = "/{id:.+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateSuiteInstance(
            @ApiParam(value = "ID of suiteInstance that needs to be updated", required = true)
            @Min(value = 0, message = "Param id must be greater than 0")
            @PathVariable("id") Long id,
            @ApiParam(value = "suiteInstance content", required = true)
            @Valid
            @NotNull
            @RequestBody SuiteInstance anSuiteInstance, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        SuiteInstanceEntity suiteInstanceEntity = convertUtilities.model2Entity(anSuiteInstance, SuiteInstanceEntity.class);
        SuiteInstance suiteInstance = convertUtilities.entity2Model(suiteInstanceService.updateSuiteInstance(id, suiteInstanceEntity), SuiteInstance.class);
        return ResponseEntity.ok(suiteInstance);
    }
}
