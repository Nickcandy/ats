package com.hpe.itsma.bo.ats.service.domain;

import com.hpe.itsma.bo.ats.service.domain.enumeration.ContactRole;
import com.hpe.itsma.bo.common.persistence.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact_entity")
@Audited // to save audit db log into tenant_entity_aud
public class ContactEntity extends BaseEntity {
    private static final String ENTITY_TYPE = "Contact";

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "account_id")
    private Long account;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ContactRole role;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "region")
    private String region;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    public static String getEntityType() {
        return ENTITY_TYPE;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public ContactRole getRole() {
        return role;
    }

    public void setRole(ContactRole role) {
        this.role = role;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ContactEntity that = (ContactEntity) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(account, that.account) &&
                role == that.role &&
                Objects.equals(timezone, that.timezone) &&
                Objects.equals(region, that.region) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, email, account, role, timezone, region, phone, address);
    }
}
