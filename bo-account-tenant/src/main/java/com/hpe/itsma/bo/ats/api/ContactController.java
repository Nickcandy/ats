package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.model.express.ContactExpress;
import com.hpe.itsma.bo.ats.model.Contact;
import com.hpe.itsma.bo.ats.service.ContactService;
import com.hpe.itsma.bo.ats.service.domain.ContactEntity;
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
@Api(value = "contacts Service API", description = "the contacts domain API")
@Validated
@RequestMapping("/contacts")
public class ContactController extends BaseController {
    public static final String CUSTOMER_ID = "contactId";
    public static final String URI_CUSTOMERS = "/contacts/";
    public static final String URI_URI_CUSTOMERS_PAGE_COUNT = "/contacts?limit=0&offset=0";
    public static final String URI_THE_CUSTOMER = URI_CUSTOMERS + "{" + CUSTOMER_ID + "}";
    public static final String SEARCH_PARAM_NAME = "name";
    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ConvertUtilities convertUtilities;

    @ApiOperation(notes = "id must be integer", value = "Find contact by ID", nickname = "getContactById", tags = {"Contacts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Contact.class),
            @ApiResponse(code = 400, message = "Invalid contact Id"),
            @ApiResponse(code = 404, message = "Contact not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/{id:.+}", produces = "application/json")
    public ResponseEntity getContactById(
            @Min(value = 0, message = "Param id must be greater than 0")
            @ApiParam(value = "ID of contact that needs to be fetched", required = true)
            @PathVariable("id") Long id) {
        Contact contact = convertUtilities.entity2Model(contactService.getContactById(id), Contact.class);
        return ResponseEntity.ok(contact);
    }


    @ApiOperation(value = "Finds contacts by given criteria", notes = "all optional parameter will be joined as \"and\" condition", nickname = "listContacts", tags = {"Contacts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = PageQueryModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listContacts(
            @Range(min = PageConstant.PAGE_MIN_SIZE, max = PageConstant.PAGE_MAX_SIZE)
            @ApiParam(value = "Page Size ", defaultValue = PageConstant.PAGE_SIZE + "", allowableValues = "range[" + PageConstant.PAGE_MIN_SIZE + "," + PageConstant.PAGE_MAX_SIZE + "]")
            @RequestParam(value = "limit", defaultValue = PageConstant.PAGE_SIZE + "") int limit,
            @ApiParam(value = "default 0", allowableValues = "range[0,infinity]")
            @Min(0)
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @ApiParam(value = "list of contact id")
            @RequestParam(value = "ids", required = false) List<Long> contactIdList,
            @ApiParam(value = "list of order by field name, value has following format: sortName1:[asc|desc],sortName2:[asc|desc] ...")
            @RequestParam(value = "orderBy", required = false) List<String> orderByFieldList,
            @ApiParam(value = "list of filter...")
            @RequestParam(value = "filter", required = false) String filter,
            @ApiParam(value = "Fetch total count or not, True indicate fetch total count ,False will not fetch total count ", defaultValue = "true")
            @RequestParam(value = "count", required = false, defaultValue = "true") boolean count
    ) {
        PageQueryModel<Contact> result = convertUtilities.constructPageQueryModel(contactService.getAllContacts(limit, offset, filter, orderByFieldList, count), Contact.class);
        return ResponseEntity.ok(result);
    }


    @ApiOperation(value = "create contact", notes = "allow contact creation", nickname = "create contact", tags = {"Contacts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Contact.class),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createContact(
            @ApiParam(value = "contacts to be created", required = true)
            @Valid
            @NotNull
            @RequestBody ContactExpress contactExpress, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        Contact result = convertUtilities.entity2Model(contactService.createContacts(convertUtilities.model2Entity(contactExpress, ContactEntity.class)),Contact.class);
        return ResponseEntity.ok(result);
    }


    @ApiOperation(notes = "id must be integer", value = "Update contact by ID", nickname = "updateContact", tags = {"Contacts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Contact.class),
            @ApiResponse(code = 400, message = "Invalid contact Id"),
            @ApiResponse(code = 404, message = "Contact not found"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @PutMapping(value = "/{id:.+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ParameterScriptAssert(script = "id.equals(aContact.getId())", lang = "javascript", message = "Param id not eq with Request body Contact.Id")
    public ResponseEntity updateContact(
            @Min(value = 0, message = "Param id must be greater than 0")
            @ApiParam(value = "ID of contact that needs to be updated", required = true)
            @PathVariable("id") Long id,
            @ApiParam(value = "contact content", required = true)
            @Valid
            @NotNull
            @RequestBody Contact aContact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BoInvalidParameterException();
        }
        Contact contact = convertUtilities.entity2Model(contactService.updateContact(convertUtilities.model2Entity(aContact, ContactEntity.class)), Contact.class);
        return ResponseEntity.ok(contact);
    }

    @ApiOperation(notes = "delete contact", value = "delete contact by Id", nickname = "DeleteContact", tags = {"Contacts"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Boolean.class),
            @ApiResponse(code = 400, message = "Invalid contact Id"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @DeleteMapping(value = "/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteContactById(@Min(value = 0, message = "Param id must be greater than 0")
                                    @ApiParam(value = "ID of contact that needs to be deleted", required = true)
                                    @PathVariable("id") Long id) {
        contactService.delete(id);
        return ResponseEntity.ok(true);
    }
}
