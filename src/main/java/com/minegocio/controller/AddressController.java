package com.minegocio.controller;

import com.minegocio.model.dto.AddressDTO;
import com.minegocio.service.AddressService;
import com.minegocio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{person}/addresses")
public class AddressController {

    @Autowired
    private AddressService service;
    @PostMapping
    public ResponseEntity<Void> addAddress(@PathVariable Long person,
                                           @RequestBody AddressDTO dto) {
        service.addAddressToCustomer(person, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses(@PathVariable Long person) {
        List<AddressDTO> addresses = service.getAddressesByCustomerId(person);
        if(addresses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addresses);
    }
}
