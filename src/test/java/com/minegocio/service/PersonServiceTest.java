package com.minegocio.service;

import com.minegocio.model.entity.Person;
import com.minegocio.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @Test
    void shouldReturnPerson_WhenSearchByIdentification() {
        Person person = new Person();
        person.setName("Jonathan Mejia");
        person.setIdentificationNumber("17892838");

        when(repository.findByIdentificationNumber("17892838")).thenReturn(Collections.singletonList(person));
        List<Person> result = service.search(null,"17892838");
        assertEquals("Jonathan Mejia", result.get(0).getName());
    }

    @Test
    void shouldReturnPerson_WhenSearchByName() {
        Person person = new Person();
        person.setName("Jonathan Mejia");
        person.setIdentificationNumber("17892838");

        when(repository.findByNameContainingIgnoreCase("Jona")).thenReturn(Collections.singletonList(person));
        List<Person> result = service.search("Jona",null);
        assertEquals("Jonathan Mejia", result.get(0).getName());
    }




}
