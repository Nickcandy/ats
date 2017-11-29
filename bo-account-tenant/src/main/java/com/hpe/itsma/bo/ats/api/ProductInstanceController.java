package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.model.express.ProductInstanceExpress;
import com.hpe.itsma.bo.ats.model.ProductInstance;
import com.hpe.itsma.bo.ats.service.ProductInstanceService;
import com.hpe.itsma.bo.ats.service.domain.ProductInstanceEntity;
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
@Api(value = "Product Instance Service API", description = "the productInstances domain API")
@Validated
@RequestMapping("/productInstances")
public class ProductInstanceController extends BaseController {
    Logger logger = LoggerFactory.getLogger(ProductInstanceController.class);
    public static final String SUITE_INSTANCE_ID = "productInstanceId";
    public static final String URI_SUITE_INSTANCE = "/productInstances/";
    public static final String URI_THE_SUITE_INSTANCE = URI_SUITE_INSTANCE + "{" + SUITE_INSTANCE_ID + "}";

    @Autowired
    private ProductInstanceService productInstanceService;

    @Autowired
    private ConvertUtilities convertUtilities;

    @ApiOperation(notes = "id must be integer", value = "Find product instance by ID", nickname = "getProductInstanceById", tags = {"ProductInstances"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = ProductInstance.class),
            @ApiResponse(code = 400, message = "Invalid productInstance Id"),
            @ApiResponse(code = 404, message = "ProductInstance not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getProductInstanceById(
            @ApiParam(value = "ID of productInstance that needs to be fetched", required = true)
            @Min(value = 0, message = "Param id must be greater than 0")
            @PathVariable("id") Long id) {
        ProductInstance productInstance = convertUtilities.entity2Model(productInstanceService.getProductInstanceById(id), ProductInstance.class);
        return ResponseEntity.ok(productInstance);
    }

    @ApiOperation(value = "Finds productInstances by given criteria", notes = "all optional parameter will be joined as \"and\" condition", nickname = "listProductInstances", tags = {"ProductInstances"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = ProductInstance.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listProductInstances(
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
        PageQueryModel<ProductInstance> result = convertUtilities.constructPageQueryModel(productInstanceService.getAllProductInstances(limit, offset, filter, orderByFieldList, count), ProductInstance.class);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "create productInstance", notes = "allow productInstance creation", nickname = "create productInstance", tags = {"ProductInstances"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = ProductInstanceExpress.class),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProductInstance(
            @ApiParam(value = "productInstances to be created", required = true)
            @Valid
            @NotNull
            @RequestBody  ProductInstanceExpress productInstanceExpress, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        ProductInstance result = convertUtilities.entity2Model(productInstanceService.createProductInstance(convertUtilities.model2Entity(productInstanceExpress, ProductInstanceEntity.class)),ProductInstance.class);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(notes = "id must be integer", value = "Update productInstance by ID", nickname = "updateProductInstance", tags = {"ProductInstances"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = ProductInstance.class),
            @ApiResponse(code = 400, message = "Invalid productInstance Id"),
            @ApiResponse(code = 404, message = "ProductInstance not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PutMapping(value = "/{id:.+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProductInstance(
            @ApiParam(value = "ID of productInstance that needs to be updated", required = true)
            @Min(value = 0, message = "Param id must be greater than 0")
            @PathVariable("id") Long id,
            @ApiParam(value = "productInstance content", required = true)
            @Valid
            @NotNull
            @RequestBody ProductInstance anProductInstance, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        ProductInstanceEntity productInstanceEntity = convertUtilities.model2Entity(anProductInstance, ProductInstanceEntity.class);
        ProductInstance productInstance = convertUtilities.entity2Model(productInstanceService.updateProductInstance(id, productInstanceEntity), ProductInstance.class);
        return ResponseEntity.ok(productInstance);
    }
}
