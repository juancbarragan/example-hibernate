package com.reece.service;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Abstract Service containing validation
 * 
 * @author juancarlosbarraganquintero
 */
@Service
@Validated
public abstract class AbstractService {

    @Autowired
    protected javax.validation.Validator validator;

    protected void validate(Object bean) {
        Set<ConstraintViolation<Object>> violations = validator.validate(bean);

        if (violations.size() > 0) {

            StringBuilder sb = new StringBuilder();

            violations.stream().map((violation) -> {

                return violation.getMessage();
            }).forEach((_item) -> {
                sb.append(_item);
                sb.append("\n");
            });

            throw new ValidationException(sb.toString());
        }
    }
}
