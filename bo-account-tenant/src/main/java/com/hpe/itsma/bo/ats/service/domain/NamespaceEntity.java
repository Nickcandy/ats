package com.hpe.itsma.bo.ats.service.domain;

import com.hpe.itsma.bo.ats.service.domain.enumeration.NamespaceStateEnum;
import com.hpe.itsma.bo.ats.service.domain.enumeration.NamespaceTypeEnum;
import com.hpe.itsma.bo.common.persistence.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by wxiaodon on 6/23/2017.
 */
@Entity
@Table(name = "namespace_entity")
@Audited // to save audit db log into tenant_entity_aud
public class NamespaceEntity extends BaseEntity{

    private static final String ENTITY_TYPE = "Namespace";

    @Enumerated(EnumType.STRING)
    @Column(name = "namespace_type")
    private NamespaceTypeEnum namespaceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "namespace_state")
    private NamespaceStateEnum namespaceState;

    @Column(name = "identifier_in_k8s")
    private String identifierInK8S;

    @Column(name = "login_url")
    private String loginURL;

    @Column(name = "host_url_list")
    private String hostURLList;

    public NamespaceEntity(){}

    public NamespaceEntity(NamespaceTypeEnum namespaceType, NamespaceStateEnum namespaceState, String identifierInK8S, String loginURL, String hostURLList) {
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

    public static String getEntityType() {
        return ENTITY_TYPE;
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
        if (!super.equals(o)) return false;

        NamespaceEntity that = (NamespaceEntity) o;

        if (namespaceType != that.namespaceType) return false;
        if (namespaceState != that.namespaceState) return false;
        return identifierInK8S != null ? identifierInK8S.equals(that.identifierInK8S) : that.identifierInK8S == null;
    }

    @Override
    public int hashCode() {
        int result = namespaceType != null ? namespaceType.hashCode() : 0;
        result = 31 * result + (namespaceState != null ? namespaceState.hashCode() : 0);
        result = 31 * result + (identifierInK8S != null ? identifierInK8S.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NamespaceEntity{" +
                "namespaceType=" + namespaceType +
                ", namespaceState=" + namespaceState +
                ", identifierInK8S='" + identifierInK8S + '\'' +
                '}';
    }
}
