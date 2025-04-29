package com.minegocio.controller;

import com.minegocio.model.dto.PersonDTO;
import com.minegocio.model.entity.Address;
import com.minegocio.model.entity.Person;
import com.minegocio.model.dto.PersonUpdateDTO;
import com.minegocio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/search")
    public ResponseEntity<List<PersonDTO>> searchCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String identification
    ) {
        List<Person> customers = service.search(name, identification);
        List<PersonDTO> result = customers.stream()
                .map(PersonDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable Long id) {
        return PersonDTO.fromEntity(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@Valid @RequestBody PersonDTO req) {
        Address mainAddress = req.getAddressObj();
        Person person = req.getPersonObj();
        return ResponseEntity.ok(service.createCustomer(person, mainAddress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id,
                                            @RequestBody PersonUpdateDTO updateDTO) {
        service.updateCustomer(id, updateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        service.deletePerson(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
