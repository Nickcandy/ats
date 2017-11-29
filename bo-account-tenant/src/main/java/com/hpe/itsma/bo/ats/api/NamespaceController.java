package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.model.express.NamespaceExpress;
import com.hpe.itsma.bo.ats.model.Namespace;

import com.hpe.itsma.bo.ats.service.NamespaceService;
import com.hpe.itsma.bo.ats.service.domain.NamespaceEntity;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/namespaces")
@Api(value = "namespaces Service API", description = "the namespaces domain API")
public class NamespaceController extends BaseController {

    Logger logger = LoggerFactory.getLogger(NamespaceController.class);

    public static final String NAMESPACE_ID = "namespaceId";
    public static final String URI_NAMESPACE = "/namespaces/";
    public static final String URI_THE_NAMESPACE = URI_NAMESPACE + "{" + NAMESPACE_ID + "}";
    public static final String SEARCH_PARAM_NAME = "name";

    @Autowired
    private NamespaceService namespaceService;

    @Autowired
    private ConvertUtilities convertUtilities;


    //@ApiOperation(notes = "id must be integer", value = "Find Namespace by ID", nickname = "getNamespaceById", tags = {"Namespaces"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Namespace.class),
            @ApiResponse(code = 400, message = "Invalid Namespace Id"),
            @ApiResponse(code = 404, message = "Namespace not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity getNamespaceById(
            @ApiParam(value = "ID of Namespace that needs to be fetched", required = true)
            @Min(value = 0, message = "Param id must be greater than 0")
            @PathVariable("id") Long id) {
        Namespace namespace = convertUtilities.entity2Model(namespaceService.getNamespaceById(id), Namespace.class);
        return ResponseEntity.ok(namespace);
    }


    //@ApiOperation(value = "Finds Namespaces by given criteria", notes = "all optional parameter will be joined as \"and\" condition", nickname = "listNamespaces", tags = {"Namespaces"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Namespace.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity listNamespaces(
            @Range(min = PageConstant.PAGE_MIN_SIZE, max = PageConstant.PAGE_MAX_SIZE)
            @ApiParam(value = "Page Size ", defaultValue = PageConstant.PAGE_SIZE + "", allowableValues = "range[" + PageConstant.PAGE_MIN_SIZE + "," + PageConstant.PAGE_MAX_SIZE + "]")
            @RequestParam(value = "limit", defaultValue = PageConstant.PAGE_SIZE + "") int limit,
            @ApiParam(value = "default 0", allowableValues = "range[0,infinity]")
            @Min(value = 0, message = "Item start must be greater than 0")
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @ApiParam(value = "list of Namespace id")
            @RequestParam(value = "ids", required = false) List<Long> namespaceIdList,
            @ApiParam(value = "list of order by field name, value has following format: sortName1:[asc|desc],sortName2:[asc|desc] ...")
            @RequestParam(value = "orderBy", required = false) List<String> orderByFieldList,
            @ApiParam(value = "list of filter...")
            @RequestParam(value = "filter", required = false) String filter,
            @ApiParam(value = "Fetch total count or not, True indicate fetch total count ,False will not fetch total count ", defaultValue = "true")
            @RequestParam(value = "count", required = false, defaultValue = "true") boolean count
    ) {
        PageQueryModel result = convertUtilities.constructPageQueryModel(namespaceService.getAllNameSpaces(limit, offset, namespaceIdList, orderByFieldList, filter, count), Namespace.class);
        return ResponseEntity.ok(result);
    }


    //@ApiOperation(value = "create Namespaces", notes = "allow batch creation", nickname = "createNamespaces", tags = {"Namespaces"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Namespace.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid Namespace Request Param"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity createNamespace(
            @ApiParam(value = "Namespaces to be created", required = true)
            @RequestBody NamespaceExpress namespaceExpress) {
        Namespace result = convertUtilities.entity2Model(namespaceService.createNamespaces(convertUtilities.model2Entity(namespaceExpress, NamespaceEntity.class)),Namespace.class);
        return ResponseEntity.ok(result);
    }


    //@ApiOperation(notes = "id must be integer", value = "Update Namespace by ID", nickname = "updateNamespace", tags = {"Namespaces"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Namespace.class),
            @ApiResponse(code = 400, message = "Invalid Namespace Request Param"),
            @ApiResponse(code = 404, message = "Namespace not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity updateNamespace(
            @ApiParam(value = "ID of Namespace that needs to be updated", required = true)
            @Min(value = 0, message = "Param id must be greater than 0")
            @PathVariable("id") Long id,
            @Valid
            @NotNull
            @ApiParam(value = "Namespace content", required = true)
            @RequestBody Namespace aNamespace, BindingResult result) {
        if (result.hasErrors()) {
            throw new BoInvalidParameterException("result:", result.toString());
        }
        Namespace namespace = convertUtilities.entity2Model(namespaceService.updateNamespace(id, convertUtilities.model2Entity(aNamespace, NamespaceEntity.class)), Namespace.class);
        return ResponseEntity.ok(namespace);
    }


    //@ApiOperation(notes = "any keywords", value = "search namespace by keyword", nickname = "searchNamespace", tags = {"Namespaces"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Namespace.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid Namespace Param"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/namespaces/search", produces = {"application/json"})
    public ResponseEntity searchNamespace(
            @Range(min = PageConstant.PAGE_MIN_SIZE, max = PageConstant.PAGE_MAX_SIZE)
            @ApiParam(value = "Page Size ", defaultValue = PageConstant.PAGE_SIZE + "", allowableValues = "range[" + PageConstant.PAGE_MIN_SIZE + "," + PageConstant.PAGE_MAX_SIZE + "]")
            @Min(value = 0, message = "Page size limit must be greater than 0")
            @RequestParam(value = "limit", defaultValue = PageConstant.PAGE_SIZE + "") int limit,
            @ApiParam(value = "default 0", allowableValues = "range[0,infinity]")
            @Min(value = 0, message = "Item start must be greater than 0")
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @ApiParam(value = "list of order by field name, value has following format: sortName1:[asc|desc],sortName2:[asc|desc] ...")
            @RequestParam(value = "orderBy", required = false) List<String> orderByFieldList,
            @ApiParam(value = "ID of namespace that needs to be updated", required = true)
            @RequestParam(value = "keywords") String keyWords,
            @ApiParam(value = "list of filter...")
            @RequestParam(value = "filter", required = false) String filter,
            @ApiParam(value = "Fetch total count or not, True indicate fetch total count ,False will not fetch total count ", defaultValue = "true")
            @RequestParam(value = "count", required = false, defaultValue = "true") boolean count
    ) {
        PageQueryModel result = convertUtilities.constructPageQueryModel(namespaceService.searchNamespace(limit, offset, orderByFieldList, keyWords, filter, count), Namespace.class);
        return ResponseEntity.ok(result);
    }
}
