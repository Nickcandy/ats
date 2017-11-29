package com.hpe.itsma.bo.ats.model;

import com.hpe.itsma.bo.ats.service.domain.enumeration.CustomerStatus;
import com.hpe.itsma.bo.common.api.BaseModelPremium;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@ApiModel(value = "Customer", description = "a pojo representing customer user data")
public class Customer extends BaseModelPremium {
    @ApiModelProperty(
            required = true,
            value = "full name"
    )
    @NotEmpty
    private String fullName;
    @ApiModelProperty(
            required = true,
            value = "short name"
    )
    @NotEmpty
    private String shortName;
    private String type;
    private String phone;
    @Email
    private String email;
    private String contact;
    @ApiModelProperty(
            required = true,
            value = "ACTIVE"
    )
    @NotNull
    private CustomerStatus status = null;

    public Customer() {
    }

    public Customer(String fullName, String shortName, String type, String phone, String email, String contact, CustomerStatus status) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.contact = contact;
        this.status = status;
    }

    public Customer id(Long id) {
        this.id = id;
        return this;
    }


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

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer account = (Customer) o;
        return Objects.equals(this.fullName, account.fullName) &&
                Objects.equals(this.shortName, account.shortName) &&
                Objects.equals(this.type, account.type) &&
                Objects.equals(this.phone, account.phone) &&
                Objects.equals(this.email, account.email) &&
                Objects.equals(this.contact, account.contact) &&
                Objects.equals(this.status, account.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Customer {\n");
        sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
        sb.append("    shortName: ").append(toIndentedString(shortName)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    contactId: ").append(toIndentedString(contact)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    @Override
    protected String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

