package com.hpe.itsma.bo.ats.model.express;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hpe.itsma.bo.ats.service.domain.enumeration.ContactRole;
import com.hpe.itsma.bo.common.api.BaseModelExpress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by yhuang1 on 7/9/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class ContactExpress extends BaseModelExpress {
    @ApiModelProperty(
            required = true,
            value = ""
    )
    @NotEmpty
    private String fullName;

    @ApiModelProperty
    @Email
    private String email;

    @ApiModelProperty
    private ContactRole role;

    @ApiModelProperty
    private String timezone;

    @ApiModelProperty
    private String region;

    @ApiModelProperty
    private String phone;

    @ApiModelProperty
    private String address;

    @ApiModelProperty
    @NotNull
    private Long account;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }
}
