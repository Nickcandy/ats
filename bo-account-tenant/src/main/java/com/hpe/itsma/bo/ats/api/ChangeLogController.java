package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.service.ChangelogService;
import com.hpe.itsma.bo.common.api.PageQueryObject;
import com.hpe.itsma.bo.common.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wxiaodon on 9/7/2017.
 */
@RestController
@Api(value = "Changelog Service API", description = "datermine whether the entity attribute is changed since the given datetime")
@RequestMapping("/changelog")
public class ChangeLogController extends BaseController{

    Logger logger = LoggerFactory.getLogger(ChangeLogController.class);

    @Autowired
    private ChangelogService changelogService;

    @ApiOperation(value = "whether the entity attribute is changed since the given datetime", nickname = "isChange", tags = {"Changelog"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = PageQueryObject.class),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity changeLog(
            @ApiParam(value = "entity type", required = true)
            @RequestParam(value = "entityType", required = true) String entityType,
            @ApiParam(value = "entity field", required = true)
            @RequestParam(value = "field", required = true) String fieldName,
            @ApiParam(value = "entity id", required = true)
            @RequestParam(value = "entityId", required = true) Long entityId,
            @ApiParam(value = "since the given datetime", required = true)
            @RequestParam(value = "lastUpdate", required = true) Long lastChangeDateTime
    ) {
        return ResponseEntity.ok(changelogService.isChangeOfAttributeChangeLog(entityType, fieldName, entityId, lastChangeDateTime));
    }

    @ApiOperation(value = "Finds field value list by given last update time", nickname = "listValue", tags = {"Changelog"})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Succeed!", response = Boolean.class),
            @ApiResponse(code = 400, message = "Invalid parameters"),
            @ApiResponse(code = 500, message = "System General error")
    })
    @GetMapping(value = "/listValue", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity listValueOfChangeLog(
            @ApiParam(value = "entity type", required = true)
            @RequestParam(value = "entityType") String entityType,
            @ApiParam(value = "entity field", required = true)
            @RequestParam(value = "field") String fieldName,
            @ApiParam(value = "entity id", required = true)
            @RequestParam(value = "entityId") Long entityId,
            @ApiParam(value = "since the given datetime")
            @RequestParam(value = "lastUpdate", required = false) Long lastChangeDateTime
    ) {
        return ResponseEntity.ok(changelogService.listValueOfAttributeChangelog(entityType, fieldName, entityId, lastChangeDateTime));
    }

}
