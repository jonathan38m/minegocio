package com.minegocio.service;

import com.minegocio.exception.ResourceNotFoundException;
import com.minegocio.model.dto.AddressDTO;
import com.minegocio.model.dto.PersonDTO;
import com.minegocio.model.entity.Address;
import com.minegocio.model.entity.Person;
import com.minegocio.model.dto.PersonUpdateDTO;
import com.minegocio.repository.AddressRepository;
import com.minegocio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public List<Person> search(String name, String identificationNumber) {
        if (name != null && !name.isEmpty() && identificationNumber != null && !identificationNumber.isEmpty()) {
            return repository.findByNameContainingIgnoreCaseOrIdentificationNumber(name, identificationNumber);
        } else if (name != null && !name.isEmpty()) {
            return repository.findByNameContainingIgnoreCase(name);
        } else if (identificationNumber != null && !identificationNumber.isEmpty()) {
            return repository.findByIdentificationNumber(identificationNumber);
        } else {
            return new ArrayList<>();
        }
    }

    public Person findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    public PersonDTO createCustomer(Person person, Address mainAddress) {
        mainAddress.setMain(true);
        person.setMainAddress(mainAddress);
        mainAddress.setPerson(person);
        person.getAddresses().add(mainAddress);
        return PersonDTO.fromEntity(repository.save(person));
    }

    @Transactional
    public void updateCustomer(Long id, PersonUpdateDTO dto) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        person.setIdentificationNumber(dto.getIdentificationNumber());
        person.setEmail(dto.getEmail());
        person.setName(dto.getName());
        person.setCellphone(dto.getCellphone());
    }

    public void deletePerson(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        repository.delete(person);
    }

}
