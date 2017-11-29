package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.model.operation.ExportTenantModel;
import com.hpe.itsma.bo.ats.model.operation.ImportTenantModel;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wxiaodon on 10/20/2017.
 */
@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface MoveTenantService {

    Object importTenant(ImportTenantModel model);

    Object exportTenant(ExportTenantModel model);

    List<String> listImportTenant();
}
