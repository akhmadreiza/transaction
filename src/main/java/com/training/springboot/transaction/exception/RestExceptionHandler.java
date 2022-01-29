package com.training.springboot.transaction.exception;

import com.training.springboot.transaction.dto.error.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("masuk RestExceptionHandler handleMethodArgumentNotValid");
        List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        for (ObjectError err : objectErrorList) {
            String fieldName = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();
            ErrorDto errorDtoResult = ErrorDto.builder()
                    .errorCode("BAD_INPUT_400")
                    .errorMessage(fieldName.concat(" ").concat(errorMessage))
                    .timestamp(LocalDateTime.now().toString())
                    .build();
            errorDtoList.add(errorDtoResult);
        }
        return new ResponseEntity<>(errorDtoList, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errTimestamp = LocalDateTime.now().toString();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        ErrorDto errorDto = ErrorDto.builder()
                .errorCode("BAD_REQUEST_400")
                .errorMessage(ex.getMessage())
                .timestamp(errTimestamp)
                .build();
        errorDtoList.add(errorDto);
        return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DataNotFoundException.class})
    protected ResponseEntity<Object> handleDataNotFound(RuntimeException e, HttpServletRequest request) {
        String errTimestamp = LocalDateTime.now().toString();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        ErrorDto errorDto = ErrorDto.builder()
                .errorCode("DATA_NOT_FOUND_404")
                .errorMessage(e.getMessage())
                .timestamp(errTimestamp)
                .build();
        errorDtoList.add(errorDto);
        return new ResponseEntity<>(errorDtoList, HttpStatus.NOT_FOUND);
    }
}
