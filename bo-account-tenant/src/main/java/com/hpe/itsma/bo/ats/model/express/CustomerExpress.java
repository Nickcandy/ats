package com.hpe.itsma.bo.ats.model.express;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.CustomerStatus;
import com.hpe.itsma.bo.common.api.BaseModelExpress;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by xuyuf on 2017/8/10.
 */
public class CustomerExpress extends BaseModelExpress {
    @ApiModelProperty(
            required = true,
            value = "full name"
    )
    @NotEmpty
    @JsonProperty
    private String fullName;
    @ApiModelProperty(
            required = true,
            value = "short name"
    )
    @NotEmpty
    @JsonProperty
    private String shortName;
    @JsonProperty
    private String type;
    @JsonProperty
    private String phone;
    @JsonProperty
    @Email
    private String email;
    @JsonProperty
    private String contact;
    @ApiModelProperty(
            required = true,
            value = "ACTIVE"
    )
    @JsonProperty
    @NotNull
    private CustomerStatus status = null;

    public CustomerExpress() {
    }

    public CustomerExpress(String fullName, String shortName, String type, String phone, String email, String contact, CustomerStatus status) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.contact = contact;
        this.status = status;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerExpress that = (CustomerExpress) o;

        if (contact != that.contact) return false;
        if (status != that.status) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = fullName != null ? fullName.hashCode() : 0;
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerExpress{" +
                "fullName'=" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", type='" + type + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", contact=" + contact +
                ", status=" + status +
                '}';
    }

}
