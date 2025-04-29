package com.minegocio.repository;

import com.minegocio.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameContainingIgnoreCase(String name);

    List<Person> findByIdentificationNumber(String identificationNumber);

    List<Person> findByNameContainingIgnoreCaseOrIdentificationNumber(String name, String identificationNumber);

    boolean existsByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber);

}
