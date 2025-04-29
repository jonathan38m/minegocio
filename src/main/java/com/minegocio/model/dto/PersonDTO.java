package com.minegocio.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minegocio.model.entity.Address;
import com.minegocio.model.entity.Person;
import com.minegocio.validation.UniquePerson;

import javax.validation.constraints.NotBlank;

@UniquePerson
public class PersonDTO {
    private Long id;
    @NotBlank
    private String identificationType;
    @NotBlank
    private String identificationNumber;
    private String names;
    private String email;
    private String cellphone;
    private String mainProvidence;
    private String mainAddress;
    private String mainCity;

    public static PersonDTO fromEntity(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setIdentificationType(person.getIdentificationType());;
        dto.setIdentificationNumber(person.getIdentificationNumber());
        dto.setNames(person.getName());
        dto.setEmail(person.getEmail());
        dto.setCellphone(person.getCellphone());

        if (person.getMainAddress() != null) {
            AddressDTO addressDTO = AddressDTO.fromEntity(person.getMainAddress());
            dto.setMainProvidence(addressDTO.getProvince());
            dto.setMainCity(addressDTO.getCity());
            dto.setMainAddress(addressDTO.getAddress());
        }
        return dto;
    }
    @JsonIgnore
    public Address getAddressObj(){
        Address mainAddress = new Address();
        mainAddress.setCity(this.getMainCity());
        mainAddress.setAddress(this.getMainAddress());
        mainAddress.setProvince(this.getMainProvidence());
        return mainAddress;
    }

    @JsonIgnore
    public Person getPersonObj(){
        Person person = new Person();
        person.setIdentificationType(this.getIdentificationType());
        person.setIdentificationNumber(this.getIdentificationNumber());
        person.setName(this.getNames());
        person.setEmail(this.getEmail());
        person.setCellphone(this.getCellphone());
        return person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
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

    public String getMainProvidence() {
        return mainProvidence;
    }

    public void setMainProvidence(String mainProvidence) {
        this.mainProvidence = mainProvidence;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getMainCity() {
        return mainCity;
    }

    public void setMainCity(String mainCity) {
        this.mainCity = mainCity;
    }
}
