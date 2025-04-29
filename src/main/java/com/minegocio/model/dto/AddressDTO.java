package com.minegocio.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minegocio.model.entity.Address;

public class AddressDTO {

    private Long id;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private boolean isMain = false;

    public static AddressDTO fromEntity(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setAddress(address.getAddress());
        dto.setCity(address.getCity());
        dto.setProvince(address.getProvince());
        dto.setPostalCode(address.getPostalCode());
        dto.setMain(address.isMain());
        return dto;
    }

    public Address ToEntity(){
        Address address = new Address();
        address.setCity(this.getCity());
        address.setAddress(this.getAddress());
        address.setProvince(this.getProvince());
        address.setPostalCode(this.getPostalCode());
        address.setMain(this.isMain);
        return address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }
}
