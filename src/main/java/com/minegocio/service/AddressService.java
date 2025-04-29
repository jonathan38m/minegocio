package com.minegocio.service;

import com.minegocio.exception.ResourceNotFoundException;
import com.minegocio.model.dto.AddressDTO;
import com.minegocio.model.entity.Address;
import com.minegocio.model.entity.Person;
import com.minegocio.repository.AddressRepository;
import com.minegocio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private AddressRepository addressRepository;
    @Transactional
    public void addAddressToCustomer(Long id, AddressDTO dto) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        if (dto.isMain()) {
            person.getAddresses().forEach(a -> a.setMain(false));
        }
        Address address = dto.ToEntity();
        address.setPerson(person);
        addressRepository.save(address);
    }

    public List<AddressDTO> getAddressesByCustomerId(Long personId) {
        List<Address> addresses = addressRepository.findByPersonId(personId);
        return addresses.stream()
                .map(AddressDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
