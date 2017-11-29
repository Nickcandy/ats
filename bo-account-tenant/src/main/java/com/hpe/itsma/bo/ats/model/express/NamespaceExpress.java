package com.hpe.itsma.bo.ats.model.express;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.NamespaceStateEnum;
import com.hpe.itsma.bo.ats.service.domain.enumeration.NamespaceTypeEnum;
import com.hpe.itsma.bo.common.api.BaseModelExpress;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

/**
 * Created by wxiaodon on 8/11/2017.
 */
@ApiModel(value = "NamespaceExpress", description = "a pojo representing namespaceExpress data for create namespace")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class NamespaceExpress extends BaseModelExpress {

    @JsonProperty
    private NamespaceTypeEnum namespaceType;

    @JsonProperty
    private NamespaceStateEnum namespaceState;

    @JsonProperty
    @Length(min = 63, max = 63, message = "Kubernetes Namespace identifier is not correct")
    @Pattern(regexp = "[a-z0-9]([-a-z0-9]*[a-z0-9])?", message = "Kubernetes Namespace identifier not conforming to regular")
    private String identifierInK8S;

    @JsonProperty
    private String loginURL;

    @JsonProperty
    private String hostURLList;

    public NamespaceExpress() {
    }

    public NamespaceExpress(NamespaceTypeEnum namespaceType, NamespaceStateEnum namespaceState, String identifierInK8S, String loginURL, String hostURLList) {
        this.namespaceType = namespaceType;
        this.namespaceState = namespaceState;
        this.identifierInK8S = identifierInK8S;
        this.loginURL = loginURL;
        this.hostURLList = hostURLList;
    }

    public NamespaceTypeEnum getNamespaceType() {
        return namespaceType;
    }

    public void setNamespaceType(NamespaceTypeEnum namespaceType) {
        this.namespaceType = namespaceType;
    }

    public NamespaceStateEnum getNamespaceState() {
        return namespaceState;
    }

    public void setNamespaceState(NamespaceStateEnum namespaceState) {
        this.namespaceState = namespaceState;
    }

    public String getIdentifierInK8S() {
        return identifierInK8S;
    }

    public void setIdentifierInK8S(String identifierInK8S) {
        this.identifierInK8S = identifierInK8S;
    }

    public String getLoginURL() {
        return loginURL;
    }

    public void setLoginURL(String loginURL) {
        this.loginURL = loginURL;
    }

    public String getHostURLList() {
        return hostURLList;
    }

    public void setHostURLList(String hostURLList) {
        this.hostURLList = hostURLList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NamespaceExpress that = (NamespaceExpress) o;

        if (namespaceType != that.namespaceType) return false;
        if (namespaceState != that.namespaceState) return false;
        if (identifierInK8S != null ? !identifierInK8S.equals(that.identifierInK8S) : that.identifierInK8S != null)
            return false;
        if (loginURL != null ? !loginURL.equals(that.loginURL) : that.loginURL != null) return false;
        return hostURLList != null ? hostURLList.equals(that.hostURLList) : that.hostURLList == null;
    }

    @Override
    public int hashCode() {
        int result = namespaceType != null ? namespaceType.hashCode() : 0;
        result = 31 * result + (namespaceState != null ? namespaceState.hashCode() : 0);
        result = 31 * result + (identifierInK8S != null ? identifierInK8S.hashCode() : 0);
        result = 31 * result + (loginURL != null ? loginURL.hashCode() : 0);
        result = 31 * result + (hostURLList != null ? hostURLList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NamespaceExpress{" +
                "namespaceType=" + namespaceType +
                ", namespaceState=" + namespaceState +
                ", identifierInK8S='" + identifierInK8S + '\'' +
                ", loginURL='" + loginURL + '\'' +
                ", hostURLList='" + hostURLList + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", displayLabel='" + displayLabel + '\'' +
                '}';
    }
}
