package com.hpe.itsma.bo.ats.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.NamespaceStateEnum;
import com.hpe.itsma.bo.ats.service.domain.enumeration.NamespaceTypeEnum;
import com.hpe.itsma.bo.common.api.BaseModelPremium;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
/**
 * Namespace
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-23T06:37:19.054Z")
@Validated
public class Namespace extends BaseModelPremium {
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

    public Namespace id(Long id) {
        this.id = id;
        return this;
    }

    public Namespace namespaceType(NamespaceTypeEnum namespaceType) {
        this.namespaceType = namespaceType;
        return this;
    }

    /**
     * The type of namespace
     * @return namespaceType
     **/
    @ApiModelProperty(required = true, value = "The type of namespace")
    @NotNull(message = "Namespace Type can not be null")
    public NamespaceTypeEnum getNamespaceType() {
        return namespaceType;
    }

    public void setNamespaceType(NamespaceTypeEnum namespaceType) {
        this.namespaceType = namespaceType;
    }

    public Namespace namespaceState(NamespaceStateEnum namespaceState) {
        this.namespaceState = namespaceState;
        return this;
    }

    @ApiModelProperty(value = "Kubernetes Namespace")
    public String getIdentifierInK8S() {
        return identifierInK8S;
    }

    public void setIdentifierInK8S(String identifierInK8S) {
        this.identifierInK8S = identifierInK8S;
    }

    /**
     * The state of namespace
     * @return namespaceState
     **/
    @ApiModelProperty(value = "The state of namespace")
    public NamespaceStateEnum getNamespaceState() {
        return namespaceState;
    }

    public void setNamespaceState(NamespaceStateEnum namespaceState) {
        this.namespaceState = namespaceState;
    }

    /**
     * The login url of namespace
     * @return loginURL
     **/
    @ApiModelProperty(value = "The login url of namespace")
    public String getLoginURL() {
        return loginURL;
    }

    public void setLoginURL(String loginURL) {
        this.loginURL = loginURL;
    }

    /**
     * The host url list of namespace
     * @return host url list
     **/
    @ApiModelProperty(value = "The host url list of namespace")
    public String getHostURLList() {
        return hostURLList;
    }

    public void setHostURLList(String hostURLList) {
        this.hostURLList = hostURLList;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Namespace namespace = (Namespace) o;
        return super.equals(namespace) &&
                Objects.equals(this.namespaceType, namespace.namespaceType) &&
                Objects.equals(this.namespaceState, namespace.namespaceState);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Namespace {\n");
        sb.append(super.toString());
        sb.append("    namespaceType: ").append(toIndentedString(namespaceType)).append("\n");
        sb.append("    namespaceState: ").append(toIndentedString(namespaceState)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}

