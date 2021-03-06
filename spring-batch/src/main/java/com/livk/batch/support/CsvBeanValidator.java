package com.livk.batch.support;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.lang.NonNull;


/**
 * <p>
 * CsvBeanValidator
 * </p>
 *
 * @author livk
 * @date 2021/12/27
 */
public class CsvBeanValidator<T> implements org.springframework.batch.item.validator.Validator<T> {

    private final Validator validator;

    public CsvBeanValidator() {
        try (var validatorFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validatorFactory.usingContext().getValidator();
        }
    }

    @Override
    public void validate(@NonNull T value) throws ValidationException {
        var constraintViolations = validator.validate(value);
        if (!constraintViolations.isEmpty()) {
            var message = new StringBuilder();
            constraintViolations
                    .forEach(constraintViolation -> message.append(constraintViolation.getMessage()).append("\n"));
            throw new ValidationException(message.toString());
        }
    }

}
