package com.hpe.itsma.bo.ats.service.domain;

import com.hpe.itsma.bo.ats.service.domain.enumeration.*;
import com.hpe.itsma.bo.common.persistence.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "suite_instance_entity")
@Audited // to save audit db log into tenant_entity_aud
public class SuiteInstanceEntity extends BaseEntity{

    public static final String ENTITY_TYPE = "SuiteInstance";

    @Enumerated(EnumType.STRING)
    @Column(name = "suite_type")
    private SuiteType suiteType;

    public SuiteType getSuiteType() {
        return suiteType;
    }

    public void setSuiteType(SuiteType suiteType) {
        this.suiteType = suiteType;
    }

    public static String getEntityType() {
        return ENTITY_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SuiteInstanceEntity that = (SuiteInstanceEntity) o;
        return suiteType == that.suiteType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suiteType);
    }
}
