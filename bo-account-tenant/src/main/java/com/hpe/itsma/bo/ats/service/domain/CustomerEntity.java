package com.hpe.itsma.bo.ats.service.domain;

import com.hpe.itsma.bo.ats.service.domain.enumeration.CustomerStatus;
import com.hpe.itsma.bo.common.persistence.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer_entity")
@Audited // to save audit db log into tenant_entity_aud
public class CustomerEntity extends BaseEntity {

    public static final String ENTITY_TYPE = "Customer";

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "short_name", unique = true, nullable = false)
    private String shortName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CustomerStatus status;

    @Column(name = "type")
    private String type;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public static String getEntityType() {
        return ENTITY_TYPE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(shortName, that.shortName) &&
                status == that.status &&
                Objects.equals(type, that.type) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, shortName, status, type, phone, email, contact);
    }
}
