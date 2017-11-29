package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.model.express.CustomerExpress;
import com.hpe.itsma.bo.ats.model.Customer;
import com.hpe.itsma.bo.ats.service.CustomerService;
import com.hpe.itsma.bo.ats.service.domain.CustomerEntity;
import com.hpe.itsma.bo.common.api.PageQueryModel;
import com.hpe.itsma.bo.common.controller.BaseController;
import com.hpe.itsma.bo.common.exception.BoInvalidParameterException;
import com.hpe.itsma.bo.common.page.PageConstant;
import com.hpe.itsma.bo.common.utils.ConvertUtilities;
import io.swagger.annotations.*;
import org.hibernate.validator.constraints.ParameterScriptAssert;
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
@Api(value = "customers Service API", description = "the customers domain API")
@Validated
@RequestMapping("/customers")
public class CustomerController extends BaseController {
    public static final String CUSTOMER_ID = "customerId";
    public static final String URI_CUSTOMERS = "/customers/";
    public static final String URI_URI_CUSTOMERS_PAGE_COUNT = "/customers?limit=0&offset=0";
    public static final String URI_THE_CUSTOMER = URI_CUSTOMERS + "{" + CUSTOMER_ID + "}";
    public static final String SEARCH_PARAM_NAME = "name";
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ConvertUtilities convertUtilities;

    @ApiOperation(notes = "id must be integer", value = "Find customer by ID", nickname = "getCustomerById", tags = {"Customers"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Customer.class),
            @ApiResponse(code = 400, message = "Invalid customer Id"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/{id:.+}", produces = "application/json")
    public ResponseEntity getCustomerById(
            @Min(value = 0, message = "Param id must be greater than 0")
            @ApiParam(value = "ID of customer that needs to be fetched", required = true)
            @PathVariable("id") Long id) {
        Customer customer = convertUtilities.entity2Model(customerService.getCustomerById(id), Customer.class);
        return ResponseEntity.ok(customer);
    }


    @ApiOperation(value = "Finds customers by given criteria", notes = "all optional parameter will be joined as \"and\" condition", nickname = "listCustomers", tags = {"Customers"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = PageQueryModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listCustomers(
            @Range(min = PageConstant.PAGE_MIN_SIZE, max = PageConstant.PAGE_MAX_SIZE)
            @ApiParam(value = "Page Size ", defaultValue = PageConstant.PAGE_SIZE + "", allowableValues = "range[" + PageConstant.PAGE_MIN_SIZE + "," + PageConstant.PAGE_MAX_SIZE + "]")
            @RequestParam(value = "limit", defaultValue = PageConstant.PAGE_SIZE + "") int limit,
            @ApiParam(value = "default 0", allowableValues = "range[0,infinity]")
            @Min(0)
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @ApiParam(value = "list of customer id")
            @RequestParam(value = "ids", required = false) List<Long> customerIdList,
            @ApiParam(value = "list of order by field name, value has following format: sortName1:[asc|desc],sortName2:[asc|desc] ...")
            @RequestParam(value = "orderBy", required = false) List<String> orderByFieldList,
            @ApiParam(value = "list of filter...")
            @RequestParam(value = "filter", required = false) String filter,
            @ApiParam(value = "Fetch total count or not, True indicate fetch total count ,False will not fetch total count ", defaultValue = "true")
            @RequestParam(value = "count", required = false, defaultValue = "true") boolean count
    ) {
        PageQueryModel result = convertUtilities.constructPageQueryModel(customerService.getAllCustomers(limit, offset, filter, orderByFieldList, count), Customer.class);
        return ResponseEntity.ok(result);
    }


    @ApiOperation(value = "create customer", notes = "allow customer creation", nickname = "create customer", tags = {"Customers"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Customer.class),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(
            @ApiParam(value = "customers to be created", required = true)
            @Valid
            @NotNull
            @RequestBody CustomerExpress customerExpress, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        Customer result = convertUtilities.entity2Model(customerService.createCustomers(convertUtilities.model2Entity(customerExpress, CustomerEntity.class)),Customer.class);
        return ResponseEntity.ok(result);
    }


    @ApiOperation(notes = "id must be integer", value = "Update customer by ID", nickname = "updateCustomer", tags = {"Customers"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Customer.class),
            @ApiResponse(code = 400, message = "Invalid customer Id"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PutMapping(value = "/{id:.+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ParameterScriptAssert(script = "id.equals(aCustomer.getId())", lang = "javascript", message = "Param id not eq with Request body Customer.Id")
    public ResponseEntity updateCustomer(
            @Min(value = 0, message = "Param id must be greater than 0")
            @ApiParam(value = "ID of customer that needs to be updated", required = true)
            @PathVariable("id") Long id,
            @ApiParam(value = "customer content", required = true)
            @Valid
            @NotNull
            @RequestBody Customer aCustomer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        Customer customer = convertUtilities.entity2Model(customerService.updateCustomer(convertUtilities.model2Entity(aCustomer, CustomerEntity.class)), Customer.class);
        return ResponseEntity.ok(customer);
    }

    @ApiOperation(notes = "delete customer", value = "delete customer by Id", nickname = "DeleteCustomer", tags = {"Customers"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Boolean.class),
            @ApiResponse(code = 400, message = "Invalid customer Id"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @DeleteMapping(value = "/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCustomerById(@Min(value = 0, message = "Param id must be greater than 0")
                                    @ApiParam(value = "ID of customer that needs to be deleted", required = true)
                                    @PathVariable("id") Long id) {
        customerService.delete(id);
        return ResponseEntity.ok(true);
    }
}
