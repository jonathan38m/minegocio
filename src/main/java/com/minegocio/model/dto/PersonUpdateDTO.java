package com.minegocio.model.dto;

import com.minegocio.validation.UniquePerson;

import javax.validation.constraints.NotBlank;

@UniquePerson
public class PersonUpdateDTO {

    @NotBlank
    private String identificationType;
    @NotBlank
    private String identificationNumber;

    private String name;

    private String email;

    private String cellphone;

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
