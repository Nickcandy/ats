package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.model.operation.CloneTenantModel;
import com.hpe.itsma.bo.ats.model.operation.CloneTenantProcessResponse;
import com.hpe.itsma.bo.ats.model.operation.CloneTenantResponse;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;

/**
 * Created by wxiaodon on 10/20/2017.
 */
@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface CloneTenantService {

    CloneTenantResponse cloneTenant(CloneTenantModel model);

    CloneTenantProcessResponse getCloneTenantProcessStatus(String processId);

}
