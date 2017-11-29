package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.ats.service.DeployTenantService;
import com.hpe.itsma.bo.ats.model.deploy.DeployTenantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sheyu on 8/25/2017.
 */
@RestController
public class TokenController {
    Logger logger = LoggerFactory.getLogger(TokenController.class);


    @Autowired
    private DeployTenantService tenantService;

    @PostMapping(value = "/saas-portal-mock/internal-portal/openservice/v1/lwsso/getToken")
    public String getToken() {
        return "QAo300EEnSlp5RcOB-bU1VA23tKCf7QXfnGMawQNc4Ibyra3isWR8ghkD6NeFtegcdKoXDXP1HE2rsybqgkkkQXHEpaV7msx_-K232XL9Xjd3xslvWR1ncNiVxblc05xhWG3a3dUrgALdYfd8JRA4QirkApy-qYAdVx2RHHwWcn1zz1LUW99FRclbOjac_hLNhB0XqIafACoVQnf4YcB156ghB_nyDClazD-EZ1i_n3nFU95cNQKQUPVEOCzLkpA0nuCydd03ovVgPEL2i44c8JkqEehrw6Gt-Q69WRCY5VajEXjhxMTB6_bc2SMsb5cPc_JxcRLAEAU2C-rHaMevYWZOvShUXxvHjQ4J16ugB50auGraE7ZFQIfabs8tUl2_CSyOOJ-CiQWfYGbE61sKkdGAQCUK-pmfo8hv0CRM6vlNNUb6tMISMbmzbzdXmSf7qKjMBaQ6Sfs2Oj6mjZnb0WsrjSshIyePeBTajYhxr2YHD_9ceqHTB905WEjvq8-KKELwGPFIbOWpBsnVtdzigHAHg92A_uDlKPdjpK_AHcPOZfnrm_LxXOouHMdZTeLLSdMKYObSSyRiUY47LqHY-7tdvX4mOnWxrncZKWie5-_IzVQAPwnTqQ2sP7ndy0dOpONhqxWqpDGaoSbA1sLZTTekt9TE8yfomou29cOcsTFVGR1GCcb5d5c98ZcSXr0D-DZXUv-bec1mxdwntVY_yMcLGzD2kolUpeFfRfh-4BnHX0sykqUDS8kc6o610RKp0Lt7CtlD6X72pb_joUO1wUjY1NJv5V9XRhLfF3oHoLPVyeoBEGLt8QzU1HVueYgkmwnMj4R_IczOJlw9jNovzOGUxcYcXQjOQ4xdVbBOsimFwUoKdam4SD-j0gvkFEkTxT0mqMBdZLa4FHtdKzpx-RHSBfj3D1FSw7v4XjyYdPGguyLQbsiFl0y30CD-iVTVy8MdAS7pdOe5l4MJTS_chuaXfYgs8fFwZG_1Dpb3a_KwCM6VkFm2e5u7N35JZ5POquQKLN1Ddeh2SXQ3JJuFHmdQ5qteL-NPFFwaEhS5FcRVa_fvGr1XVtaSKQefbf2KnOLcWi-g_f9pavgEQ3S0MtbXfxsNRwIemxCDyuHV4ym6wpjsROUDKA2OHheaq2m1tYXOjj6O9sphaFpd-FuvA..";
    }

    @PostMapping(value = "/saas-portal-mock/tenants/deploy/callback", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity deployTenantCallback(
            @RequestBody DeployTenantResponse deployTenantResponse
    ) {
        logger.info(deployTenantResponse.toString());
        tenantService.deployTenantCallback(deployTenantResponse);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "internal-portal/service/v1/tenant/provision/callback", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity deployTrialProvisionTenantCallback(
            @RequestBody DeployTenantResponse deployTenantResponse
    ) {
        logger.info(deployTenantResponse.toString());
        tenantService.deployTenantCallback(deployTenantResponse);
        return ResponseEntity.ok().build();
    }
}
