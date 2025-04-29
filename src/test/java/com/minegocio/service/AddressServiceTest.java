package com.minegocio.service;

import com.minegocio.model.dto.AddressDTO;
import com.minegocio.model.entity.Address;
import com.minegocio.model.entity.Person;
import com.minegocio.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Test
    void shouldReturnAddressDTOList_WhenCustomerHasAddresses() {
        Address address = new Address();
        address.setId(1L);
        address.setAddress("123 Main St");
        address.setMain(true);
        Person person = new Person();
        address.setPerson(person);

        when(addressRepository.findByPersonId(1L))
                .thenReturn(Collections.singletonList(address));

        List<AddressDTO> result = addressService.getAddressesByCustomerId(1L);

        assertEquals(1, result.size());
        assertEquals("123 Main St", result.get(0).getAddress());
        assertTrue(result.get(0).isMain());
    }

    @Test
    void shouldReturnEmptyList_WhenCustomerHasNoAddresses() {
        when(addressRepository.findByPersonId(99L)).thenReturn(Collections.emptyList());
        List<AddressDTO> result = addressService.getAddressesByCustomerId(99L);
        assertTrue(result.isEmpty());
    }
}
