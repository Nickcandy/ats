package com.hpe.itsma.bo.ats.model.deploy;


import java.util.List;

/**
 * Created by wxiaodon on 6/29/2017.
 */
public class DeployTenantResponse {

    private String tenantId;

    private String status;

    public static class DeployError {
        private String msg;

        private int errorCode;

        public DeployError(String msg, int errorCode) {
            this.msg = msg;
            this.errorCode = errorCode;
        }

        public DeployError(){}

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }
    }

    private DeployError error;

    public static class ProductStatuses {
        private String product;

        private String applicationUrl;

        private String farmId;

        private String status;

        private DeployError error;

        public ProductStatuses(String product, String applicationUrl, String farmId, String status, DeployError error) {
            this.product = product;
            this.applicationUrl = applicationUrl;
            this.farmId = farmId;
            this.status = status;
            this.error = error;
        }

        public ProductStatuses() {
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getApplicationUrl() {
            return applicationUrl;
        }

        public void setApplicationUrl(String applicationUrl) {
            this.applicationUrl = applicationUrl;
        }

        public String getFarmId() {
            return farmId;
        }

        public void setFarmId(String farmId) {
            this.farmId = farmId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public DeployError getError() {
            return error;
        }

        public void setError(DeployError error) {
            this.error = error;
        }
    }

    private List<ProductStatuses> productStatuses;

    public static class LicenseStatuses {
        private String licenseKey;

        private String status;

        private DeployError error;

        public LicenseStatuses(String licenseKey, String status, DeployError error) {
            this.licenseKey = licenseKey;
            this.status = status;
            this.error = error;
        }

        public String getLicenseKey() {
            return licenseKey;
        }

        public void setLicenseKey(String licenseKey) {
            this.licenseKey = licenseKey;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public DeployError getError() {
            return error;
        }

        public void setError(DeployError error) {
            this.error = error;
        }
    }

    private List<LicenseStatuses> licenseStatuses;

    public DeployTenantResponse(){}

    public DeployTenantResponse(String tenantId, String status, DeployError error, List<ProductStatuses> productStatuses, List<LicenseStatuses> licenseStatuses) {
        this.tenantId = tenantId;
        this.status = status;
        this.error = error;
        this.productStatuses = productStatuses;
        this.licenseStatuses = licenseStatuses;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeployError getError() {
        return error;
    }

    public void setError(DeployError error) {
        this.error = error;
    }

    public List<ProductStatuses> getProductStatuses() {
        return productStatuses;
    }

    public void setProductStatuses(List<ProductStatuses> productStatuses) {
        this.productStatuses = productStatuses;
    }

    public List<LicenseStatuses> getLicenseStatuses() {
        return licenseStatuses;
    }

    public void setLicenseStatuses(List<LicenseStatuses> licenseStatuses) {
        this.licenseStatuses = licenseStatuses;
    }

    @Override
    public String toString() {
        return "DeployTenantResponse{" +
                "tenantId='" + tenantId + '\'' +
                ", status=" + status +
                ", error=" + error +
                ", productStatuses=" + productStatuses +
                ", licenseStatuses=" + licenseStatuses +
                '}';
    }
}
