package com.minegocio.validation;

import com.minegocio.model.dto.PersonDTO;
import com.minegocio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePersonValidator implements ConstraintValidator<UniquePerson, PersonDTO> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean isValid(PersonDTO request, ConstraintValidatorContext context) {
        if (request.getIdentificationType() == null || request.getIdentificationNumber() == null) {
            return true;
        }

        return !personRepository.existsByIdentificationTypeAndIdentificationNumber(
                request.getIdentificationType(),
                request.getIdentificationNumber()
        );
    }
}
