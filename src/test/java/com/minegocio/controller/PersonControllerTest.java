package com.minegocio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minegocio.model.dto.AddressDTO;
import com.minegocio.model.dto.PersonDTO;
import com.minegocio.model.entity.Address;
import com.minegocio.model.entity.Person;
import com.minegocio.service.AddressService;
import com.minegocio.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void shouldReturnPerson_WhenRequestById() throws Exception {
        Person person = new Person();
        person.setId(1L);
        person.setName("Jonathan");
        Address address = new Address();
        address.setAddress("Av main 123");
        address.setProvince("Pichincha");
        address.setCity("City");
        person.setMainAddress(address);
        person.getAddresses().add(address);

        when(personService.findById(1L)).thenReturn(person);

        mockMvc.perform(get("/api/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.names").value("Jonathan"))
                .andExpect(jsonPath("$.mainProvidence").value("Pichincha"));
    }

}
