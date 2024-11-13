package nl.geckotech.recipe.controller;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import nl.geckotech.recipe.exception.ErrorResponse;
import nl.geckotech.recipe.exception.RecipeNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Set;

@ControllerAdvice
public class RecipeControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleRecipeNotFound(RecipeNotFoundException recipeNotFoundException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("RECIPE_NOT_FOUND");
        errorResponse.setErrorMessages(recipeNotFoundException.getMessage());

        return new ResponseEntity<ErrorResponse>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("BAD_INPUT");
        errorResponse.setErrorMessages(dataIntegrityViolationException.getMessage());

        return new ResponseEntity<ErrorResponse>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException constraintViolationException){
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        List<String> violationList = violations
                .stream()
                .map(ConstraintViolation::getMessage)
                .filter(StringUtils::isBlank)
                .toList();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("BAD_INPUT");
        errorResponse.setErrorMessages(violationList);

        return new ResponseEntity<ErrorResponse>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }

}
